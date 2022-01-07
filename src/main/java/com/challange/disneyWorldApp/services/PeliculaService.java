

package com.challange.disneyWorldApp.services;



import com.challange.disneyWorldApp.dto.PeliculaCreateDto;
import com.challange.disneyWorldApp.dto.PeliculaDto;
import com.challange.disneyWorldApp.dto.PeliculaImagenTituloFechaDto;
import com.challange.disneyWorldApp.entities.Genero;
import com.challange.disneyWorldApp.entities.Pelicula;
import com.challange.disneyWorldApp.repositories.PeliculaRepository;
import com.challange.disneyWorldApp.repositories.GeneroRepository;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service 
public class PeliculaService {
    
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private GeneroRepository generoRepository;
    
//9. Creacion, Edicion y eliminacion de Pelicula
    @Transactional
     public void crearPelicula(PeliculaCreateDto peliculaCreateDto){
        Pelicula pelicula= new Pelicula();
        pelicula.setTitulo(peliculaCreateDto.getTitulo());
        pelicula.setFechaCreacion(Calendar.getInstance());
        pelicula.setCalificacion(peliculaCreateDto.getCalificacion());
        pelicula.setImagen(peliculaCreateDto.getImagen());
        Optional<Genero> optional= generoRepository.findById(peliculaCreateDto.getGeneroid());
        if (optional.isPresent()) {
            pelicula.setGenero(generoRepository.getOne(peliculaCreateDto.getGeneroid()));
        }
        peliculaRepository.save(pelicula);
    }
    
    @Transactional
    public void borrarPelicula(String peliculaid){
        try{
            Optional<Pelicula> pelicula= peliculaRepository.findById(peliculaid);
            peliculaRepository.delete(pelicula.get());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public PeliculaDto readPelicula(String movieid){
        ModelMapper modelMapper= new ModelMapper();
        PeliculaDto peliculaDto= modelMapper.map(peliculaRepository.findById(movieid), PeliculaDto.class);
        return peliculaDto;
    }
    
    public List<PeliculaImagenTituloFechaDto> getAllPeliculas(){
        return peliculaRepository.findAll()
                .stream()
                .map(this::convertPeliculaToDto)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void actualizarPelicula(PeliculaDto peliculaDto) throws Exception{
        ModelMapper modelMapper= new ModelMapper();  
        Pelicula pelicula= modelMapper.map(peliculaDto, Pelicula.class);
        Optional<Pelicula> optional =peliculaRepository.findById(pelicula.getId());
        if (optional.isPresent()) {
              peliculaRepository.save(pelicula);
        }else{
            throw new Exception("No existe personaje con ese ID");
        }
     }
       
    public PeliculaImagenTituloFechaDto convertPeliculaToDto(Pelicula pelicula){
        PeliculaImagenTituloFechaDto peliculaImagenTituloFechaDto= new PeliculaImagenTituloFechaDto();
        peliculaImagenTituloFechaDto.setImagen(pelicula.getImagen());
        peliculaImagenTituloFechaDto.setFechaCreacion(pelicula.getFechaCreacion());
        peliculaImagenTituloFechaDto.setTitulo(pelicula.getTitulo());
        return peliculaImagenTituloFechaDto;
    }
    
    public List<PeliculaDto> findMoviesByName(String nombre){
        ModelMapper modelMapper= new ModelMapper();
        List<Pelicula> listaPeliculas= peliculaRepository.findByTitulo(nombre);
         Type listType = new TypeToken<List<PeliculaDto>>(){}.getType();
         List<PeliculaDto> listPeliculaDto= modelMapper.map(listaPeliculas, listType);
         return listPeliculaDto;
        
    }
     public List<PeliculaDto> findMoviesByGenre(String generoid){
        ModelMapper modelMapper= new ModelMapper();
        List<Pelicula> listaPeliculas= peliculaRepository.findByGenero(generoid);
         Type listType = new TypeToken<List<PeliculaDto>>(){}.getType();
         List<PeliculaDto> listPeliculaDto= modelMapper.map(listaPeliculas, listType);
         return listPeliculaDto;
    }
     public List<PeliculaDto> findMoviesByOrder(String order){
         ModelMapper modelMapper= new ModelMapper();
         Type listType = new TypeToken<List<PeliculaDto>>(){}.getType();
         if (order.equals("DESC")) {
             List<Pelicula> listaPeliculas= peliculaRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaCreacion"));
              List<PeliculaDto> listPeliculaDto= modelMapper.map(listaPeliculas, listType);
             return listPeliculaDto;
         } else{
              List<Pelicula> listaPeliculas= peliculaRepository.findAll(Sort.by(Sort.Direction.ASC, "fechaCreacion"));
              List<PeliculaDto> listPeliculaDto= modelMapper.map(listaPeliculas, listType);
             return listPeliculaDto;
         }
     }
     
     public List<PeliculaDto> movieFinder(String name, String genre, String order){
          if (name!=null) {
          List<PeliculaDto> listPeliculasDto= findMoviesByName(name); 
          return listPeliculasDto;
       }
       if (genre!=null) {
           
           List<PeliculaDto> listPeliculasDto= findMoviesByGenre(genre);
            return listPeliculasDto;
       }
      
       if (order.equals("DESC") || order.equals("ASC")) {
           
            List<PeliculaDto> listPeliculasDto= findMoviesByOrder(order);
            return listPeliculasDto;
       }
       return null;
     }
}
