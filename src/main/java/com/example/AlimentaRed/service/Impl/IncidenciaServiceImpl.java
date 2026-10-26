package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Entity.Incidencia;
import com.example.AlimentaRed.Entity.LoteAlimento;
import com.example.AlimentaRed.repository.IncidenciaRepository;
import com.example.AlimentaRed.repository.LoteAlimentoRepository;
import com.example.AlimentaRed.service.IncidenciaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final LoteAlimentoRepository loteRepository;

    public IncidenciaServiceImpl(IncidenciaRepository incidenciaRepository, LoteAlimentoRepository loteRepository) {
        this.incidenciaRepository = incidenciaRepository;
        this.loteRepository = loteRepository;
    }

    @Override
    @Transactional
    public Incidencia reportarIncidencia(Long loteId, String descripcion) {
        LoteAlimento lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcion(descripcion);
        incidencia.setFechaReporte(new Date());
        incidencia.setEstado("REPORTADO");
        incidencia.setLote(lote);

        lote.setEstado("INCIDENCIA");
        loteRepository.save(lote);

        return incidenciaRepository.save(incidencia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Incidencia> listarPorLote(Long loteId) {
        return incidenciaRepository.findByLoteId(loteId);
    }
}