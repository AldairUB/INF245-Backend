package pe.edu.pucp.dovah.Gestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.pucp.dovah.Gestion.model.Especialidad;
import pe.edu.pucp.dovah.Gestion.model.Facultad;
import pe.edu.pucp.dovah.Gestion.model.Semestre;
import pe.edu.pucp.dovah.Gestion.repository.CursoRepository;
import pe.edu.pucp.dovah.Gestion.repository.EspecialidadRepository;
import pe.edu.pucp.dovah.Gestion.repository.FacultadRepository;
import pe.edu.pucp.dovah.Gestion.repository.SemestreRepository;
import java.util.Date;

@Configuration
public class GestionTestData {
    private final static Logger log = LoggerFactory.getLogger(GestionTestData.class);
    private final FacultadRepository facultadRepository;
    private final EspecialidadRepository especialidadRepository;
    private final SemestreRepository semestreRepository;
    private final CursoRepository cursoRepository;

    public GestionTestData(FacultadRepository facultadRepository, EspecialidadRepository especialidadRepository,
                           SemestreRepository semestreRepository,CursoRepository cursoRepository) {
        this.facultadRepository = facultadRepository;
        this.especialidadRepository = especialidadRepository;
        this.semestreRepository = semestreRepository;
        this.cursoRepository = cursoRepository;
    }
    @Bean
    public CommandLineRunner loadFacultad() {
        return (args) -> {
            if (facultadRepository.findAll().isEmpty()) {
                log.info("Agregando Facultad..."+facultadRepository.save(new Facultad("Ciencias e Ingenieria",
                        "Mendoza",1950)));
                log.info("Agregando Facultad..."+facultadRepository.save(new Facultad("Arquitectura y Urbanismo",
                        "Campos",1960)));
            }
        };
    }
    @Bean
    public CommandLineRunner loadEspecialidad() {
        return (args) -> {
            if (especialidadRepository.findAll().isEmpty()) {
                log.info("Agregando Especialidad..."+especialidadRepository.save(new Especialidad("Informatica",
                        "INF","Luis Flores","desarrollarás distintas soluciones " +
                        "necesarias para la automatización de la información")));
                log.info("Agregando Especialidad..."+especialidadRepository.save(new Especialidad("Industrial",
                        "IND","Carlos Villalobos","se ocupa del diseño, implementación, " +
                        "gestión, optimización y dirección de sistemas de producción de bienes y servicios")));
            }
        };
    }

    @Bean
    public CommandLineRunner loadSemestre() {
        return (args) -> {
            if (semestreRepository.findAll().isEmpty()) {
                log.info("Agregando Semestre..."+semestreRepository.save(new Semestre("2022",
                        "1","15/03/2022","15/07/2022","20/07/2022",
                        "25/07/2022")));
                log.info("Agregando Semestre..."+semestreRepository.save(new Semestre("2022",
                        "2","15/08/2022","10/12/2022", "18/12/2022",
                        "22/12/2022")));
            }
        };
    }

    @Bean
    public CommandLineRunner loadCursos() {
        return (args) -> {
            if (cursoRepository.findAll().isEmpty()) {
                log.info("Agregando cursos...");
            }
        };
    }
}
