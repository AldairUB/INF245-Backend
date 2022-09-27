package pe.edu.pucp.dovah.asignaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.pucp.dovah.asignaciones.model.Tarea;
import pe.edu.pucp.dovah.asignaciones.repository.TareaRepository;

@Configuration
public class AsignacionesTestData {
    private final static Logger log = LoggerFactory.getLogger(AsignacionesTestData.class);
    private final TareaRepository tareaRepository;

    public AsignacionesTestData(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Bean
    public CommandLineRunner loadTareas() {
        return (args) -> {
            if (tareaRepository.findAll().isEmpty()) {
                log.info("Preloading " + tareaRepository.save(new Tarea("descripcion1")));
                log.info("Preloading " + tareaRepository.save(new Tarea("descripcion2")));
                log.info("Preloading " + tareaRepository.save(new Tarea("descripcion3")));
                log.info("Preloading " + tareaRepository.save(new Tarea("descripcion4")));
                log.info("Preloading " + tareaRepository.save(new Tarea("descripcion5")));
            }
        };
    }
}
