package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Dto.Request.EntregaConfirmacionRequestDto;
import com.example.AlimentaRed.Entity.Entrega;
import com.example.AlimentaRed.service.EntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping("/confirmar")
    public ResponseEntity<Entrega> confirmarEntrega(@RequestBody EntregaConfirmacionRequestDto requestDto) {
        Entrega entrega = entregaService.confirmarEntrega(requestDto);
        return new ResponseEntity<>(entrega, HttpStatus.CREATED);
    }
}