/*
* Nombre del archivo: Curso.java
* Fecha de creacion: 20-09-2022
* Autor: Victor Avalos
* Descripción: Definición de la clase Curso
*/
package pe.edu.pucp.dovah.Gestion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pe.edu.pucp.dovah.RRHH.model.Alumno;
import pe.edu.pucp.dovah.RRHH.model.Usuario;
import pe.edu.pucp.dovah.asignaciones.model.Tarea;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*Clave, Nombre del Curso, Ciclo(Semestre?), Descripcion, Coordinador del Curso, Lista de Profesores, Lista de Alumnos */
    private int idCurso;
    private String clave;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaFinalizacion;
    private boolean activo;

    @ManyToOne
    @JsonBackReference
    private Especialidad especialidad;

    @ManyToOne
    @JsonBackReference
    private Semestre semestre;

    @OneToMany
    @JsonManagedReference
    private List<Tarea> tareas;

    @ManyToMany
    @JsonManagedReference
    private List<Usuario> listaUsuarios;


    protected Curso() {}

    public Curso(String clave, String nombre){
        this.clave = clave;
        this.nombre = nombre;
        this.activo = true;
    }

    @Override
    public String toString() {
        return String.format("Curso(clave='%s', nombre='%s')", this.getClave(), this.getNombre());
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public List<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    public void setUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
}
