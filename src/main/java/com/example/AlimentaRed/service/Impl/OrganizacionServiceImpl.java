package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Entity.Organizacion;
import com.example.AlimentaRed.repository.OrganizacionRepository;
import com.example.AlimentaRed.service.OrganizacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizacionServiceImpl implements OrganizacionService {

    private final OrganizacionRepository organizacionRepository;

    public OrganizacionServiceImpl(OrganizacionRepository organizacionRepository) {
        this.organizacionRepository = organizacionRepository;
    }

    @Override
    @Transactional
    public Organizacion registrarOrganizacion(Organizacion organizacion) {
        return organizacionRepository.save(organizacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Organizacion> listarOrganizaciones() {
        return organizacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Organizacion obtenerPorId(Long id) {
        return organizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organización no encontrada con ID: " + id));
    }
}