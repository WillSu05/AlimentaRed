package com.example.AlimentaRed.repository;

import com.example.AlimentaRed.Entity.SolicitudDonacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudDonacionRepository extends JpaRepository<SolicitudDonacion, Long> {
    List<SolicitudDonacion> findByOrganizacionId(Long organizacion);
    List<SolicitudDonacion> findByLoteId(Long loteId);
    List<SolicitudDonacion> findByEstado(String estado);
}
