package com.zapas.service_pagos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int monto;
    private LocalDate fechaPago;
    private String metodo; // Debito, transferencia
    private String estado; // APROBADO, RECHAZADO
    private String codigoTransaccion;

    private Long pedidoId;

    @Transient
    private Object datosPedido;
}