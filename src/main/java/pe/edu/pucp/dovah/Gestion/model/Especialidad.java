/*
 * Nombre del archivo: Especialidad.java
 * Fecha de creacion: 20-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de la clase Especialidad
 */
package pe.edu.pucp.dovah.Gestion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int idEspecialidad;
    private String nombre;
    private String codigo;
    private String nombreCoordinador;
    private String descripcion;
    private boolean activo;
    @ManyToOne
    @JsonBackReference
    private Facultad facultad;

    @OneToMany
    @JsonManagedReference
    private List<Curso> cursos;

    protected Especialidad() {}

    public Especialidad(String nombre, String codigo, String nombreCoordinador,String descripcion){
        this.nombre = nombre;
        this.codigo = codigo;
        this.nombreCoordinador = nombreCoordinador;
        this.descripcion = descripcion;
        this.activo = true;
    }

    @Override
    public String toString() {
        return String.format("Especialidad(nombre='%s' codigo='%s' nombreCoordinador='%s')",
                this.getNombre(), this.getCodigo(),this.getNombreCoordinador());
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCoordinador() {
        return nombreCoordinador;
    }

    public void setNombreCoordinador(String nombreCoordinador) {
        this.nombreCoordinador = nombreCoordinador;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
