package com.example.msmariopago.controller;

import com.example.msmariopago.dtos.PagoRequestDTO;
import com.example.msmariopago.dtos.PagoResponseDTO;
import com.example.msmariopago.dtos.PagoUpdateDTO;
import com.example.msmariopago.service.PagoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crearPago(@RequestBody PagoRequestDTO request) {
        return ResponseEntity.ok(pagoService.crearPago(request));
    }

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> obtenerTodosLosPagos() {
        return ResponseEntity.ok(pagoService.obtenerTodosLosPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.obtenerPagoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> actualizarPago(
            @PathVariable Long id,
            @RequestBody PagoUpdateDTO request) {
        return ResponseEntity.ok(pagoService.actualizarPago(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
