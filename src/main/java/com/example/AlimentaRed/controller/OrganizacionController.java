package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Entity.Organizacion;
import com.example.AlimentaRed.service.OrganizacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizaciones")
public class OrganizacionController {

    private final OrganizacionService organizacionService;

    public OrganizacionController(OrganizacionService organizacionService) {
        this.organizacionService = organizacionService;
    }

    @PostMapping
    public ResponseEntity<Organizacion> registrarOrganizacion(@RequestBody Organizacion organizacion) {
        Organizacion nuevaOrg = organizacionService.registrarOrganizacion(organizacion);
        return new ResponseEntity<>(nuevaOrg, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Organizacion>> listarOrganizaciones() {
        return ResponseEntity.ok(organizacionService.listarOrganizaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizacion> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(organizacionService.obtenerPorId(id));
    }
}