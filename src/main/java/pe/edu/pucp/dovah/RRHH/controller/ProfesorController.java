package pe.edu.pucp.dovah.RRHH.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.RRHH.exceptions.UsuarioNotFoundException;
import pe.edu.pucp.dovah.RRHH.model.Administrador;
import pe.edu.pucp.dovah.RRHH.model.Profesor;
import pe.edu.pucp.dovah.RRHH.repository.AdministradorRepository;
import pe.edu.pucp.dovah.RRHH.repository.ProfesorRepository;
import pe.edu.pucp.dovah.asignaciones.model.Tarea;

import java.util.List;
import java.util.Map;

/*
 * Nombre del archivo: ProfesorController
 * Fecha de creación: 20/09/2022 , 18:00
 * Autor: Lloyd Castillo Ramos
 * Descripción: Clase que maneja el controlador de la clase profesor
 */
@BasePathAwareController
@RestController
public class ProfesorController {
    private final ProfesorRepository repository;
    private final static Logger log = LoggerFactory.getLogger(ProfesorController.class);

    public ProfesorController(ProfesorRepository repository) {

        this.repository = repository;

    }

    /*
        Listar todos los profesores
    */

    @GetMapping("/profesor")
    List<Profesor> all(){

        return repository.queryAllByActivoIsTrue();

    }

    /*
        Listar un profesor en especifico
    */

    @GetMapping("/profesor/{id}")
    Profesor obtenerProfesorPorId(@PathVariable int id){

        return repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

    }



    @DeleteMapping("/profesor/{id}")
    void eliminarProfesor(@PathVariable int id){

        repository.deleteById(id);

    }
    /*
        Insertar nuevo profesor
    */

    @PostMapping("/profesor")
    Profesor nuevoProfesor(@RequestBody Map<String,Object> nuevoProfesor){

        log.info("Agregando profesor");
        var json = new JSONObject(nuevoProfesor);
        var profesor = new Profesor(json.getString("nombre"),json.getString("apellido"),
                                    json.getString("genero").charAt(0),json.getString("codigoPUCP"),
                                    json.getString("correo"),json.getString("urlDisponibilidad"),
                                    json.getString("password"));


        return repository.save(profesor);

    }

    @GetMapping("/profesor/buscarPorCodigo/{codigo}")
    List<Profesor> listarProfesores(@PathVariable String codigo){

        return repository.findByCodigoPUCPContainingAndActivoIsTrue(codigo);


    }

}
