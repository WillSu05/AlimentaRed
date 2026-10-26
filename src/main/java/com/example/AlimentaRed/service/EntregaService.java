package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Dto.Request.EntregaConfirmacionRequestDto;
import com.example.AlimentaRed.Entity.Entrega;

public interface EntregaService {
    Entrega confirmarEntrega(EntregaConfirmacionRequestDto requestDto);
}