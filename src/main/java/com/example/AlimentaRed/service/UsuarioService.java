package com.example.AlimentaRed.service;

import com.example.AlimentaRed.Dto.Response.UsuarioResponseDto;
import com.example.AlimentaRed.Entity.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    UsuarioResponseDto obtenerPorId(Long id);
    List<UsuarioResponseDto> listarUsuarios();
    UsuarioResponseDto obtenerPerfilPorEmail(String email);
}