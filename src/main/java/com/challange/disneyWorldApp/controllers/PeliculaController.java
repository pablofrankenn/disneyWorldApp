

package com.challange.disneyWorldApp.controllers;

// @author Franken

import com.challange.disneyWorldApp.dto.PeliculaCreateDto;
import com.challange.disneyWorldApp.dto.PeliculaDto;
import com.challange.disneyWorldApp.dto.PeliculaImagenTituloFechaDto;
import com.challange.disneyWorldApp.repositories.PeliculaRepository;
import com.challange.disneyWorldApp.services.PeliculaService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/pelicula")
public class PeliculaController {
    
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private PeliculaRepository peliculaRepository;
    
  @ApiOperation(value= "Crear Pelicula")
  @PostMapping("/create")
    public ResponseEntity createPeliucla(@RequestBody PeliculaCreateDto peliculaCreateDto){
        peliculaService.crearPelicula(peliculaCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @ApiOperation(value="Borrar pelicula")
    @DeleteMapping("/delete/{peliculaid}")
    public ResponseEntity deletePelicula(
                    @PathVariable("peliculaid")@NotNull() String peliculaid){
        peliculaService.borrarPelicula(peliculaid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @ApiOperation(value="Actualizar pelicula")
    @PutMapping("/update")
    public ResponseEntity updatePelicula(@RequestBody(required=true)@Valid PeliculaDto peliculaDto) throws Exception{
        peliculaService.actualizarPelicula(peliculaDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    

//7.Listado de Peliculas   
    @ApiOperation(value="Mostrar todas las peliculas de BD")
    @GetMapping("")
    public ResponseEntity<List<PeliculaImagenTituloFechaDto>> getAllMovies(){
        return new ResponseEntity<>(peliculaService.getAllPeliculas(),HttpStatus.OK);
    }
    
    
    
//8.Detallle de la Pelicula
    @ApiOperation(value="Buscar pelicula por ID")
   @GetMapping("/read/{movieid}")
   public ResponseEntity<PeliculaDto> readMovie(
                @PathVariable(name="movieid",required=true)
                @NotNull(message = "El parametro id no puede estar vacio")
                String movieid){
    return ResponseEntity.ok(peliculaService.readPelicula(movieid));
   }
   
   //10. Busqueda de Pelicula o Series
  @ApiOperation(value="Buscar pelicula por nombre, genero o las ordena por fecha de creacion")
   @GetMapping("/movies")
    
   public ResponseEntity<List<PeliculaDto>> getMovies(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) String order) { 
   return ResponseEntity.ok(peliculaService.movieFinder(name, genre, order));
  }
   
   
}
