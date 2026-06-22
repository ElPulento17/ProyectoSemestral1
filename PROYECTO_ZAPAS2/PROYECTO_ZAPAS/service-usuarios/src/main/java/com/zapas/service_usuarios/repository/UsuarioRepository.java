package com.zapas.service_usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zapas.service_usuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByRun(String run);

    @Query("""
            SELECT u.tipoRol.nombre AS tipoRol,
                    COUNT(u) AS cantidad
            FROM Usuario u
            GROUP BY u.tipoRol.nombre
            """)
    List<Object[]> conteoPorTipoRol();

    @Query("""
            SELECT u FROM Usuario u
            JOIN FETCH u.direccion dir
            WHERE dir IS NOT NULL 
            """)
    List<Usuario> findUsuariosConDireccion();
}
