

package com.challange.disneyWorldApp.dto;




import java.util.Calendar;
import lombok.Data;


@Data
public class PeliculaImagenTituloFechaDto {
private String imagen;
private String titulo;
private Calendar fechaCreacion;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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


}
