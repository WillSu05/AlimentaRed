package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Entity.CategoriaAlimento;
import com.example.AlimentaRed.repository.CategoriaAlimentoRepository;
import com.example.AlimentaRed.service.CategoriaAlimentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaAlimentoServiceImpl implements CategoriaAlimentoService {

    private final CategoriaAlimentoRepository categoriaRepository;

    public CategoriaAlimentoServiceImpl(CategoriaAlimentoRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaAlimento> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaAlimento obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public CategoriaAlimento guardarCategoria(CategoriaAlimento categoria) {
        return categoriaRepository.save(categoria);
    }
}