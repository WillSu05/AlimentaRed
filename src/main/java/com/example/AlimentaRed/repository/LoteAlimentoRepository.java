package com.example.AlimentaRed.repository;

import com.example.AlimentaRed.Entity.LoteAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoteAlimentoRepository extends JpaRepository<LoteAlimento, Long> {

    List<LoteAlimento> findByEstado(String estado);
    List<LoteAlimento> findByCategoriaIdAndEstado(Long categoriaId, String estado);
    List<LoteAlimento> findByDonanteId(Long donanteId);

}
