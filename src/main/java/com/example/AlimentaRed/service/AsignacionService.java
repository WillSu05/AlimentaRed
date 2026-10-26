package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Entity.Asignacion;
import java.util.List;

public interface AsignacionService {
    List<Asignacion> listarAsignaciones();
    Asignacion obtenerPorId(Long id);
    Asignacion guardarAsignacion(Asignacion asignacion);
}