package pe.edu.pucp.dovah.asignaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import pe.edu.pucp.dovah.asignaciones.model.Documento;
import pe.edu.pucp.dovah.asignaciones.model.Tarea;
import pe.edu.pucp.dovah.asignaciones.repository.DocumentoRepository;
import pe.edu.pucp.dovah.asignaciones.repository.TareaRepository;

import javax.print.Doc;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.nio.file.Files;

@Configuration
public class AsignacionesTestData {
    private final static Logger log = LoggerFactory.getLogger(AsignacionesTestData.class);
    private final TareaRepository tareaRepository;
    private final DocumentoRepository documentoRepository;

    public AsignacionesTestData(TareaRepository tareaRepository, DocumentoRepository documentoRepository) {
        this.tareaRepository = tareaRepository;
        this.documentoRepository = documentoRepository;
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

    @Bean
    public CommandLineRunner loadDocumentos() {
        return (args) -> {
            if (documentoRepository.findAll().isEmpty()) {
                Documento doc = new Documento("application.properties");
                File f = ResourceUtils.getFile("classpath:application.properties");
                doc.setBlobDoc(Files.readAllBytes(f.toPath()));
                log.info("Precargando " + documentoRepository.save(doc));
                doc = new Documento("hello.html");
                f = ResourceUtils.getFile("classpath:templates/hello.html");
                doc.setBlobDoc(Files.readAllBytes(f.toPath()));
                log.info("Precargando " + documentoRepository.save(doc));
                doc = new Documento("20-amdgpu.conf");
                doc.setNombre("20-amdgpu.conf");
                String b64str = """
                        IyB2aTpzZXQgZnQ9eGY4NmNvbmY6ClNlY3Rpb24gIkRldmljZSIKICAgIElkZW50aWZpZXIgIkFN
                        RCIKICAgIERyaXZlciAiYW1kZ3B1IgogICAgT3B0aW9uICJUZWFyRnJlZSIgInRydWUiCiAgICBP
                        cHRpb24gIlNXQ3Vyc29yIiAidHJ1ZSIKICAgIE9wdGlvbiAiVmFyaWFibGVSZWZyZXNoIiAidHJ1
                        ZSIKRW5kU2VjdGlvbgo=
                        """;
                doc.setBlobDoc(DatatypeConverter.parseBase64Binary(b64str));
                log.info("Precargando " + documentoRepository.save(doc));
            }
        };
    }
}
