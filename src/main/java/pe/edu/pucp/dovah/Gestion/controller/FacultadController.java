/*
 * Nombre del archivo: FacultadController.java
 * Fecha de creacion: 21-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de los metodos usados para la clase Facultad
 */
package pe.edu.pucp.dovah.Gestion.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.Gestion.exception.FacultadNotFoundException;
import pe.edu.pucp.dovah.Gestion.model.Facultad;
import pe.edu.pucp.dovah.Gestion.repository.FacultadRepository;
import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
public class FacultadController {

    private final FacultadRepository repository;
    private final static Logger log = LoggerFactory.getLogger(FacultadController.class);

    public FacultadController(FacultadRepository repository) {
        this.repository = repository;
    }

    /*Listar todos las facultades*/
    @GetMapping("/facultad")
    List<Facultad> all(){

        return repository.listarActivos();

    }

    /*Buscar una facultad*/
    @GetMapping("/facultad/{id}")
    Facultad obtenerFacultadPorId(@PathVariable int id){
        return repository.findById(id).orElseThrow(() -> new FacultadNotFoundException(id));
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

}
