package com.jaob.ms_ordenes.service.impl;

import com.jaob.ms_ordenes.aggregates.constants.Constantes;
import com.jaob.ms_ordenes.aggregates.request.OrdenRequest;
import com.jaob.ms_ordenes.aggregates.response.*;
import com.jaob.ms_ordenes.entity.Orden;
import com.jaob.ms_ordenes.exceptions.ResourceNotFoundException;
import com.jaob.ms_ordenes.repository.OrdenRepository;
import com.jaob.ms_ordenes.rest.AuthFeignClient;
import com.jaob.ms_ordenes.rest.ProductoFeignClient;
import com.jaob.ms_ordenes.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository repository;
    private final ProductoFeignClient productoClient;
    private final AuthFeignClient authClient;

    @Override
    public ResponseBase<OrdenDTO> crear(OrdenRequest request, String token) {
        validarProductosDisponibles(request, token);
        AuthData user = validarObtenerUsuario(token);
        Orden orden = Orden.builder()
                .usuarioId(user.getId())
                .productosIds(request.getProductosIds())
                .fecha(LocalDateTime.now())
                .build();
        Orden orden1 = repository.save(orden);
        return new ResponseBase<>(
                Constantes.CODE_CREATED,
                false,
                Constantes.MESSAGE_CREATED,
                generarOrdenDto(orden1));
    }

    @Override
    public ResponseBase<List<OrdenDTO>> listar() {
        List<Orden> list = repository.findAll();
        List<OrdenDTO> dtos = list.stream()
                .map(this::generarOrdenDto)
                .toList();
        return new ResponseBase<>(
                Constantes.CODE_SUCCESSFUL,
                false,
                Constantes.MESSAGE_SUCCESSFUL,
                dtos);
    }

    private OrdenDTO generarOrdenDto(Orden orden) {
        return OrdenDTO.builder()
                .id(orden.getId())
                .usuarioId(orden.getUsuarioId())
                .productosIds(orden.getProductosIds())
                .fecha(orden.getFecha())
                .build();
    }

    private void validarProductosDisponibles(OrdenRequest request, String token) {
        ResponseEntity<ProductoResponse> response = productoClient.listarProductos(token);
        if (response == null || response.getBody() == null || response.getBody().getData() == null) {
            throw new ResourceNotFoundException(Constantes.MESSAGE_EMPTY_LIST_PRODUCTS);
        }
        List<ProductoDTO> disponibles = response.getBody().getData();
        Set<Long> disponiblesIds = disponibles.stream().map(ProductoDTO::getId).collect(Collectors.toSet());
        if (!disponiblesIds.containsAll(request.getProductosIds())) {
            throw new ResourceNotFoundException(Constantes.MESSAGE_INVALID_PRODUCTS_IDS);
        }
    }

    private AuthData validarObtenerUsuario(String token) {
        ResponseEntity<AuthResponse> response = authClient.validateToken(token);
        if (response == null || response.getBody() == null || response.getBody().getData() == null) {
            throw new ResourceNotFoundException(Constantes.MESSAGE_USER_NOT_FOUND);
        }
        return response.getBody().getData();
    }
}
