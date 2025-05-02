package com.jaob.ms_ordenes.aggregates.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBase<T> {
    private int statusCode;
    private boolean hasError;
    private String message;
    private T data;
}
