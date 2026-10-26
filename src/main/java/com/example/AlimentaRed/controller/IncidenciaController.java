package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Entity.Incidencia;
import com.example.AlimentaRed.service.IncidenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @PostMapping("/lote/{loteId}")
    public ResponseEntity<Incidencia> reportarIncidencia(@PathVariable Long loteId, @RequestParam String descripcion) {
        Incidencia incidencia = incidenciaService.reportarIncidencia(loteId, descripcion);
        return new ResponseEntity<>(incidencia, HttpStatus.CREATED);
    }

    @GetMapping("/lote/{loteId}")
    public ResponseEntity<List<Incidencia>> listarPorLote(@PathVariable Long loteId) {
        return ResponseEntity.ok(incidenciaService.listarPorLote(loteId));
    }
}