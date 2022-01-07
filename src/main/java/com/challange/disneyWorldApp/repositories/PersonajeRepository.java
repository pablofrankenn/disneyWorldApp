
package com.challange.disneyWorldApp.repositories;


import com.challange.disneyWorldApp.entities.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @author Franken
 
@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, String> {
    @Query("SELECT c FROM Personaje c WHERE c.nombre= :nombre")
    public Personaje buscarPorNombre(@Param("nombre")String nombre);
    
    @Query("SELECT c FROM Personaje c WHERE c.nombre= :nombre")
    public List<Personaje> buscarPorNombreList(@Param("nombre")String nombre);
    
    @Query("SELECT c FROM Personaje c WHERE c.edad= :edad")
    public List<Personaje> buscarPorEdadList(@Param("edad")String edad);
    

    
}
