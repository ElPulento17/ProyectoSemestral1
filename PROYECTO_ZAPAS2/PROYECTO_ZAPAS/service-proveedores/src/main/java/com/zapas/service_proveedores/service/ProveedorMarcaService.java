package com.zapas.service_proveedores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zapas.service_proveedores.model.Proveedor;
import com.zapas.service_proveedores.model.ProveedorMarca;
import com.zapas.service_proveedores.repository.ProveedorMarcaRepository;
import com.zapas.service_proveedores.repository.ProveedorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProveedorMarcaService {

    @Autowired
    private ProveedorMarcaRepository proveedorMarcaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    //este asocia una marca a un proveedor
    @Transactional
    public ProveedorMarca asociarMarca(Long proveedorId, ProveedorMarca proveedorMarca) {
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        proveedorMarca.setProveedor(proveedor);
        return proveedorMarcaRepository.save(proveedorMarca);
    }

    public List<ProveedorMarca> listarPorProveedor(Long proveedorId) {
        return proveedorMarcaRepository.findByProveedorId(proveedorId);
    }
}