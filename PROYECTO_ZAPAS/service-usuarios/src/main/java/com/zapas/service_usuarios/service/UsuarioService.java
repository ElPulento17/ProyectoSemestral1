package com.zapas.service_usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zapas.service_usuarios.model.Usuario;
import com.zapas.service_usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario guardar(Usuario usuario){
        if(usuario.getDireccion() != null){
            usuario.getDireccion().setUsuario(usuario);
        }
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id){
        usuarioRepository.deleteById(id);
    }
}
