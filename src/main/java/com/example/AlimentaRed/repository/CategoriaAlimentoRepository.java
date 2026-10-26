package com.example.AlimentaRed.repository;

import com.example.AlimentaRed.Entity.CategoriaAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAlimentoRepository extends JpaRepository<CategoriaAlimento, Long> {

}
