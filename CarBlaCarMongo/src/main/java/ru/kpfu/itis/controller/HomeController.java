package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.model.Driver;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.DriversService;
import ru.kpfu.itis.service.UsersService;

@Controller
public class HomeController {

    @Autowired
    DriversService driversService;

    @RequestMapping("/")
    String home(ModelMap modelMap) {
        modelMap.put("drivers", driversService.findAll());
        return "home";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sample() {
        return "home";
    }

}