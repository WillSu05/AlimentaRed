package com.example.AlimentaRed.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "entrega")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_retiro", nullable = false)
    private Date fechaRetiro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_recepcion", nullable = false)
    private Date fechaRecepcion;

    private String observaciones;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignacion_id", nullable = false)
    private Asignacion asignacion;

}
