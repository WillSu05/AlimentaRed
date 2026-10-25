package com.example.AlimentaRed.Dto.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntregaConfirmacionRequestDto {
    @NotNull(message = "El ID de la asignación es obligatorio")
    private Long asignacionId;

    @NotNull(message = "La fecha de retiro es obligatoria")
    private Date fechaRetiro;

    @NotNull(message = "La fecha de recepción es obligatoria")
    private Date fechaRecepcion;

    private String observaciones;
}
