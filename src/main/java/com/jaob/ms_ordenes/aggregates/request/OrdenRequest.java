package com.jaob.ms_ordenes.aggregates.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrdenRequest {

    private List<Long> productosIds;

}
