package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Dto.Request.ResolverSolicitudRequestDto;
import com.example.AlimentaRed.Dto.Request.SolicitudDonacionRequestDto;
import com.example.AlimentaRed.Entity.SolicitudDonacion;

public interface SolicitudDonacionService {
    SolicitudDonacion crearSolicitud(SolicitudDonacionRequestDto requestDto);
    void resolverSolicitud(Long solicitudId, ResolverSolicitudRequestDto requestDto);
}