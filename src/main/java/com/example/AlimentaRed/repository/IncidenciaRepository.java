package com.example.AlimentaRed.repository;

import com.example.AlimentaRed.Entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByLoteId(Long loteId);
}
