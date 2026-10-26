package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Dto.Request.LoteAlimentoRequestDto;
import com.example.AlimentaRed.Dto.Response.LoteAlimentoResponseDto;
import java.util.List;

public interface LoteAlimentoService {
    LoteAlimentoResponseDto publicarLote(LoteAlimentoRequestDto requestDto);
    List<LoteAlimentoResponseDto> listarLotesDisponibles();
    LoteAlimentoResponseDto obtenerLotePorId(Long id);
}