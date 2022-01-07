

package com.challange.disneyWorldApp.controllers;

// @author Franken

import com.challange.disneyWorldApp.entities.Genero;
import com.challange.disneyWorldApp.services.GeneroService;
import java.util.List;
import javax.validation.constraints.NotNull;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    
    @Autowired
    private GeneroService generoService;
    
    
    @PostMapping("/create")
    public ResponseEntity createGenero(@RequestBody Genero genero){
        generoService.crearGenero(genero);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @DeleteMapping("/delete/{generoid}")
    public ResponseEntity deleteGenero(
            @PathVariable("generoid") @NotNull()String generoid) throws Exception{
        generoService.borrarGenero(generoid);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @PutMapping
    public ResponseEntity updateGenero(@RequestBody Genero genero) throws Exception{
        generoService.updateGenero(genero);
        return ResponseEntity.status(HttpStatus.OK).build(); 
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Genero>> getAllGeneros(){
        return new ResponseEntity<>(generoService.getAll(),HttpStatus.OK);
   }
}
