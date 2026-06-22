package com.zapas.service_proveedores.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zapas.service_proveedores.model.Proveedor;
import com.zapas.service_proveedores.model.ProveedorMarca;
import com.zapas.service_proveedores.repository.ProveedorMarcaRepository;
import com.zapas.service_proveedores.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
public class ProveedorMarcaServiceTest {

    @Mock
    private ProveedorMarcaRepository proveedorMarcaRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorMarcaService proveedorMarcaService;

    @Test
    public void asociarMarcaTest() {

        Proveedor proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Distribuidora Nike Chile");

        ProveedorMarca proveedorMarca = new ProveedorMarca();
        proveedorMarca.setId(1L);
        proveedorMarca.setMarcaId(1L);

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));
        when(proveedorMarcaRepository.save(proveedorMarca)).thenReturn(proveedorMarca);

        ProveedorMarca resultado = proveedorMarcaService.asociarMarca(1L, proveedorMarca);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getMarcaId());
        assertEquals(proveedor, resultado.getProveedor());
    }
}