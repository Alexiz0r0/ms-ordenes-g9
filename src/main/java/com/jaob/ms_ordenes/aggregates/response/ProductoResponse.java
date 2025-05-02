package com.jaob.ms_ordenes.aggregates.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductoResponse {
    private int statusCode;
    private boolean hasError;
    private String message;
    private List<ProductoDTO> data;
}
