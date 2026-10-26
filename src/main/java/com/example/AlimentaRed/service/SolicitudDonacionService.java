package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Dto.Request.ResolverSolicitudRequestDto;
import com.example.AlimentaRed.Dto.Request.SolicitudDonacionRequestDto;
import com.example.AlimentaRed.Dto.Response.SolicitudDonacionResponseDto;

public interface SolicitudDonacionService {

    SolicitudDonacionResponseDto crearSolicitud(SolicitudDonacionRequestDto requestDto);

    SolicitudDonacionResponseDto resolverSolicitud(Long id, ResolverSolicitudRequestDto requestDto);
}