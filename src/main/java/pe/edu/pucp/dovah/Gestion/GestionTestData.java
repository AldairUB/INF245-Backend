package pe.edu.pucp.dovah.Gestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.pucp.dovah.Gestion.repository.CursoRepository;

@Configuration
public class GestionTestData {
    private final static Logger log = LoggerFactory.getLogger(GestionTestData.class);
    private final CursoRepository cursoRepository;

    public GestionTestData(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Bean
    public CommandLineRunner loadTareas() {
        return (args) -> {
            if (cursoRepository.findAll().isEmpty()) {
                log.info("Agregando datos...");
            }
        };
    }
}
