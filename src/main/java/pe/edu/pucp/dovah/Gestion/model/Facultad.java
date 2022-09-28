/*
 * Nombre del archivo: Facultad.java
 * Fecha de creacion: 20-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de la clase Facultad
 */
package pe.edu.pucp.dovah.Gestion.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int idFacultad;
    private String nombre;
    private String decano;
    private boolean activo;
    private int anhoFundacion;
    //@Transient
    @OneToMany
    @JsonManagedReference
    private List<Especialidad> especialidades;

    protected Facultad() {}

    public Facultad(String nombre, String decano, int anhoFundacion){
        this.nombre = nombre;
        this.decano = decano;
        this.anhoFundacion = anhoFundacion;
        this.activo = true;
    }

    @Override
    public String toString() {
        return String.format("Facultad(nombre='%s' decano='%s' anhoFundacion='%d')", this.getNombre(),
                this.getDecano(),this.getAnhoFundacion());
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDecano() {
        return decano;
    }

    public void setDecano(String decano) {
        this.decano = decano;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getAnhoFundacion() {
        return anhoFundacion;
    }

    public void setAnhoFundacion(int anhoFundacion) {
        this.anhoFundacion = anhoFundacion;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
}
