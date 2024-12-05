package com.nexai.todolist.controller;


import com.nexai.todolist.entity.HolidayResponse;
import com.nexai.todolist.service.impl.HolidayServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    private final HolidayServiceImpl holidayService;

    public HolidayController(HolidayServiceImpl holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/{countryCode}")
    public Mono<List<HolidayResponse>> getHolidays(@PathVariable String countryCode) {
        return holidayService.getHolidays(countryCode);
    }
}
