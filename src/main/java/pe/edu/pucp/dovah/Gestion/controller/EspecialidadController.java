/*
 * Nombre del archivo: EspecialidadController.java
 * Fecha de creacion: 21-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de los metodos usados para la clase Especialidad
 */
package pe.edu.pucp.dovah.Gestion.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.Gestion.exception.EspecialidadNotFoundException;
import pe.edu.pucp.dovah.Gestion.exception.FacultadNotFoundException;
import pe.edu.pucp.dovah.Gestion.model.Especialidad;
import pe.edu.pucp.dovah.Gestion.repository.EspecialidadRepository;
import pe.edu.pucp.dovah.Gestion.repository.FacultadRepository;

import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
public class EspecialidadController {

    private final EspecialidadRepository repository;

    private final FacultadRepository repositoryFacultad;
    private final static Logger log = LoggerFactory.getLogger(EspecialidadController.class);

    public EspecialidadController(EspecialidadRepository repository, FacultadRepository repositoryFacultad) {

        this.repository = repository;
        this.repositoryFacultad = repositoryFacultad;

    }
    /*Listar todos las especialidades*/
    @GetMapping("/especialidad")
    List<Especialidad> all(){
        return repository.findAll();
    }

    /*Buscar una especialidad*/
    @GetMapping("/especialidad/{id}")
    Especialidad obtenerEspecialidadPorId(@PathVariable int id){
        return repository.findById(id).orElseThrow(() -> new EspecialidadNotFoundException(id));
    }

    /*Eliminar una especialidad*/
    @DeleteMapping("/especialidad/{id}")
    void eliminarEspecialidad(@PathVariable int id){ repository.deleteById(id); }

    /*Insertar una especialidad*/
    @PostMapping("/especialidad")
    Especialidad nuevaEspecialidad(@RequestBody Map<String,Object> nuevaEspecialidad){
        log.info("Agregando especialidad");
        var json = new JSONObject(nuevaEspecialidad);
        var especialidad = new Especialidad(json.getString("codigo"),json.getString("nombre"),
                json.getString("nombreCoordinador"));

        return repository.save(especialidad);
    }
/*
    @PostMapping("/especialidad/agregarFacultad")
    Especialidad agregarFacultad(Map<String, Object>map){
        var json = new JSONObject(map);
        int idFacultad = json.getInt("idFacultad");
        int idEspecialidad = json.getInt("idEspecialidad");
        var especialidad = repository.findById(idEspecialidad).orElseThrow(
                                      ()-> new EspecialidadNotFoundException(idEspecialidad));
        var facultad = repositoryFacultad.findById(idFacultad)
                .orElseThrow(()->new FacultadNotFoundException(idFacultad));

        return especialidad;
    }

 */
}
