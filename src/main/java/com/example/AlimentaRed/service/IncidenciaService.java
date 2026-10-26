package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Entity.Incidencia;
import java.util.List;

public interface IncidenciaService {
    Incidencia reportarIncidencia(Long loteId, String descripcion);
    List<Incidencia> listarPorLote(Long loteId);
}