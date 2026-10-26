package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Entity.CategoriaAlimento;
import java.util.List;

public interface CategoriaAlimentoService {
    List<CategoriaAlimento> listarCategorias();
    CategoriaAlimento obtenerPorId(Long id);
    CategoriaAlimento guardarCategoria(CategoriaAlimento categoria);
}