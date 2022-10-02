/*
 * Nombre del archivo: UsuarioController
 * Fecha de creación: 1/10/2022 , 09:02
 * Autor: Lloyd Erwin Castillo Ramos
 * Descripción:
 */
package pe.edu.pucp.dovah.RRHH.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.pucp.dovah.RRHH.repository.UsuarioRepository;


@BasePathAwareController
@RestController
public class UsuarioController {

    private final UsuarioRepository repositoryUsuario;
    private final static Logger log = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioRepository repository) {

        this.repositoryUsuario = repository;

    }
}
