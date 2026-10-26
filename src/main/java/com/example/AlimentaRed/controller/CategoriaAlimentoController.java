package com.example.AlimentaRed.controller;

import com.example.AlimentaRed.Entity.CategoriaAlimento;
import com.example.AlimentaRed.service.CategoriaAlimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaAlimentoController {

    private final CategoriaAlimentoService categoriaService;

    public CategoriaAlimentoController(CategoriaAlimentoService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listarCategoria")
    public ResponseEntity<List<CategoriaAlimento>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/buscarCategoriaAlimento/{id}")
    public ResponseEntity<CategoriaAlimento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @PostMapping("/guardarCategoria")
    public ResponseEntity<CategoriaAlimento> guardarCategoria(@RequestBody CategoriaAlimento categoria) {
        CategoriaAlimento nuevaCategoria = categoriaService.guardarCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }
}