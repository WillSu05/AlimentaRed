package com.example.AlimentaRed.service.Impl;

import com.example.AlimentaRed.Dto.Request.ResolverSolicitudRequestDto;
import com.example.AlimentaRed.Dto.Request.SolicitudDonacionRequestDto;
import com.example.AlimentaRed.Entity.Asignacion;
import com.example.AlimentaRed.Entity.LoteAlimento;
import com.example.AlimentaRed.Entity.Organizacion;
import com.example.AlimentaRed.Entity.SolicitudDonacion;
import com.example.AlimentaRed.repository.AsignacionRepository;
import com.example.AlimentaRed.repository.LoteAlimentoRepository;
import com.example.AlimentaRed.repository.OrganizacionRepository;
import com.example.AlimentaRed.repository.SolicitudDonacionRepository;
import com.example.AlimentaRed.service.SolicitudDonacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SolicitudDonacionServiceImpl implements SolicitudDonacionService {

    private final SolicitudDonacionRepository solicitudRepository;
    private final LoteAlimentoRepository loteRepository;
    private final OrganizacionRepository organizacionRepository;
    private final AsignacionRepository asignacionRepository;

    public SolicitudDonacionServiceImpl(SolicitudDonacionRepository solicitudRepository,
                                        LoteAlimentoRepository loteRepository,
                                        OrganizacionRepository organizacionRepository,
                                        AsignacionRepository asignacionRepository) {
        this.solicitudRepository = solicitudRepository;
        this.loteRepository = loteRepository;
        this.organizacionRepository = organizacionRepository;
        this.asignacionRepository = asignacionRepository;
    }

    @Override
    @Transactional
    public SolicitudDonacion crearSolicitud(SolicitudDonacionRequestDto requestDto) {
        LoteAlimento lote = loteRepository.findById(requestDto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        if (!lote.getEstado().equals("DISPONIBLE") || lote.getFechaLimite().before(new Date())) {
            throw new RuntimeException("El lote no está disponible o ha vencido");
        }

        if (requestDto.getCantidadSolicitada() > lote.getCantidad()) {
            throw new RuntimeException("La cantidad solicitada supera la disponibilidad del lote");
        }

        Organizacion organizacion = organizacionRepository.findById(requestDto.getOrganizacionId())
                .orElseThrow(() -> new RuntimeException("Organización no encontrada"));

        SolicitudDonacion solicitud = new SolicitudDonacion();
        solicitud.setCantidadSolicitada(requestDto.getCantidadSolicitada());
        solicitud.setFechaSolicitud(new Date());
        solicitud.setEstado("PENDIENTE");
        solicitud.setLote(lote);
        solicitud.setOrganizacion(organizacion);

        return solicitudRepository.save(solicitud);
    }

    @Override
    @Transactional
    public void resolverSolicitud(Long solicitudId, ResolverSolicitudRequestDto requestDto) {
        SolicitudDonacion solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (!solicitud.getEstado().equals("PENDIENTE")) {
            throw new RuntimeException("La solicitud ya fue resuelta anteriormente");
        }

        solicitud.setEstado(requestDto.getEstado());
        solicitudRepository.save(solicitud);

        if (requestDto.getEstado().equals("APROBADO")) {
            LoteAlimento lote = solicitud.getLote();

            if (solicitud.getCantidadSolicitada() > lote.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para aprobar esta solicitud");
            }

            lote.setCantidad(lote.getCantidad() - solicitud.getCantidadSolicitada());
            if (lote.getCantidad() == 0.0) {
                lote.setEstado("ASIGNADO");
            }
            loteRepository.save(lote);

            Asignacion asignacion = new Asignacion();
            asignacion.setFechaAsignacion(new Date());
            asignacion.setEstado("PROGRAMADA");
            asignacion.setSolicitud(solicitud);
            asignacionRepository.save(asignacion);
        }
    }
}