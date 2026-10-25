package com.example.AlimentaRed.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteAlimentoResponseDto {
    private Long id;
    private String nombre;
    private Double cantidad;
    private String unidad;
    private Date fechaLimite;
    private String estado;
    private String categoriaNombre;
    private String donanteNombre;
}
