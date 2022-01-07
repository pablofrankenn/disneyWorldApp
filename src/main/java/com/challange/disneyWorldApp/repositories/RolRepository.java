package com.challange.disneyWorldApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challange.disneyWorldApp.entities.Rol;
import com.challange.disneyWorldApp.security.enums.RolNombre;

//Notación que indica que es un repositorio
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
