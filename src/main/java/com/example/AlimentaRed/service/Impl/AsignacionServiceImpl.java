package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Entity.Asignacion;
import com.example.AlimentaRed.repository.AsignacionRepository;
import com.example.AlimentaRed.service.AsignacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AsignacionServiceImpl implements AsignacionService {

    private final AsignacionRepository asignacionRepository;

    public AsignacionServiceImpl(AsignacionRepository asignacionRepository) {
        this.asignacionRepository = asignacionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignacion> listarAsignaciones() {
        return asignacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Asignacion obtenerPorId(Long id) {
        return asignacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID: " + id));
    }

    @Override
    @Transactional
    public Asignacion guardarAsignacion(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }
}