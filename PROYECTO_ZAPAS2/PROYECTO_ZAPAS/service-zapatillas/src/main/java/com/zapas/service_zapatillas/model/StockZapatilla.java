package com.zapas.service_zapatillas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "stock_zapatilla")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockZapatilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int talla;     
    private int cantidad;  
    private int precio;    
    private String estado; // nueva, usada

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zapatilla_id")
    @JsonIgnore 
    private Zapatilla zapatilla;
}