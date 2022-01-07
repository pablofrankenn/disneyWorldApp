
package com.challange.disneyWorldApp.entities;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

// @author Franken

@Entity
public class Pelicula {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String titulo;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaCreacion;
    
    private String calificacion;
    
    @ManyToMany
    @JoinTable(
    name = "personaje_pelicula", 
    joinColumns = @JoinColumn(name = "pelicula_id"), 
    inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personaje;
    

    private String imagen;
    
    @ManyToOne
    private Genero genero;

    public Pelicula() {
    }

    public Pelicula(String id, String titulo, Calendar fechaCreacion, String calificacion, List<Personaje> personaje, String imagen, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personaje = personaje;
        this.imagen = imagen;
        this.genero = genero;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonaje() {
        return personaje;
    }

    public void setPersonaje(List<Personaje> personaje) {
        this.personaje = personaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
        
}
