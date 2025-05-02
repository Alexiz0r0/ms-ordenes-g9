package com.jaob.ms_ordenes.controller;

import com.jaob.ms_ordenes.aggregates.request.OrdenRequest;
import com.jaob.ms_ordenes.aggregates.response.OrdenDTO;
import com.jaob.ms_ordenes.aggregates.response.ResponseBase;
import com.jaob.ms_ordenes.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService service;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase<OrdenDTO>> crear(@RequestBody OrdenRequest request, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.crear(request, token), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase<List<OrdenDTO>>> listar() {
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }
}
