package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Dto.Request.ResolverSolicitudRequestDto;
import com.example.AlimentaRed.Dto.Request.SolicitudDonacionRequestDto;
import com.example.AlimentaRed.Dto.Response.SolicitudDonacionResponseDto;
import com.example.AlimentaRed.Entity.Asignacion;
import com.example.AlimentaRed.Entity.LoteAlimento;
import com.example.AlimentaRed.Entity.Organizacion;
import com.example.AlimentaRed.Entity.SolicitudDonacion;
import com.example.AlimentaRed.repository.AsignacionRepository;
import com.example.AlimentaRed.repository.LoteAlimentoRepository;
import com.example.AlimentaRed.repository.OrganizacionRepository;
import com.example.AlimentaRed.repository.SolicitudDonacionRepository;
import com.example.AlimentaRed.service.SolicitudDonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SolicitudDonacionServiceImpl implements SolicitudDonacionService {

    @Autowired
    private SolicitudDonacionRepository solicitudRepository;

    @Autowired
    private LoteAlimentoRepository loteRepository;

    @Autowired
    private OrganizacionRepository organizacionRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Override
    @Transactional
    public SolicitudDonacionResponseDto crearSolicitud(SolicitudDonacionRequestDto requestDto) {

        LoteAlimento lote = loteRepository.findById(requestDto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        Organizacion organizacion = organizacionRepository.findById(requestDto.getOrganizacionId())
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));

        SolicitudDonacion solicitud = new SolicitudDonacion();
        solicitud.setCantidadSolicitada(requestDto.getCantidadSolicitada());
        solicitud.setFechaSolicitud(new Date());
        solicitud.setEstado("PENDIENTE");
        solicitud.setLote(lote);
        solicitud.setOrganizacion(organizacion);

        SolicitudDonacion guardada = solicitudRepository.save(solicitud);

        return convertirADto(guardada);
    }

    @Override
    @Transactional
    public SolicitudDonacionResponseDto resolverSolicitud(Long id, ResolverSolicitudRequestDto requestDto) {

        SolicitudDonacion solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if ("APROBADO".equalsIgnoreCase(requestDto.getEstado())) {
            solicitud.setEstado("APROBADO");

            LoteAlimento lote = solicitud.getLote();

            if (lote.getCantidad() < solicitud.getCantidadSolicitada()) {
                throw new RuntimeException("La cantidad solicitada supera el stock disponible del lote.");
            }

            lote.setCantidad(lote.getCantidad() - solicitud.getCantidadSolicitada());
            if (lote.getCantidad() == 0) {
                lote.setEstado("AGOTADO");
            }
            loteRepository.save(lote);

            Asignacion asignacion = new Asignacion();
            asignacion.setSolicitud(solicitud);
            asignacion.setFechaAsignacion(new Date());
            asignacion.setEstado("PENDIENTE");
            asignacionRepository.save(asignacion);

        } else if ("RECHAZADO".equalsIgnoreCase(requestDto.getEstado())) {
            solicitud.setEstado("RECHAZADO");
        } else {
            throw new RuntimeException("Estado no válido. Debe ser APROBADO o RECHAZADO.");
        }

        SolicitudDonacion actualizada = solicitudRepository.save(solicitud);
        return convertirADto(actualizada);
    }

    private SolicitudDonacionResponseDto convertirADto(SolicitudDonacion solicitud) {
        SolicitudDonacionResponseDto dto = new SolicitudDonacionResponseDto();
        dto.setId(solicitud.getId());
        dto.setCantidadSolicitada(solicitud.getCantidadSolicitada());

        if (solicitud.getFechaSolicitud() != null) {
            dto.setFechaSolicitud(solicitud.getFechaSolicitud().toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime());
        }

        dto.setEstado(solicitud.getEstado());

        if (solicitud.getLote() != null) {
            dto.setLoteId(solicitud.getLote().getId());
            dto.setLoteNombre(solicitud.getLote().getNombre());
        }

        if (solicitud.getOrganizacion() != null) {
            dto.setOrganizacionId(solicitud.getOrganizacion().getId());
            dto.setOrganizacionNombre(solicitud.getOrganizacion().getNombre());
        }

        return dto;
    }
}