package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.controller.editor.AutomobileEditor;
import ru.kpfu.itis.forms.TripForm;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
public class TripsController {

    @Autowired
    TripsService tripsService;

    @Autowired
    BookingService bookingService;

    @Autowired
    ReviewsService reviewsService;

    @Autowired
    DriversService driversService;

    @Autowired
    AutosService autosService;

    @Autowired
    PassengersService passengersService;

    @Autowired
    UsersService usersService;

    @InitBinder
    public final void initBinderTripFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        binder.registerCustomEditor(Automobile.class, new AutomobileEditor());
    }


    @RequestMapping(value = "/newtrip", method = RequestMethod.GET)
    public String newTripPage(ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        if(user.getDriver().getAutomobileList().isEmpty()){
            modelMap.addAttribute("info", "Перед предложением поездки " +
                    "необходимо добавить машин");
            return "redirect:/newauto";
        }
        return "newtrip";
    }

    @RequestMapping(value = "/newtrip", method = RequestMethod.POST)
    public String newTrip(@ModelAttribute TripForm tripForm, BindingResult bindingResult,
                          ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        if(user == null) {
            return "login";
        }

        if(bindingResult.hasErrors()){
            modelMap.addAttribute("errors", bindingResult.getAllErrors());
            return "newtrip";
        }
        Trip trip = new Trip();
        trip.setDriver(user.getDriver());
        trip.setAuto(tripForm.getAuto());
        trip.setDeparture(tripForm.getDeparture());
        trip.setDestination(tripForm.getDestination());
        trip.setDate(tripForm.getDate());
        trip.setPrice(tripForm.getPrice());
        trip.setCount(tripForm.getCount());
        trip.setStatus("Ожидание");
        trip.setInfo(tripForm.getInfo());
        trip.setPassengers(new ArrayList<Passenger>());

        tripsService.addTrip(trip);

        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips/{tripId:\\d+}", method = RequestMethod.GET)
    public String joinTripPage(ModelMap modelMap, @PathVariable Long tripId) {
        Trip trip = tripsService.findById(tripId);
        modelMap.put("trip", trip);
        return "jointrip";
    }

    @RequestMapping(value = "/trips/{tripId:\\d+}", method = RequestMethod.POST)
    public String joinTrip(@ModelAttribute Booking booking, @PathVariable Long tripId,
                           Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        booking.setTrip(tripsService.findById(tripId));
        booking.setPassenger(user.getPassenger());
        bookingService.addBooking(booking);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/bookings/{bookingId:\\d+}/conf", method = RequestMethod.GET)
    public String confirmBooking(@PathVariable Long bookingId, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Booking booking = bookingService.findById(bookingId);
        booking.setConfirm(true);
        Trip trip = booking.getTrip();
        trip.getPassengers().add(booking.getPassenger());
        trip.setCount(trip.getCount() - booking.getCount());
        tripsService.update(trip);
        bookingService.update(booking);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/bookings/{bookingId:\\d+}/deny", method = RequestMethod.GET)
    public String denyBooking(@PathVariable Long bookingId, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Booking booking = bookingService.findById(bookingId);
        booking.setConfirm(false);
        bookingService.update(booking);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips/{tripId:\\d+}/status", method = RequestMethod.GET)
    public String tripStatusPage(@PathVariable Long tripId, ModelMap modelMap) {
        Trip trip = tripsService.findById(tripId);
        modelMap.put("trip", trip);
        return "tripstatus";
    }

    @RequestMapping(value = "/trips/{tripId:\\d+}/status", method = RequestMethod.POST)
    public String tripStatus(@PathVariable Long tripId, @RequestParam String status, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Trip trip = tripsService.findById(tripId);
        trip.setStatus(status);
        tripsService.update(trip);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips/{tripId:\\d+}/review", method = RequestMethod.GET)
    public String tripReviewPage(@PathVariable Long tripId, ModelMap modelMap, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Trip trip = tripsService.findById(tripId);
        modelMap.put("trip", trip);
        return "tripreview";
    }

    @RequestMapping(value = "/trips/{tripId:\\d+}/review", method = RequestMethod.POST)
    public String tripReview(@PathVariable Long tripId, @ModelAttribute Review review, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        Trip trip = tripsService.findById(tripId);
        review.setUser(user);
        review.setTrip(trip);
        reviewsService.addReview(review);
        if (user.getDriver() != null && trip.getDriver().getUser().getId() == user.getId()) {
            for (Passenger passenger : trip.getPassengers()) {
                passenger.setRating(passenger.getRating() + review.getGrade());
                passengersService.update(passenger);
            }

        } else {
            for (Passenger passenger : trip.getPassengers()) {
                if (passenger.getUser().getId() == user.getId()) {
                    Driver driver = trip.getDriver();
                    driver.setRating(driver.getRating() + review.getGrade());
                    driversService.update(driver);
                    break;
                }
            }

        }

        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public String TripsPage(ModelMap modelMap) {
        List<Trip> trips = tripsService.findByStatusOrderDate();
        modelMap.put("trips", trips);
        return "trips";
    }

    @RequestMapping(value = "/trips/find", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Trip> getTrips(HttpServletRequest request) {
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        List<Trip> trips = tripsService.findBySearch(departure, destination);
        return trips;
    }
}

