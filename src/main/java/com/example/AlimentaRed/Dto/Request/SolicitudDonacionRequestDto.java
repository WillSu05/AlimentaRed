package com.example.AlimentaRed.Dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDonacionRequestDto {
    @NotNull(message = "La cantidad solicitada es obligatoria")
    @Positive(message = "La cantidad solicitada debe ser mayor a cero")
    private Double cantidadSolicitada;

    @NotNull(message = "El ID del lote es obligatorio")
    private Long loteId;

    @NotNull(message = "El ID de la organización receptora es obligatorio")
    private Long organizacionId;
}
