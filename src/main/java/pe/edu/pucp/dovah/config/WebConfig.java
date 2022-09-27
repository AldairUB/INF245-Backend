/**
 * Nombre del archivo: WebConfig.java
 * Fecha de creacion: 23/09/2022
 * Autor: Carlos Toro
 * Descripcion: Configuracion necesaria para spring
 */

package pe.edu.pucp.dovah.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:3000", "http://localhost:8081", "http://localhost")
                .allowedMethods("*");
    }
}
