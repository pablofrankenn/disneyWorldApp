
package com.challange.disneyWorldApp.repositories;

import com.challange.disneyWorldApp.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// @author Franken
@Repository
public interface GeneroRepository extends JpaRepository<Genero, String>{
    
}
