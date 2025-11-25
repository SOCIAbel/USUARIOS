package com.example.msmariopago.dtos;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PagoResponseDTO {
    private Long idPago;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}