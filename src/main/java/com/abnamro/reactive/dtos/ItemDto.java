package com.abnamro.reactive.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private LocalDate added;
    private Boolean available;
}