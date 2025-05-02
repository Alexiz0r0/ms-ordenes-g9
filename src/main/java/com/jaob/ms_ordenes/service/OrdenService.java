package com.jaob.ms_ordenes.service;

import com.jaob.ms_ordenes.aggregates.request.OrdenRequest;
import com.jaob.ms_ordenes.aggregates.response.OrdenDTO;
import com.jaob.ms_ordenes.aggregates.response.ResponseBase;

import java.util.List;

public interface OrdenService {

    ResponseBase<OrdenDTO> crear(OrdenRequest request, String token);

    ResponseBase<List<OrdenDTO>> listar();
}
