package com.example.msmariopago.service;


import com.example.msmariopago.Exceptions.ResourceNotFoundException;
import com.example.msmariopago.dtos.PagoRequestDTO;
import com.example.msmariopago.dtos.PagoResponseDTO;
import com.example.msmariopago.dtos.PagoUpdateDTO;
import com.example.msmariopago.entity.Pago;


import com.example.msmariopago.respository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    // ======================================================
    // CREAR
    // ======================================================
    @Transactional
    public PagoResponseDTO crearPago(PagoRequestDTO request) {

        Pago pago = Pago.builder()
                .monto(request.getMonto())
                .fecha(request.getFecha())
                .build();

        Pago savedPago = pagoRepository.save(pago);

        return mapToResponseDTO(savedPago);
    }

    // ======================================================
    // OBTENER POR ID
    // ======================================================
    @Transactional(readOnly = true)
    public PagoResponseDTO obtenerPagoPorId(Long idPago) {
        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + idPago));

        return mapToResponseDTO(pago);
    }

    // ======================================================
    // OBTENER TODOS
    // ======================================================
    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerTodosLosPagos() {
        return pagoRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ======================================================
    // ACTUALIZAR
    // ======================================================
    @Transactional
    public PagoResponseDTO actualizarPago(Long idPago, PagoUpdateDTO updateDTO) {

        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con id: " + idPago));

        if (updateDTO.getMonto() != null)
            pago.setMonto(updateDTO.getMonto());

        if (updateDTO.getFecha() != null)
            pago.setFecha(updateDTO.getFecha());

        Pago pagoActualizado = pagoRepository.save(pago);

        return mapToResponseDTO(pagoActualizado);
    }

    // ======================================================
    // ELIMINAR
    // ======================================================
    @Transactional
    public void eliminarPago(Long idPago) {
        if (!pagoRepository.existsById(idPago)) {
            throw new ResourceNotFoundException("Pago no encontrado con id: " + idPago);
        }

        pagoRepository.deleteById(idPago);
    }

    // ======================================================
    // MAPPER → PagoResponseDTO
    // ======================================================
    private PagoResponseDTO mapToResponseDTO(Pago pago) {
        return PagoResponseDTO.builder()
                .idPago(pago.getIdPago())
                .monto(pago.getMonto())
                .fecha(pago.getFecha())
                .createdAt(pago.getCreatedAt())
                .updatedAt(pago.getUpdatedAt())
                .build();
    }
}
