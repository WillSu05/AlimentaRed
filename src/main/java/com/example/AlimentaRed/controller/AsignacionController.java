package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Entity.Asignacion;
import com.example.AlimentaRed.service.AsignacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
public class AsignacionController {

    private final AsignacionService asignacionService;

    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @GetMapping("/listarAsignaciones")
    public ResponseEntity<List<Asignacion>> listarAsignaciones() {
        return ResponseEntity.ok(asignacionService.listarAsignaciones());
    }

    @GetMapping("/buscarAsignacion/{id}")
    public ResponseEntity<Asignacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(asignacionService.obtenerPorId(id));
    }

    @PostMapping("/guardarAsignacion")
    public ResponseEntity<Asignacion> guardarAsignacion(@RequestBody Asignacion asignacion) {
        Asignacion nuevaAsignacion = asignacionService.guardarAsignacion(asignacion);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }
}