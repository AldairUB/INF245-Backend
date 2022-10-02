package pe.edu.pucp.dovah.asignaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.dovah.asignaciones.model.Documento;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Optional<Documento> findByIdAndNombreAndActivoIsTrue(Long id, String nombre);

    Optional<Documento> queryByIdAndActivoIsTrue(Long id);

    List<Documento> queryAllByActivoIsTrue();


}
