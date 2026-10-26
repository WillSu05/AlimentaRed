package com.example.AlimentaRed.repository;

import com.example.AlimentaRed.Entity.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    Optional<Asignacion> finBySolicitudId(Long solicitudId);
}
