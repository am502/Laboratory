package ru.kpfu.itis.api;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.Trip;
import ru.kpfu.itis.service.TripsService;
import ru.kpfu.itis.viewobject.TripVO;

@RestController
@RequestMapping("/api/v1_0/trip")
public class TripRestController {

    @Autowired
    TripsService tripsService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TripVO> find(@PathVariable Long id){
        Trip trip = tripsService.findById(id);

        if (trip == null) {
            return new ResponseEntity<TripVO>(HttpStatus.NOT_FOUND);
        }
        TripVO result = new DozerBeanMapper().map(trip, TripVO.class);
        return new ResponseEntity<TripVO>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TripVO create(@RequestBody Trip trip){
        return new DozerBeanMapper().map(tripsService.addTrip(trip), TripVO.class);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<TripVO> update(@PathVariable Long id, @RequestBody Trip trip){
        trip.setId(id);
        tripsService.update(trip);
        return new ResponseEntity<TripVO>(HttpStatus.OK);
    }

}
