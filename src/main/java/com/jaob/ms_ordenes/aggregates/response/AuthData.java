package com.jaob.ms_ordenes.aggregates.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthData {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}
