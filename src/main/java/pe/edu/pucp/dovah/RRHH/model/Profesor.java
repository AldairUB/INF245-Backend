package pe.edu.pucp.dovah.RRHH.model;

import pe.edu.pucp.dovah.asignaciones.model.Tarea;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*
 * Nombre del archivo: Profesor
 * Fecha de creación: 20/09/2022 , 18:00
 * Autor: Lloyd Castillo Ramos
 * Descripción: Clase profesor
 */
@Entity
@PrimaryKeyJoinColumn(name = "idProfesor")
public class Profesor extends Usuario {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String urlDisponibilidad;
    private boolean activo;

    @ManyToMany(mappedBy = "listaUsuariosCorrigen")
    List<Tarea> listaTareas;

    protected Profesor() {}

    public Profesor(String nombre, String apellido, char genero, String codigoPUCP, String correo, String urlDisponibilidad,
                    String password) {

        super(nombre, apellido, genero, codigoPUCP, correo,password);
        this.urlDisponibilidad = urlDisponibilidad;
        this.activo = true;

    }

    public String getUrlDisponibilidad() {
        return urlDisponibilidad;
    }

    public void setUrlDisponibilidad(String urlDisponibilidad) {
        this.urlDisponibilidad = urlDisponibilidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
