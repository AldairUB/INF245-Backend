/**
 * Nombre del archivo: TareaController.java
 * Fecha de creacion: 20/09/2022
 * Autor: Carlos Toro
 * Descripcion: Clase que implementa la api de tareas
 */

package pe.edu.pucp.dovah.asignaciones.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;
import pe.edu.pucp.dovah.asignaciones.exception.DocumentoNotFoundException;
import pe.edu.pucp.dovah.asignaciones.exception.TareaNotFoundException;
import pe.edu.pucp.dovah.asignaciones.model.Tarea;
import pe.edu.pucp.dovah.asignaciones.repository.DocumentoRepository;
import pe.edu.pucp.dovah.asignaciones.repository.TareaRepository;

import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
public class TareaController {
    private final TareaRepository tareaRepository;
    private final DocumentoRepository documentoRepository;
    private final static Logger log = LoggerFactory.getLogger(TareaController.class);

    public TareaController(TareaRepository tareaRepository, DocumentoRepository documentoRepository) {
        this.tareaRepository = tareaRepository;
        this.documentoRepository = documentoRepository;
    }

    /*
    Listar todas las tareas registradas
     */
    @GetMapping("/tareas")
    List<Tarea> all() {
        return tareaRepository.findAll();
    }

    /*
    Listar una tarea en especifico
     */
    @GetMapping("/tareas/{id}")
    Tarea one(@PathVariable Long id) {
        return tareaRepository.findById(id).orElseThrow(() -> new TareaNotFoundException(id));
    }

    /*
    Insertar nueva tarea
     */
    @PostMapping("/tareas")
    Tarea newTarea(@RequestBody Map<String, Object> newTarea) {
        log.info("Agregando tarea...");
        var json = new JSONObject(newTarea);
        var tarea = new Tarea(json.getString("descripcion"));
        return tareaRepository.save(tarea);
    }

    /*
    Modificar la nota de una tarea
     */
    @PostMapping("/tareas/modificarNota")
    Tarea modificarNota(@RequestBody Map<String, Object> map) {
        var json = new JSONObject(map);
        Long id = json.getLong("id");
        int nota = json.getInt("nota");
        var tarea = tareaRepository.findById(id).orElseThrow(() -> new TareaNotFoundException(id));
        log.info(String.format("Modificando nota '%d' por nota '%d' de la tarea con id '%d'",
                tarea.getNota(), nota, id));
        tarea.setNota(nota);
        return tareaRepository.save(tarea);
    }

    /**
     * Agregar documento a tarea
     * @param map json con la siguiente estructura
     *            {
     *              "idTarea": num,
     *              "idDocumento": num
     *            }
     * @return Tarea modificada
     */
    @PostMapping("/tareas/agregarDocumento")
    Tarea agregarDocumento(@RequestBody Map<String, Object> map) {
        var json = new JSONObject(map);
        Long idTarea = json.getLong("idTarea");
        Long idDoc = json.getLong("idDocumento");
        var tarea = tareaRepository.findById(idTarea).orElseThrow(() -> new TareaNotFoundException(idTarea));
        var doc = documentoRepository.findById(idDoc)
                .orElseThrow(() -> new DocumentoNotFoundException(idDoc));
        tarea.getListaDocumentos().add(doc);
        return tareaRepository.save(tarea);
    }
}
