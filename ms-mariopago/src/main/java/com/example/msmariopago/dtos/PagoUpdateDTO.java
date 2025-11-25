package com.example.msmariopago.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagoUpdateDTO {
    private BigDecimal monto;
    private LocalDateTime fecha;
}