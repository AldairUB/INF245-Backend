package pe.edu.pucp.dovah.RRHH.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pe.edu.pucp.dovah.Gestion.model.Curso;
import pe.edu.pucp.dovah.Reglas.model.Rol;
import pe.edu.pucp.dovah.asignaciones.model.Documento;
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
    private String password;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActivacion;

    @OneToMany(mappedBy = "usuarioEntrega")
    List<Tarea> listaTareas;

    @ManyToMany (mappedBy = "listaUsuarios")
    @JsonBackReference
    private List<Curso> listaCursos;

    @OneToMany(mappedBy = "usuario")
    private List<Documento>listaDocumentos;

    @JsonManagedReference
    @ManyToMany
    List<Rol>listaRoles;

    protected Usuario() {}

    public Usuario(String nombre,
                   String apellido, char genero, String codigoPUCP,
                   String correo,String password) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.codigoPUCP = codigoPUCP;
        this.correo = correo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActivacion=LocalDateTime.now();
        this.password = password;

    }

    @Override
    public String toString() {

        return String.format("Usuario[id=%d,nombre='%s',apellido='%s',codigo='%s'",
                             idUsuario, nombre, apellido,codigoPUCP);
    }

    public int getIdUsuario() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }


    public List<Rol> getListaRoles() {
        return listaRoles;
    }
}
