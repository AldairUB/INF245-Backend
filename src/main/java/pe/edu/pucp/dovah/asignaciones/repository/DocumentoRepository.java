package pe.edu.pucp.dovah.asignaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.pucp.dovah.asignaciones.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
