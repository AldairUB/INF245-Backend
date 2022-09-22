package pe.edu.pucp.dovah.RRHH.model;

import pe.edu.pucp.dovah.asignaciones.model.Tarea;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
/*
 * Nombre del archivo: Usuario
 * Fecha de creación: 20/09/2022 , 18:00
 * Autor: Lloyd Castillo Ramos
 * Descripción: Clase usuario
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;
    private String nombre;
    private String apellido;
    private char genero;
    private String codigoPUCP;
    private String correo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActivacion;

    @ManyToMany
    private List<Tarea> listaTareas;

    protected Usuario() {}

    public Usuario(String nombre,
                   String apellido, char genero, String codigoPUCP,
                   String correo) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.codigoPUCP = codigoPUCP;
        this.correo = correo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActivacion=LocalDateTime.now();

    }

    @Override
    public String toString() {

        return String.format("Usuario[id=%d,nombre='%s',apellido='%s',codigo='%s'",
                             idUsuario, nombre, apellido,codigoPUCP);
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getCodigoPUCP() { return codigoPUCP;}

    public void setCodigoPUCP(String codigoPUCP) {
        this.codigoPUCP = codigoPUCP;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(LocalDateTime fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

}
