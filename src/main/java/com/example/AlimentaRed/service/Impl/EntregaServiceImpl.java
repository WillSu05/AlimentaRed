package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Dto.Request.EntregaConfirmacionRequestDto;
import com.example.AlimentaRed.Entity.Asignacion;
import com.example.AlimentaRed.Entity.Entrega;
import com.example.AlimentaRed.repository.AsignacionRepository;
import com.example.AlimentaRed.repository.EntregaRepository;
import com.example.AlimentaRed.service.EntregaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntregaServiceImpl implements EntregaService {

    private final EntregaRepository entregaRepository;
    private final AsignacionRepository asignacionRepository;

    public EntregaServiceImpl(EntregaRepository entregaRepository, AsignacionRepository asignacionRepository) {
        this.entregaRepository = entregaRepository;
        this.asignacionRepository = asignacionRepository;
    }

    @Override
    @Transactional
    public Entrega confirmarEntrega(EntregaConfirmacionRequestDto requestDto) {
        Asignacion asignacion = asignacionRepository.findById(requestDto.getAsignacionId())
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        if (asignacion.getEstado().equals("COMPLETADA")) {
            throw new RuntimeException("Esta asignación ya fue entregada");
        }

        Entrega entrega = new Entrega();
        entrega.setFechaRetiro(requestDto.getFechaRetiro());
        entrega.setFechaRecepcion(requestDto.getFechaRecepcion());
        entrega.setObservaciones(requestDto.getObservaciones());
        entrega.setAsignacion(asignacion);

        asignacion.setEstado("COMPLETADA");
        asignacionRepository.save(asignacion);

        return entregaRepository.save(entrega);
    }
}