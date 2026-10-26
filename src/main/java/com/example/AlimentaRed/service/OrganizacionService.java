package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Entity.Organizacion;
import java.util.List;

public interface OrganizacionService {
    Organizacion registrarOrganizacion(Organizacion organizacion);
    List<Organizacion> listarOrganizaciones();
    Organizacion obtenerPorId(Long id);
}