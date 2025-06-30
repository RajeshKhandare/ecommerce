package com.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stockQuantity;

    private String categoryName;
}
