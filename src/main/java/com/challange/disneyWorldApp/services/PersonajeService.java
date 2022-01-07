

package com.challange.disneyWorldApp.services;



import com.challange.disneyWorldApp.dto.PersonajeCreateDto;
import com.challange.disneyWorldApp.dto.PersonajeDto;
import com.challange.disneyWorldApp.dto.PersonajeNombreImagenDto;
import com.challange.disneyWorldApp.entities.Pelicula;
import com.challange.disneyWorldApp.entities.Personaje;
import com.challange.disneyWorldApp.repositories.PeliculaRepository;
import com.challange.disneyWorldApp.repositories.PersonajeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
     @Autowired
     private PeliculaRepository peliculaRepository;
   
    @Transactional
   public void crearPersonaje(PersonajeCreateDto personajeCreateDto){
        Personaje personaje=  new Personaje();
        personaje.setNombre(personajeCreateDto.getNombre());
        personaje.setEdad(personajeCreateDto.getEdad());
        personaje.setPeso(personajeCreateDto.getPeso());
        personaje.setHistoria(personajeCreateDto.getHistoria());
        personaje.setImagen(personajeCreateDto.getImagen());
        
        if (!personajeCreateDto.getPeliculaid().isEmpty()) {
            List<Pelicula> listaPeliculas= new ArrayList();
            for (String object : personajeCreateDto.getPeliculaid()) {
                Optional<Pelicula> pelicula= peliculaRepository.findById(object);
                if (pelicula.isPresent()) {
                    listaPeliculas.add(peliculaRepository.getOne(object));
                }
            }
         personaje.setPeliculas(listaPeliculas);   
        }
        personajeRepository.save(personaje);
    }
    @Transactional
    public void borrarPersonaje(String personajeid){
        try{
            Optional<Personaje> personaje=personajeRepository.findById(personajeid);
            personajeRepository.delete(personaje.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    @Transactional
    public void actualizarPersonaje(PersonajeDto personajeDto) throws Exception{
        ModelMapper modelMapper= new ModelMapper();  
        Personaje personaje= modelMapper.map(personajeDto, Personaje.class);
        Optional<Personaje> optional =personajeRepository.findById(personaje.getId());
        if (optional.isPresent()) {
              personajeRepository.save(personaje);
        }else{
            throw new Exception("No existe personaje con ese ID");
        }
     }
       
     
    public PersonajeDto readPersonaje(String id){
        ModelMapper modelMapper= new ModelMapper(); 
        PersonajeDto personajeDto= modelMapper.map(personajeRepository.getOne(id), PersonajeDto.class);
        return personajeDto;
    } 
    
 
    
    public List<PersonajeNombreImagenDto> getAllPersonajes(){
        return personajeRepository.findAll()
                .stream()
                .map(this::convertPersonajeToDto)
                .collect(Collectors.toList());
    }
    
    private PersonajeNombreImagenDto convertPersonajeToDto(Personaje personaje){
        PersonajeNombreImagenDto personajeDto= new PersonajeNombreImagenDto();
        personajeDto.setNombre(personaje.getNombre());
        personajeDto.setImagen(personaje.getImagen());
        return personajeDto;
    }
    
    private PersonajeDto convertPersonajeToDto2(Personaje personaje){
        PersonajeDto personajeDto= new PersonajeDto();
        personajeDto.setNombre(personaje.getNombre());
        personajeDto.setEdad(personaje.getEdad());
        personajeDto.setHistoria(personaje.getHistoria());
        personajeDto.setImagen(personaje.getImagen());
        personajeDto.setId(personaje.getId());
        personajeDto.setPeso(personaje.getPeso());
        personajeDto.setPeliculas(personaje.getPeliculas());
        return personajeDto;
    }
    
   
    
   
    public List<PersonajeDto> getCharacters(String name, String age, String movies){
        if (name!=null) {
        List<Personaje> listPersonajes=personajeRepository.buscarPorNombreList(name); 
        List<PersonajeDto> listPersonajesDto=listPersonajes.stream().map(this::convertPersonajeToDto2)
                .collect(Collectors.toList());
        return listPersonajesDto;
       }
       if (age!=null) {
           
            List<Personaje> listPersonajes= personajeRepository.buscarPorEdadList(age);
           List<PersonajeDto> listPersonajesDto=listPersonajes.stream().map(this::convertPersonajeToDto2)
                .collect(Collectors.toList());
            return listPersonajesDto;
       }
       if (movies!=null) {
           Optional<Pelicula> optional= peliculaRepository.findById(movies);
           if (optional.isPresent()) {
           List<Personaje> listPersonajes=  peliculaRepository.getOne(movies).getPersonaje();
           List<PersonajeDto> listPersonajesDto= listPersonajes.stream().map(this::convertPersonajeToDto2)
                   .collect(Collectors.toList());
           return listPersonajesDto;
           }
        }
       
       return null; 
        
    }
}

