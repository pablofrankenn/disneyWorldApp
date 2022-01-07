

package com.challange.disneyWorldApp.dto;



import java.util.List;
import lombok.Data;

 @Data
public class PersonajeCreateDto {
private String nombre;
    private String edad;
    private String peso;
    private String historia;
    private List<String> peliculaid;
    private String imagen;
}
