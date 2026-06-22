package com.zapas.service_proveedores.model;

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
@Table(name = "proveedor_marca")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorMarca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long marcaId; 

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
}