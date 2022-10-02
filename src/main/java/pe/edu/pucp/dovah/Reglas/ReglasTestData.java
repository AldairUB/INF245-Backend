/*
 * Nombre del archivo: ReglasTestData
 * Fecha de creación: 2/10/2022 , 10:15
 * Autor: Lloyd Erwin Castillo Ramos
 * Descripción:
 */
package pe.edu.pucp.dovah.Reglas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.pucp.dovah.Gestion.GestionTestData;
import pe.edu.pucp.dovah.Reglas.model.Rol;
import pe.edu.pucp.dovah.Reglas.repository.RolRepository;

@Configuration
public class ReglasTestData {

    private final static Logger log = LoggerFactory.getLogger(ReglasTestData.class);
    private final RolRepository rolRepository;
    public ReglasTestData(RolRepository rolRepository) {

        this.rolRepository = rolRepository;

    }
    @Bean
    public CommandLineRunner loadRol() {
        return (args) ->{
            if(rolRepository.findAll().isEmpty()){

                log.info("Agregando Roles..."+rolRepository.save(new Rol("Asesor")));
                log.info("Agregando Roles..."+rolRepository.save(new Rol("Jurado")));

            }

        };

    }



}
