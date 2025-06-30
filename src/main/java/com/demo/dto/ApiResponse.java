package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Generic response wrapper for success messages.
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
}
