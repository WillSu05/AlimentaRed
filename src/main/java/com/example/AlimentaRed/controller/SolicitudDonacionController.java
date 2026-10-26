package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Dto.Request.ResolverSolicitudRequestDto;
import com.example.AlimentaRed.Dto.Request.SolicitudDonacionRequestDto;
import com.example.AlimentaRed.Entity.SolicitudDonacion;
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
    public ResponseEntity<SolicitudDonacion> crearSolicitud(@RequestBody SolicitudDonacionRequestDto requestDto) {
        SolicitudDonacion nuevaSolicitud = solicitudService.crearSolicitud(requestDto);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/resolver")
    public ResponseEntity<String> resolverSolicitud(@PathVariable Long id, @RequestBody ResolverSolicitudRequestDto requestDto) {
        solicitudService.resolverSolicitud(id, requestDto);
        return ResponseEntity.ok("Solicitud procesada correctamente con estado: " + requestDto.getEstado());
    }
}