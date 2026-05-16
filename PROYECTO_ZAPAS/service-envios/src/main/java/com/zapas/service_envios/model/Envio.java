package com.zapas.service_envios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estadoPaquete; //ej proparando, en ruta, entregado
    private String direccionEntrega;
    private String empresaCourier; //ej Starken, Chilexpress

    private Long pedidoId;

    @Transient 
    private Object datosPedido;
}