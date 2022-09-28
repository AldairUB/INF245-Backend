/*
 * Nombre del archivo: FacultadController.java
 * Fecha de creacion: 21-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de los metodos usados para la clase Facultad
 */
package pe.edu.pucp.dovah.Gestion.controller;

import org.hibernate.annotations.SQLUpdate;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.Gestion.exception.EspecialidadNotFoundException;
import pe.edu.pucp.dovah.Gestion.exception.FacultadNotFoundException;
import pe.edu.pucp.dovah.Gestion.model.Especialidad;
import pe.edu.pucp.dovah.Gestion.model.Facultad;
import pe.edu.pucp.dovah.Gestion.repository.EspecialidadRepository;
import pe.edu.pucp.dovah.Gestion.repository.FacultadRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
public class FacultadController {

    private final FacultadRepository repository;
    private final EspecialidadRepository repositoryEsp;
    private final static Logger log = LoggerFactory.getLogger(FacultadController.class);

    public FacultadController(FacultadRepository repositoryFac, EspecialidadRepository repositoryEsp) {

        this.repository = repositoryFac;
        this.repositoryEsp = repositoryEsp;

    }

    /*Listar todos las facultades*/
    @GetMapping("/facultad")
    List<Facultad> all(){

        return repository.listarActivos();

    }

    /*Buscar una facultad*/
    @GetMapping("/facultad/{id}")
    Facultad obtenerFacultadPorId(@PathVariable int id){
        return repository.findByIdFacultad(id).orElseThrow(() -> new FacultadNotFoundException(id));
    }

    /*Eliminar una facultad*/
    @DeleteMapping("/facultad/{id}")
    void eliminarFacultad(@PathVariable int id){ repository.deleteById(id); }

    /*Insertar una facultad*/
    @PostMapping("/facultad")
    Facultad nuevaFacultad(@RequestBody Map<String,Object> nuevaFacultad){
        log.info("Agregando facultad");
        var json = new JSONObject(nuevaFacultad);
        var facultad = new Facultad(json.getString("nombre"),json.getString("decano"),
                json.getInt("anhoFundacion"));

        return repository.save(facultad);
    }


    @PostMapping("/facultad/agregarEspecialidad")
    Facultad agregarEspecialidad(@RequestBody Map<String, Object> map){

        var json = new JSONObject(map);
        int idFacultad = json.getInt("idFacultad");
        int idEspecialidad = json.getInt("idEspecialidad");
        var facultad = repository.findByIdFacultad(idFacultad).orElseThrow(()->
                                new FacultadNotFoundException(idFacultad));
        var esp = repositoryEsp.findById(idEspecialidad)
                                .orElseThrow(()-> new EspecialidadNotFoundException(idEspecialidad));
        facultad.getEspecialidades().add(esp);
        esp.setFacultad(facultad);
        repositoryEsp.save(esp);
        return repository.save(facultad);
    }


}
