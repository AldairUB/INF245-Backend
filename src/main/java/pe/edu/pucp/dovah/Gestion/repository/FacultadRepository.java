/*
 * Nombre del archivo: FacultadRepository.java
 * Fecha de creacion: 20-09-2022
 * Autor: Victor Avalos
 * Descripción: Interfaz de repositorio para la clase Facultad
 */
package pe.edu.pucp.dovah.Gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.pucp.dovah.Gestion.model.Facultad;

import java.util.List;

public interface FacultadRepository extends JpaRepository<Facultad,Integer>{
    @Query(value = "SELECT a FROM Facultad a WHERE a.activo = true")
    List<Facultad> listarActivos();
}
