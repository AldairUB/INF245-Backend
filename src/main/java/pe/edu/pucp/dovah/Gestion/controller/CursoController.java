/*
 * Nombre del archivo: CursoController.java
 * Fecha de creacion: 21-09-2022
 * Autor: Victor Avalos
 * Descripción: Definición de los metodos usados para la clase Curso
 */
package pe.edu.pucp.dovah.Gestion.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.Gestion.model.Curso;
import pe.edu.pucp.dovah.Gestion.repository.CursoRepository;
import pe.edu.pucp.dovah.RRHH.model.Administrador;

import java.util.List;

@BasePathAwareController
@RestController
public class CursoController {

    private final CursoRepository repository;

    private final static Logger log = LoggerFactory.getLogger(CursoController.class);

    public CursoController(CursoRepository repository) {
        this.repository = repository;
    }
    /*

        Listar todos los cursos activos

    */
    @GetMapping("/curso")
    List<Curso> all(){

        return repository.queryAllByActivoIsTrue();

    }


}
