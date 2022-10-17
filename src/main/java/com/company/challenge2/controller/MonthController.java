package com.company.challenge2.controller;

import com.company.challenge2.models.MonthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class MonthController {

    private static List<MonthService> month = new ArrayList<>(Arrays.asList(
            new MonthService(1,"January"),
            new MonthService(2, "February"),
            new MonthService(3, "March"),
            new MonthService(4, "April"),
            new MonthService(5,"May"),
            new MonthService(6, "June"),
            new MonthService(7, "July"),
            new MonthService(8,"August"),
            new MonthService(9, "September"),
            new MonthService(10, "October"),
            new MonthService(11, "November"),
            new MonthService(12, "December")
    ));

    @RequestMapping(value="/months", method = RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public List<MonthService> getMonthNumber() {
        return  month;
    }

    @RequestMapping(value="/months/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public MonthService getMonthAndNumber(@PathVariable int monthNumber) {
        // This code is referenced from the dating-app example in class DatingController.java
        MonthService retrieveMonth = null;

        for(MonthService month : month) {
            if (month.getNumber() == monthNumber) {
                retrieveMonth = month;
                break;
            }
        }

        return retrieveMonth;
        

    }



    @RequestMapping(value="/randomMonth", method = RequestMethod.GET)
    @ResponseStatus(value=HttpStatus.OK)
    public MonthService randomNum() {

        Random misc = new Random();

        return month.get(misc.nextInt(12));
    }
}
