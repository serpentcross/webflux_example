package com.abnamro.reactive.services;

import com.abnamro.reactive.dtos.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class SluggishService {

    private final WebClient sluggishApiWebClient;

    public Mono<ItemDto> getItemDtoFromSluggishApi(Long id) {
        return sluggishApiWebClient
            .get()
            .uri("/api/sluggish/items/{id}", id)
            .retrieve()
            .bodyToMono(ItemDto.class);
    }

}
