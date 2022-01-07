

package com.challange.disneyWorldApp.services;



import com.challange.disneyWorldApp.entities.Genero;
import com.challange.disneyWorldApp.repositories.GeneroRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;
    
    @Transactional
    public void crearGenero(Genero genero){
       generoRepository.save(genero);
    }
    
    @Transactional
    public void borrarGenero(String idgenero) throws Exception{
        Optional<Genero> genero= generoRepository.findById(idgenero);
        if (genero.isPresent()) {
            generoRepository.delete(genero.get());            
        }else{
            throw new Exception("No se encuentra genero con ese ID");
        }
    }
    
    @Transactional
    public void updateGenero(Genero genero) throws Exception{
        Optional<Genero> optional= generoRepository.findById(genero.getId());
        if (optional.isPresent()) {
           generoRepository.save(genero); 
        }else{
            throw new Exception("No se encuentra un genero con ese ID");
        }
        
    }
    
    public List<Genero> getAll(){
        List<Genero> listaAllGeneros= generoRepository.findAll();
        return listaAllGeneros;
    }
    
    
}
