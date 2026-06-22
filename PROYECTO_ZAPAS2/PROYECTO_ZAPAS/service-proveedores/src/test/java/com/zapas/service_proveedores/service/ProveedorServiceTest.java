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
import com.zapas.service_proveedores.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    public void guardarProveedorTest() {

        Proveedor proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Distribuidora Nike Chile");
        proveedor.setContacto("Pedro Soto");
        proveedor.setEmail("pedro@distribuidora.cl");

        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor resultado = proveedorService.guardar(proveedor);

        assertNotNull(resultado);
        assertEquals("Distribuidora Nike Chile", resultado.getNombre());
    }

    @Test
    public void buscarPorIdTest() {
        
        Proveedor proveedor = new Proveedor();
        proveedor.setId(1L);
        proveedor.setNombre("Distribuidora Adidas");

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));

        Optional<Proveedor> resultado = proveedorService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Distribuidora Adidas", resultado.get().getNombre());
    }
}