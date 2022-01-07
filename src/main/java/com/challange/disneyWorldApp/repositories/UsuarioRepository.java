
package com.challange.disneyWorldApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challange.disneyWorldApp.entities.Usuario;

// @author Franken
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Optional<Usuario> findByNombreUsuario(String nombreUsuario);

	boolean existsByNombreUsuario(String nombreUsuario);

	boolean existsByEmail(String email);
}
