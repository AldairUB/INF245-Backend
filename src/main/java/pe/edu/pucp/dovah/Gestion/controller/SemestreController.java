package pe.edu.pucp.dovah.Gestion.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.Gestion.exception.FacultadNotFoundException;
import pe.edu.pucp.dovah.Gestion.exception.SemestreNotFoundException;
import pe.edu.pucp.dovah.Gestion.model.Especialidad;
import pe.edu.pucp.dovah.Gestion.model.Facultad;
import pe.edu.pucp.dovah.Gestion.model.Semestre;
import pe.edu.pucp.dovah.Gestion.repository.SemestreRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
public class SemestreController {

    private final SemestreRepository repository;
    private final static Logger log = LoggerFactory.getLogger(SemestreController.class);

    public SemestreController(SemestreRepository repository) { this.repository = repository; }

    /*Listar todos los semestres activos*/
    @GetMapping("/semestre")
    List<Semestre> all(){

        return repository.queryAllByActivoIsTrue();

    }

    /*Buscar un semestre*/
    @GetMapping("/semestre/{id}")
    Semestre obtenerSemestrePorId(@PathVariable int id){
        return repository.findByIdSemestreAndActivoIsTrue(id).orElseThrow(() -> new SemestreNotFoundException(id));
    }

    /*Insertar un semestre*/
    @PostMapping("/semestre")
    Semestre nuevoSemestre(@RequestBody Map<String,Object> nuevoSemestre){
        log.info("Agregando semestre");
        var json = new JSONObject(nuevoSemestre);
        var semestre = new Semestre(json.getString("anhoAcademico"),json.getString("periodo"),
                json.getString("fechaInicio"),json.getString("fechaFin"),
                json.getString("fechaCierreNotasParcial"), json.getString("fechaCierreNotasFinal"));

        return repository.save(semestre);
    }

    /*Eliminar un semestre*/
    @PostMapping("/semestre/eliminar")
    Semestre eliminarSemestre(@RequestBody Map<String, Object> map){

        var json = new JSONObject(map);
        int id = json.getInt("idSemestre");
        var semestre = repository.findByIdSemestreAndActivoIsTrue(id).orElseThrow(()
                -> new SemestreNotFoundException(id));
        log.info(String.format("Eliminando semestre con id '%d'", semestre.getIdSemestre()));
        semestre.setActivo(false);
        return repository.save(semestre);
    }

    @PostMapping("/semestre/actualizar")
    Semestre actualizarSemestre(@RequestBody Map<String, Object> map) {
        var json = new JSONObject(map);
        int id = json.getInt("idSemestre");
        String anhoAcademico = json.getString("anhoAcademico");
        String periodo = json.getString("periodo");
        String fechaInicio = json.getString("fechaInicio");
        String fechaFin = json.getString("fechaFin");
        String fechaCierreNotasParcial = json.getString("fechaCierreNotasParcial");
        String fechaCierreNotasFinal = json.getString("fechaCierreNotasFinal");

        var semestre = repository.findByIdSemestreAndActivoIsTrue(id).orElseThrow(()
                ->new SemestreNotFoundException(id));
        log.info(String.format("Actualizando atributos de semestre con id '%d'",
                semestre.getIdSemestre()));
        semestre.setAnhoAcademico(anhoAcademico);
        semestre.setPeriodo(periodo);
        semestre.setFechaInicio(fechaInicio);
        semestre.setFechaFin(fechaFin);
        semestre.setFechaCierreNotasParcial(fechaCierreNotasParcial);
        semestre.setFechaCierreNotasFinal(fechaCierreNotasFinal);
        return repository.save(semestre);
    }
}
