package com.jaob.ms_ordenes.aggregates.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrdenDetalladaResponse {
    private Long id;
    private LocalDateTime fecha;
    private AuthData usuario;
    private List<ProductoDTO> productos;

}
