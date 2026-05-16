package com.zapas.service_zapatillas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="zapatilla")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zapatilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String modelo;
    private int precio;
    private int stock;
    private String estado; // ej nueva, usadas

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
}
