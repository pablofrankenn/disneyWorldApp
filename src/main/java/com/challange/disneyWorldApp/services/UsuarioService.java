
package com.challange.disneyWorldApp.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challange.disneyWorldApp.entities.Usuario;
import com.challange.disneyWorldApp.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Optional<Usuario> getByUsuario(String nombreUsuario) {
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}

	public Boolean existsByUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}

	public Boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

}