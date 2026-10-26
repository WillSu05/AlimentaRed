package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Dto.Request.ResolverSolicitudRequestDto;
import com.example.AlimentaRed.Dto.Request.SolicitudDonacionRequestDto;
import com.example.AlimentaRed.Dto.Response.SolicitudDonacionResponseDto;
import com.example.AlimentaRed.service.SolicitudDonacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudDonacionController {

    private final SolicitudDonacionService solicitudService;

    public SolicitudDonacionController(SolicitudDonacionService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/crearSolicitud")
    public ResponseEntity<SolicitudDonacionResponseDto> crearSolicitud(@RequestBody SolicitudDonacionRequestDto requestDto) {
        SolicitudDonacionResponseDto nuevaSolicitud = solicitudService.crearSolicitud(requestDto);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<SolicitudDonacionResponseDto> resolverSolicitud(
            @PathVariable Long id,
            @RequestBody ResolverSolicitudRequestDto requestDto) {
        SolicitudDonacionResponseDto solicitudResuelta = solicitudService.resolverSolicitud(id, requestDto);
        return ResponseEntity.ok(solicitudResuelta);
    }
}