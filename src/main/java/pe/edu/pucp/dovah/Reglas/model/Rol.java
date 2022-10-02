package pe.edu.pucp.dovah.Reglas.model;/*
 * Nombre del archivo: Rol
 * Fecha de creación: 1/10/2022 , 07:06
 * Autor: Lloyd Castillo Ramos
 * Descripción:
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pe.edu.pucp.dovah.RRHH.model.Usuario;

import javax.crypto.Mac;
import javax.persistence.*;
import java.util.List;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRol;
    private String nombre;
    @JsonBackReference
    @ManyToMany(mappedBy = "listaRoles")
    List<Usuario> listaUsuarios;

    protected  Rol(){}

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

}
