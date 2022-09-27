package pe.edu.pucp.dovah.RRHH;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.pucp.dovah.RRHH.model.Administrador;
import pe.edu.pucp.dovah.RRHH.model.Profesor;
import pe.edu.pucp.dovah.RRHH.repository.AdministradorRepository;
import pe.edu.pucp.dovah.RRHH.repository.ProfesorRepository;

@Configuration
public class RRHHTestData {
    private final static Logger log = LoggerFactory.getLogger(RRHHTestData.class);
    private final AdministradorRepository administradorRepository;
    private final ProfesorRepository profesorRepository;

    public RRHHTestData(AdministradorRepository administradorRepository, ProfesorRepository profesorRepository) {
        this.administradorRepository = administradorRepository;
        this.profesorRepository = profesorRepository;
    }

    @Bean
    public CommandLineRunner loadAdministrador(){
        return (args) -> {
            if(administradorRepository.findAll().isEmpty()){
                log.info("Creando administrador "+administradorRepository.save(new Administrador("Juan","Veliz",
                        'M',"20193345","jveliz@gmail.com","zjcveli")));
            }
        };
    }

    @Bean
    public CommandLineRunner loadProfesor(){
        return (args) -> {
            if(profesorRepository.findAll().isEmpty()){
                log.info(("Creando profesor "+profesorRepository.save(new Profesor("Rony","Cueva",'M',
                        "X2458","rcueva@pucp.edu.pe","http//....","arcuev"))));
            }

        };
    }
}
