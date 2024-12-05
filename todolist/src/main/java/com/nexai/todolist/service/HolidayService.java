package com.nexai.todolist.service;

import com.nexai.todolist.entity.HolidayResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface HolidayService {

    Mono<List<HolidayResponse>> getHolidays(String countryCode);
}