package com.zapas.service_resenas.model;

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
@Table(name = "resena")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntuacion;    // 1 a 5 estrellas
    private String comentario; // Comentario de reseña sobre la appp

    private Long usuarioId;   
    private Long zapatillaId;  

    @Transient
    private Object datosUsuario;

    @Transient
    private Object datosZapatilla;
}