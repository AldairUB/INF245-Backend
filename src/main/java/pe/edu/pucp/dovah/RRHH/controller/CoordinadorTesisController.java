/*
 * Nombre del archivo: CoordinadorTesisController
 * Fecha de creación: 2/10/2022 , 08:04
 * Autor: Lloyd Erwin Castillo Ramos
 * Descripción:
 */
package pe.edu.pucp.dovah.RRHH.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.RRHH.exceptions.UsuarioNotFoundException;
import pe.edu.pucp.dovah.RRHH.model.CoordinadorTesis;
import pe.edu.pucp.dovah.RRHH.model.Profesor;
import pe.edu.pucp.dovah.RRHH.repository.CoordinadorTesisRepository;

import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
public class CoordinadorTesisController {
    private final CoordinadorTesisRepository coordinadorTesisRepository;
    private final static Logger log = LoggerFactory.getLogger(CoordinadorTesisController.class);
    public CoordinadorTesisController(CoordinadorTesisRepository coordinadorTesisRepository){

        this.coordinadorTesisRepository = coordinadorTesisRepository;

    }

    @GetMapping("/coordinadorTesis")
    List<CoordinadorTesis> all(){

        return coordinadorTesisRepository.queryAllByActivoIsTrue();

    }

    @GetMapping("/coordinadorTesis/{id}")
    CoordinadorTesis obtenerProfesorPorId(@PathVariable int id){

        return coordinadorTesisRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

    }

    @PostMapping("/coordinadorTesis/eliminar")
    CoordinadorTesis eliminarProfesor(@RequestBody Map<String, Object> map){

        var json = new JSONObject(map);
        int id = json.getInt("idCoordinador");
        var coordinador = coordinadorTesisRepository.findById(id).orElseThrow(()
                ->new UsuarioNotFoundException(id));
        log.info(String.format("Eliminado coordinado de tesis con id '%d'",
                coordinador.getIdUsuario()));
        coordinador.setActivo(false);
        return coordinadorTesisRepository.save(coordinador);
    }

    @PostMapping("/coordinadorTesis")
    CoordinadorTesis nuevoCoordinadorTesis(@RequestBody Map<String,Object> nuevoCoordinador){

        log.info("Agregando coordinador de tesis");
        var json = new JSONObject(nuevoCoordinador);
        var coordinador = new CoordinadorTesis(json.getString("nombre"),json.getString("apellido"),
                json.getString("genero").charAt(0),json.getString("codigoPUCP"),
                json.getString("correo"),json.getString("password"));


        return coordinadorTesisRepository.save(coordinador);

    }

    @PostMapping("/coordinadorTesis/modificar")
    CoordinadorTesis modificarCoordinador(@RequestBody Map<String, Object>map){

        var json = new JSONObject(map);
        int id = json.getInt("idCoordinador");
        var coordinador = coordinadorTesisRepository.findById(id).orElseThrow(()->new UsuarioNotFoundException(id));
        log.info(String.format("Eliminado profesor con id '%d'",
                coordinador.getIdUsuario()));
        var coordinadorAux = new CoordinadorTesis(json.getString("nombre"),json.getString("apellido"),
                json.getString("genero").charAt(0),json.getString("codigoPUCP"),
                json.getString("correo"),json.getString("password"));

        coordinador.setGenero(coordinadorAux.getGenero());
        coordinador.setNombre(coordinadorAux.getNombre());
        coordinador.setApellido(coordinadorAux.getApellido());
        coordinador.setCorreo(coordinadorAux.getCorreo());
        coordinador.setCodigoPUCP(coordinadorAux.getCodigoPUCP());
        coordinador.setPassword(coordinadorAux.getPassword());
        return coordinadorTesisRepository.save(coordinador);
    }

}
