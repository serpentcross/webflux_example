package com.abnamro.reactive.controllers;

import com.abnamro.reactive.dtos.ItemDto;
import com.abnamro.reactive.services.ItemService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public Mono<ItemDto> one(@PathVariable Long id) {
        // These lines mean - When I get our Dto, we have to do something with it. For example - pass it to our front.
        return Mono.just(
            ItemDto
                .builder()
                    .id(id)
                    .name("Item_" + id)
                    .available(new Random().nextBoolean())
                    .added(LocalDate.now())
                .build()
        );
    }

//    @GetMapping("/sluggish/{id}")
//    public Mono<ItemDto> oneFromSluggish(@PathVariable Long id) {
//        // These lines mean - When I get our Dto, we have to do something with it. For example - pass it to our front.
//        return itemService.getOneSlow(id);
//    }

    @GetMapping("/sluggish/{id}")
    public Flux<ItemDto> oneFromSluggish(@PathVariable Long id) {
        // These lines mean - When I get our Dto, we have to do something with it. For example - pass it to our front.

        Mono<ItemDto> item1 = itemService.getOneSlow(1L);
        Mono<ItemDto> item2 = itemService.getOneSlow(2L);
        Mono<ItemDto> item3 = itemService.getOneSlow(3L);

        return item1.mergeWith(item2).mergeWith(item3);
    }

    @GetMapping("/numbers")
    public Flux<Integer> generateNumbers() throws InterruptedException {
        Thread.sleep(10000);
        // Создаем поток данных с числами от 1 до 10 с интервалом в 1 секунду
        return Flux.range(1, 10).delayElements(Duration.ofSeconds(1)); // Задержка между элементами в потоке
    }

}