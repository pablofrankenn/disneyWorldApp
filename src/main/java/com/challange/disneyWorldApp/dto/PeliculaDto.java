

package com.challange.disneyWorldApp.dto;




import com.challange.disneyWorldApp.entities.Genero;
import com.challange.disneyWorldApp.entities.Personaje;
import java.util.Calendar;
import java.util.List;
import lombok.Data;

@Data 
public class PeliculaDto {
private String id;
private String titulo;
private Calendar fechaCreacion;
private int calificacion;
private List<Personaje> personaje;
private String imagen;
private Genero genero;
}
