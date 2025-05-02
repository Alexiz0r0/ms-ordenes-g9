package com.jaob.ms_ordenes.service.impl;

import com.jaob.ms_ordenes.aggregates.constants.Constantes;
import com.jaob.ms_ordenes.aggregates.request.OrdenRequest;
import com.jaob.ms_ordenes.aggregates.response.*;
import com.jaob.ms_ordenes.entity.Orden;
import com.jaob.ms_ordenes.exceptions.ResourceNotFoundException;
import com.jaob.ms_ordenes.repository.OrdenRepository;
import com.jaob.ms_ordenes.rest.AuthFeignClient;
import com.jaob.ms_ordenes.rest.ProductoFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrdenServiceImplTest {

    @Mock
    private OrdenRepository repository;
    @Mock
    private ProductoFeignClient productoClient;
    @Mock
    private AuthFeignClient authClient;

    @InjectMocks
    private OrdenServiceImpl service;

    private Orden orden;
    private OrdenRequest request;
    private ProductoResponse productoResponse;
    private ProductoDTO productoDTO;
    private AuthResponse authResponse;
    private AuthData authData;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orden = new Orden();
        request = new OrdenRequest();
        productoResponse = new ProductoResponse();
        productoDTO = new ProductoDTO();
        authResponse = new AuthResponse();
        authData = new AuthData();
    }

    @Test
    void crear() {
        //ARRANGE
        String token = "Bearer aBc123";
        List<Long> productosIds = List.of(1L);
        request.setProductosIds(productosIds);
        productoDTO.setId(1L);
        productoResponse.setData(List.of(productoDTO));
        authResponse.setData(authData);
        when(productoClient.listarProductos(token)).thenReturn(ResponseEntity.ok(productoResponse));
        when(authClient.validateToken(token)).thenReturn(ResponseEntity.ok(authResponse));
        when(repository.save(any(Orden.class))).thenReturn(orden);
        //ACT
        ResponseBase<OrdenDTO> response = service.crear(request, token);
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_CREATED, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_CREATED, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());

        verify(productoClient).listarProductos(token);
        verify(authClient).validateToken(token);
        verify(repository).save(any(Orden.class));
    }

    @Test
    void listar() {
        //ARRANGE
        List<Orden> ordenes = List.of(orden);
        when(repository.findAll()).thenReturn(ordenes);
        //ACT
        ResponseBase<List<OrdenDTO>> response = service.listar();
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_SUCCESSFUL, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_SUCCESSFUL, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());

        verify(repository).findAll();
    }

    @Test
    void retornaListaVacia() {
        //ARRANGE
        when(repository.findAll()).thenReturn(Collections.emptyList());
        //ACT
        ResponseBase<List<OrdenDTO>> response = service.listar();
        //ASSERT
        assertNotNull(response);
        assertEquals(Constantes.CODE_SUCCESSFUL, response.getStatusCode());
        assertEquals(Constantes.MESSAGE_SUCCESSFUL, response.getMessage());
        assertFalse(response.isHasError());
        assertNotNull(response.getData());
        assertTrue(response.getData().isEmpty());

        verify(repository).findAll();
    }

    @Test
    void lanzarExcepcionListaProductoNoExisten() {
        //ARRANGE
        String token = "Bearer aBc123";
        List<Long> productosIds = List.of(2L);
        request.setProductosIds(productosIds);
        productoDTO.setId(1L);

        when(productoClient.listarProductos(token)).thenReturn(ResponseEntity.ok(productoResponse));
        //ACT
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.crear(request, token));
        //ASSERT
        assertEquals(Constantes.MESSAGE_EMPTY_LIST_PRODUCTS, exception.getMessage());

        verify(productoClient).listarProductos(token);
        verify(authClient, never()).validateToken(any());
        verify(repository, never()).save(any());
    }

    @Test
    void lanzarExcepcionProductoNoExisten() {
        //ARRANGE
        String token = "Bearer aBc123";
        List<Long> productosIds = List.of(2L);
        request.setProductosIds(productosIds);
        productoDTO.setId(1L);
        productoResponse.setData(List.of(productoDTO));

        when(productoClient.listarProductos(token)).thenReturn(ResponseEntity.ok(productoResponse));
        //ACT
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.crear(request, token));
        //ASSERT
        assertEquals(Constantes.MESSAGE_INVALID_PRODUCTS_IDS, exception.getMessage());

        verify(productoClient).listarProductos(token);
        verify(authClient, never()).validateToken(any());
        verify(repository, never()).save(any());
    }

    @Test
    void lanzarExceptionUsuarioNoEncontrado() {
        //ARRANGE
        String token = "Bearer aBc123";
        List<Long> productosIds = List.of(1L);
        request.setProductosIds(productosIds);
        productoDTO.setId(1L);
        productoResponse.setData(List.of(productoDTO));
        //ACT
        when(productoClient.listarProductos(token)).thenReturn(ResponseEntity.ok(productoResponse));
        when(authClient.validateToken(token)).thenReturn(ResponseEntity.ok(authResponse));
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.crear(request, token));
        //ASSERT
        assertEquals(Constantes.MESSAGE_USER_NOT_FOUND, exception.getMessage());

        verify(authClient).validateToken(token);
        verify(repository, never()).save(any());

    }
}