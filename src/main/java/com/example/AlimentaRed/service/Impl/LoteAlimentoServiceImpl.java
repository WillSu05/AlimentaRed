package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Dto.Request.LoteAlimentoRequestDto;
import com.example.AlimentaRed.Dto.Response.LoteAlimentoResponseDto;
import com.example.AlimentaRed.Entity.CategoriaAlimento;
import com.example.AlimentaRed.Entity.LoteAlimento;
import com.example.AlimentaRed.Entity.Usuario;
import com.example.AlimentaRed.repository.CategoriaAlimentoRepository;
import com.example.AlimentaRed.repository.LoteAlimentoRepository;
import com.example.AlimentaRed.repository.UsuarioRepository;
import com.example.AlimentaRed.service.LoteAlimentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoteAlimentoServiceImpl implements LoteAlimentoService {

    private final LoteAlimentoRepository loteRepository;
    private final CategoriaAlimentoRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public LoteAlimentoServiceImpl(LoteAlimentoRepository loteRepository,
                                   CategoriaAlimentoRepository categoriaRepository,
                                   UsuarioRepository usuarioRepository) {
        this.loteRepository = loteRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public LoteAlimentoResponseDto publicarLote(LoteAlimentoRequestDto requestDto) {
        CategoriaAlimento categoria = categoriaRepository.findById(requestDto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + requestDto.getCategoriaId()));

        Usuario donante = usuarioRepository.findById(requestDto.getDonanteId())
                .orElseThrow(() -> new RuntimeException("Donante no encontrado con ID: " + requestDto.getDonanteId()));

        LoteAlimento lote = new LoteAlimento();
        lote.setNombre(requestDto.getNombre());
        lote.setCantidad(requestDto.getCantidad());
        lote.setUnidad(requestDto.getUnidad());
        lote.setFechaLimite(requestDto.getFechaLimite());
        lote.setEstado("DISPONIBLE");
        lote.setCategoria(categoria);
        lote.setDonante(donante);

        LoteAlimento loteGuardado = loteRepository.save(lote);
        return mapearAResponseDto(loteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoteAlimentoResponseDto> listarLotesDisponibles() {
        return loteRepository.findByEstado("DISPONIBLE").stream()
                .map(this::mapearAResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LoteAlimentoResponseDto obtenerLotePorId(Long id) {
        LoteAlimento lote = loteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado con ID: " + id));
        return mapearAResponseDto(lote);
    }

    private LoteAlimentoResponseDto mapearAResponseDto(LoteAlimento lote) {
        LoteAlimentoResponseDto dto = new LoteAlimentoResponseDto();
        dto.setId(lote.getId());
        dto.setNombre(lote.getNombre());
        dto.setCantidad(lote.getCantidad());
        dto.setUnidad(lote.getUnidad());
        dto.setFechaLimite(lote.getFechaLimite());
        dto.setEstado(lote.getEstado());
        dto.setCategoriaNombre(lote.getCategoria().getNombre());
        dto.setDonanteNombre(lote.getDonante().getNombre());
        return dto;
    }
}