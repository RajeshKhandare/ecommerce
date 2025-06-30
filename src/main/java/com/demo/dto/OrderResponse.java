package com.demo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private String customerName;

    private LocalDateTime orderDate;

    private String status;

    private List<String> itemNames;
}
