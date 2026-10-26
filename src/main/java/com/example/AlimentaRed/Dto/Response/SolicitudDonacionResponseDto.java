package com.example.AlimentaRed.Dto.Response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SolicitudDonacionResponseDto {
    private Long id;
    private Double cantidadSolicitada;
    private LocalDateTime fechaSolicitud;
    private String estado;

    private Long loteId;
    private String loteNombre;
    private Long organizacionId;
    private String organizacionNombre;
}