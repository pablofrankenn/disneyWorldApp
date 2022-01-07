
package com.challange.disneyWorldApp.repositories;

import com.challange.disneyWorldApp.entities.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @author Franken
@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, String> {
    
     @Query("SELECT c FROM Pelicula c WHERE c.titulo= :titulo")
    public List<Pelicula> findByTitulo(@Param("titulo")String email);  
    
    @Query("SELECT c FROM Pelicula c WHERE c.genero.id= :generoid")
    public List<Pelicula> findByGenero(@Param("generoid")String generoid); 
}
