/**
 * Nombre del archivo: DataSourceConfig.java
 * Fecha de creacion: 19/09/2022
 * Autor: Carlos Toro
 * Descripcion: Clase para cargar informacion de base de datos de un archivo
 */

package pe.edu.pucp.dovah.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        var prop = new Properties();

        try (var file = new FileInputStream("database.properties")) {
            prop.load(file);
            dataSourceBuilder.url("jdbc:mariadb://" + prop.getProperty("db.url") + "/" + prop.getProperty("db.database"));
            dataSourceBuilder.username(prop.getProperty("db.username"));
            dataSourceBuilder.password(prop.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSourceBuilder.build();
    }
}