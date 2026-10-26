package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Dto.Request.LoteAlimentoRequestDto;
import com.example.AlimentaRed.Dto.Response.LoteAlimentoResponseDto;
import com.example.AlimentaRed.service.LoteAlimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
public class LoteAlimentoController {

    private final LoteAlimentoService loteService;

    public LoteAlimentoController(LoteAlimentoService loteService) {
        this.loteService = loteService;
    }

    @PostMapping("/publicarLote")
    public ResponseEntity<LoteAlimentoResponseDto> publicarLote(@RequestBody LoteAlimentoRequestDto requestDto) {
        LoteAlimentoResponseDto nuevoLote = loteService.publicarLote(requestDto);
        return new ResponseEntity<>(nuevoLote, HttpStatus.CREATED);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<LoteAlimentoResponseDto>> listarDisponibles() {
        List<LoteAlimentoResponseDto> lotes = loteService.listarLotesDisponibles();
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/buscarLote/{id}")
    public ResponseEntity<LoteAlimentoResponseDto> obtenerPorId(@PathVariable Long id) {
        LoteAlimentoResponseDto lote = loteService.obtenerLotePorId(id);
        return ResponseEntity.ok(lote);
    }
}