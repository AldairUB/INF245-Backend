/*
 * Nombre del archivo: UsuarioRepository
 * Fecha de creación: 1/10/2022 , 09:05
 * Autor: Lloyd Erwin Castillo Ramos
 * Descripción:
 */
package pe.edu.pucp.dovah.RRHH.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.dovah.RRHH.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    @EntityGraph(attributePaths = {"listaRoles"})
    Optional<Usuario> queryAllByIdUsuario(int id);


}
