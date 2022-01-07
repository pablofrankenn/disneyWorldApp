

package com.challange.disneyWorldApp.dto;




import lombok.Data;

@Data
public class PersonajeNombreImagenDto {
    
    private String nombre;
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    

}
