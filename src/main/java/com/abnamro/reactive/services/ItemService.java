package com.abnamro.reactive.services;

import com.abnamro.reactive.dtos.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final SluggishService sluggishService;

    public Mono<ItemDto> getOneSlow(Long id) {
        return sluggishService.getItemDtoFromSluggishApi(id);
    }

}