package com.example.AlimentaRed.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteAlimentoRequestDto {
    @NotBlank(message = "El nombre del lote es obligatorio")
    private String nombre;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    private Double cantidad;

    @NotBlank(message = "La unidad de medida es obligatoria (ej. kg, litros, unidades)")
    private String unidad;

    @NotNull(message = "La fecha límite de consumo es obligatoria")
    @Future(message = "La fecha límite debe ser posterior a la fecha actual")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Date fechaLimite;

    @NotNull(message = "La categoría del alimento es obligatoria")
    private Long categoriaId;

    @NotNull(message = "El ID del donante es obligatorio")
    private Long donanteId;

}
