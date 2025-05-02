package com.jaob.ms_ordenes.rest;

import com.jaob.ms_ordenes.aggregates.response.ProductoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "producto-client", url = "http://localhost:40003")
public interface ProductoFeignClient {

    @GetMapping("/productos/listar")
    ResponseEntity<ProductoResponse> listarProductos(@RequestHeader("Authorization") String token);

}
