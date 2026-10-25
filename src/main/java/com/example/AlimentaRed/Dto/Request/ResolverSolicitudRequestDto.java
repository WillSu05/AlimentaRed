package com.example.AlimentaRed.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolverSolicitudRequestDto {
    @NotBlank(message = "El estado de resolución es obligatorio")
    @Pattern(regexp = "^(APROBADO|RECHAZADO)$", message = "El estado debe ser APROBADO o RECHAZADO")
    private String estado; // APROBADO / RECHAZADO

    private String observaciones;

}
