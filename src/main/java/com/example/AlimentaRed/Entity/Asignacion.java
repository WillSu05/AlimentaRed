package com.example.AlimentaRed.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "asignacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_asignacion", nullable = false)
    private Date fechaAsignacion;

    @Column(nullable = false)
    private String estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudDonacion solicitud;

    @OneToOne(mappedBy = "asignacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Entrega entrega;

}
