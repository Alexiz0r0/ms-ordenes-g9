package com.jaob.ms_ordenes.aggregates.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrdenDTO  {
    private Long id;

    private Long usuarioId;

    private List<Long> productosIds;

    private LocalDateTime fecha;
}
