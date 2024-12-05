package com.nexai.todolist.service.impl;


import com.nexai.todolist.entity.HolidayResponse;
import com.nexai.todolist.service.HolidayService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {
    private final WebClient webClient;

    public HolidayServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://date.nager.at/Api").build();
    }

    @Override
    public Mono<List<HolidayResponse>> getHolidays(String countryCode) {
        return webClient.get()
                .uri("/PublicHolidays/2024/{countryCode}", countryCode) // Пример API
                .retrieve()  // Выполняем запрос
                .bodyToMono(new ParameterizedTypeReference<List<HolidayResponse>>() {
                });
    }
}
