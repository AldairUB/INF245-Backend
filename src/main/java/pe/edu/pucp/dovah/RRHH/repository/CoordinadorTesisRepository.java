/*
 * Nombre del archivo: CoordinadorTesisRepository
 * Fecha de creación: 2/10/2022 , 08:00
 * Autor: Lloyd Erwin Castillo Ramos
 * Descripción:
 */
package pe.edu.pucp.dovah.RRHH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.dovah.RRHH.model.CoordinadorTesis;

import java.util.List;

public interface CoordinadorTesisRepository extends JpaRepository<CoordinadorTesis, Integer> {

    List<CoordinadorTesis> queryAllByActivoIsTrue();

}
