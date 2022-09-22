/**
 * Nombre del archivo: Tarea.java
 * Fecha de creacion: 20/09/2022
 * Autor: Carlos Toro
 * Descripcion: Clase que implementa el repositorio (DAO) del modelo tarea
 */

package pe.edu.pucp.dovah.asignaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.dovah.asignaciones.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
