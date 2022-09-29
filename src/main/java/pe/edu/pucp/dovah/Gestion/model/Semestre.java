/*
 * Nombre del archivo: Semestre.java
 * Fecha de creacion: 20-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de la clase Semestre
 */
package pe.edu.pucp.dovah.Gestion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSemestre;
    private String anhoAcademico;
    private String periodo;

    private String fechaInicio;

    private String fechaFin;

    private String fechaCierreNotasParcial;

    private String fechaCierreNotasFinal;
    private boolean activo;

    @OneToMany
    @JsonManagedReference
    private List<Curso> cursos;

    protected Semestre() {}

    public Semestre(String anhoAcademico, String periodo,String fechaInicio,String fechaFin,
                    String fechaCierreNotasParcial, String fechaCierreNotasFinal){
        this.anhoAcademico = anhoAcademico;
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaCierreNotasParcial = fechaCierreNotasParcial;
        this.fechaCierreNotasFinal = fechaCierreNotasFinal;
        this.activo = true;
    }
    @Override
    public String toString() {
        return String.format("Semestre(anhoAcademico='%s' periodo='%s')",
                this.getAnhoAcademico(),this.getPeriodo());
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getAnhoAcademico() {
        return anhoAcademico;
    }

    public void setAnhoAcademico(String anhoAcademico) {
        this.anhoAcademico = anhoAcademico;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaCierreNotasParcial() {
        return fechaCierreNotasParcial;
    }

    public void setFechaCierreNotasParcial(String fechaCierreNotasParcial) {
        this.fechaCierreNotasParcial = fechaCierreNotasParcial;
    }

    public String getFechaCierreNotasFinal() {
        return fechaCierreNotasFinal;
    }

    public void setFechaCierreNotasFinal(String fechaCierreNotasFinal) {
        this.fechaCierreNotasFinal = fechaCierreNotasFinal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
