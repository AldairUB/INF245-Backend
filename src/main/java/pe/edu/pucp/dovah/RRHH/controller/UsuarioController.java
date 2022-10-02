/*
 * Nombre del archivo: UsuarioController
 * Fecha de creación: 1/10/2022 , 09:02
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
import pe.edu.pucp.dovah.RRHH.model.Usuario;
import pe.edu.pucp.dovah.RRHH.repository.UsuarioRepository;
import pe.edu.pucp.dovah.Reglas.exception.RolNotFoundException;
import pe.edu.pucp.dovah.Reglas.repository.RolRepository;

import java.util.List;
import java.util.Map;


@BasePathAwareController
@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final static Logger log = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioRepository repository,RolRepository rolRepository) {

        this.usuarioRepository = repository;
        this.rolRepository = rolRepository;

    }

    @GetMapping("/usuario")
    List<Usuario>listarTodos(){

        return usuarioRepository.findAll();

    }

    @GetMapping("/usuario/{id}")
    Usuario obtenerUsuarioPorId(@PathVariable int id){

        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

    }

    @PostMapping("/usuario/agregarRol")
    Usuario agregarRol(@RequestBody Map<String, Object> map){

        var json = new JSONObject(map);
        int idUsuario = json.getInt("idUsuario");
        int idRol = json.getInt("idRol");
        var usuario = usuarioRepository.queryAllByIdUsuario(idUsuario).orElseThrow(()->
                new UsuarioNotFoundException(idUsuario));
        var rol = rolRepository.findById(idRol).orElseThrow(()->new RolNotFoundException(idRol));
        usuario.getListaRoles().add(rol);
        return usuarioRepository.save(usuario);

    }

}
