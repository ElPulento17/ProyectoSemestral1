package com.zapas.service_auth.dto;

import java.util.List;
import lombok.Data;

@Data
public class RegistroRequest {

    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private List<RolId> roles;

    @Data
    public static class RolId {
        private Long id;
    }

}