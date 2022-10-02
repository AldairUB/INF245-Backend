package pe.edu.pucp.dovah.RRHH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.dovah.RRHH.model.Profesor;

import java.util.List;

/*
 * Nombre del archivo: ProfesorRepository
 * Fecha de creación: 20/09/2022 , 18:00
 * Autor: Lloyd Castillo Ramos
 * Descripción: Interfaz encargada de manejar los metodos trabajados sobre la entidad Profesor
 */
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor,Integer> {

    List<Profesor>queryAllByActivoIsTrue();
    List<Profesor>findByCodigoPUCPContainingAndActivoIsTrue(String codigo);
}
