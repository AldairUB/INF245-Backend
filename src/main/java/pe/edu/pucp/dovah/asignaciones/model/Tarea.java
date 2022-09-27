/**
 * Nombre del archivo: Tarea.java
 * Fecha de creacion: 20/09/2022
 * Autor: Carlos Toro
 * Descripcion: Clase que implementa el modelo de la base de datos
 */

package pe.edu.pucp.dovah.asignaciones.model;

import pe.edu.pucp.dovah.RRHH.model.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    private int nota;
    private LocalDateTime fechaCreacion;

    @ManyToMany(mappedBy = "listaTareas")
    List<Usuario> listaUsuarios;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Documento> listaDocumentos;

    protected Tarea() {}

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.nota = 0;
        this.fechaCreacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Tarea(descripcion='%s' nota='%d')", this.getDescripcion(), this.getNota());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }
}
