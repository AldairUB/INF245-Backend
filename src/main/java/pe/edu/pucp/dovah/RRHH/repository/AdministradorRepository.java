package pe.edu.pucp.dovah.RRHH.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.dovah.RRHH.model.Administrador;

import java.util.List;

/*
 * Nombre del archivo: AdmnistradorRepository
 * Fecha de creación: 20/09/2022 , 18:00
 * Autor: Lloyd Castillo Ramos
 * Descripción: Interfaz encargada de manejar los metodos trabajados sobre la entidad Admnistrador
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    @Query(value = "SELECT a FROM Administrador a WHERE a.activo=true")
    List<Administrador>listarActivos();
}