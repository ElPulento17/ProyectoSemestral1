package com.zapas.service_zapatillas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zapas.service_zapatillas.model.Zapatilla;

@Repository
public interface ZapatillaRepository extends JpaRepository<Zapatilla, Long> {

    List<Zapatilla> findByMarcaId(Long marcaId);
    
    List<Zapatilla> findByEstado(String estado);

}
