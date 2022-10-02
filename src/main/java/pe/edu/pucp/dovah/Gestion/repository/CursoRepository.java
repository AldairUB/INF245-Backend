/*
 * Nombre del archivo: CursoRepository.java
 * Fecha de creacion: 20-09-2022
 * Autor: Victor Avalos
 * Descripción: Interfaz de repositorio para la clase Curso
 */
package pe.edu.pucp.dovah.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.pucp.dovah.Gestion.model.Curso;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

    List<Curso> queryAllByActivoIsTrue();

}
