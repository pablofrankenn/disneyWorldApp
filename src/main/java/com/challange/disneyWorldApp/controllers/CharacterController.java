

package com.challange.disneyWorldApp.controllers;

// @author Franken

import com.challange.disneyWorldApp.dto.PersonajeCreateDto;
import com.challange.disneyWorldApp.dto.PersonajeDto;
import com.challange.disneyWorldApp.dto.PersonajeNombreImagenDto;
import com.challange.disneyWorldApp.exceptions.ErrorService;
import com.challange.disneyWorldApp.services.PersonajeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/characters")
@Api(value="Servicios para  gestionar personajes",tags = "personajes")
public class CharacterController {
    
    @Autowired
    private PersonajeService personajeService;
    
   
    
    // 3.Listado de Personajes
    @ApiOperation(value="Devolver todos los personajes de la BD")
    @GetMapping("/all")
    public ResponseEntity<List<PersonajeNombreImagenDto>> getCharacters(){   
        return new ResponseEntity<>(personajeService.getAllPersonajes(),HttpStatus.OK);
    }
    
    //4. CRUD
    
    @ApiOperation(value="Crear personaje" )
    @PostMapping("/create")
    public ResponseEntity createPersonaje(@RequestBody  PersonajeCreateDto personajeCreateDto) throws ErrorService{
        personajeService.crearPersonaje(personajeCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @ApiOperation(value="Borrar un personaje")
    @DeleteMapping("/delete/{personajeid}")
    public ResponseEntity deleteCharacter(
            @PathVariable("personajeid")@NotNull() String personajeid){
        personajeService.borrarPersonaje(personajeid);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @ApiOperation(value="Actualizar un personaje")
    @PutMapping("/update")
    public ResponseEntity updateCharacter(@RequestBody(required=true) @Valid PersonajeDto personajeDto) throws Exception{
        personajeService.actualizarPersonaje(personajeDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    // 5.Detalle de Personaje
     @ApiOperation(value="Devolver un personaje con sus detalles")
    @GetMapping("/read/{characterid}")
    public ResponseEntity<PersonajeDto> readCharacter(
                @PathVariable(name="characterid", required=true)
                @NotNull(message = "El parametro id de personaje no puede estar vacío")
                String personajeid){    
    return ResponseEntity.ok(personajeService.readPersonaje(personajeid));
    }
    
    // 6.Busqueda de Personajes
   
   
    @ApiOperation(value="Devolver personajes con determinada edad, nombre o pelicula")
    @GetMapping("/")
    public ResponseEntity<List<PersonajeDto>> getCharacters(
            @RequestParam(required = false) String name,
        @RequestParam(required = false) String age,
        @RequestParam(required = false) String movies){
        
       
    return ResponseEntity.ok( personajeService.getCharacters(name, age, movies));
}
    
    
    
}
