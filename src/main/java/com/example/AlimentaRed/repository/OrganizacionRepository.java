package com.example.AlimentaRed.repository;

import com.example.AlimentaRed.Entity.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {
    Optional<Organizacion> findByUsuarioId (Long usuarioId);
}
