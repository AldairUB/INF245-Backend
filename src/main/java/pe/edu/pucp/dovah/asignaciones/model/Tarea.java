/**
 * Nombre del archivo: Tarea.java
 * Fecha de creacion: 20/09/2022
 * Autor: Carlos Toro
 * Descripcion: Clase que implementa el modelo de la base de datos
 */

package pe.edu.pucp.dovah.asignaciones.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import pe.edu.pucp.dovah.Gestion.model.Curso;
import pe.edu.pucp.dovah.RRHH.model.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    private int nota;
    private LocalDateTime fechaCreacion;

    boolean activo;
    boolean visible;

    @ManyToMany
    List<Usuario> listaUsuariosCorrigen;

    @ManyToOne
    Usuario usuarioEntrega;

    @JsonManagedReference
    @ManyToMany
    List<Documento> listaDocumentos;

    @OneToOne
    Tarea tareaPadre;

    @ManyToOne
    Curso curso;

    protected Tarea() {}

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.nota = 0;
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
        this.visible = false;
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

    public List<Documento> getListaDocumentos() {
        return listaDocumentos;
    }

    public Tarea getTareaPadre() {
        return tareaPadre;
    }

    public void setTareaPadre(Tarea tareaPadre) {
        this.tareaPadre = tareaPadre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Usuario> getListaUsuariosCorrigen() {
        return listaUsuariosCorrigen;
    }

    public void setListaUsuariosCorrigen(List<Usuario> listaUsuariosCorrigen) {
        this.listaUsuariosCorrigen = listaUsuariosCorrigen;
    }

    public Usuario getUsuarioEntrega() {
        return usuarioEntrega;
    }

    public void setUsuarioEntrega(Usuario usuarioEntrega) {
        this.usuarioEntrega = usuarioEntrega;
    }

    public void setListaDocumentos(List<Documento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }
}
