package com.zapas.service_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zapas.service_auth.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
}