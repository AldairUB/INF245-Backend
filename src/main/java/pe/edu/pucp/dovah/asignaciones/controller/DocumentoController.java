package pe.edu.pucp.dovah.asignaciones.controller;

import org.apache.tika.Tika;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pe.edu.pucp.dovah.asignaciones.exception.DocumentoNotFoundException;
import pe.edu.pucp.dovah.asignaciones.model.Documento;
import pe.edu.pucp.dovah.asignaciones.repository.DocumentoRepository;
import pe.edu.pucp.dovah.asignaciones.repository.TareaRepository;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class DocumentoController {
    private final static Logger log = LoggerFactory.getLogger(DocumentoController.class);
    private final TareaRepository tareaRepository;
    private final DocumentoRepository documentoRepository;

    public DocumentoController(TareaRepository tareaRepository, DocumentoRepository documentoRepository) {
        this.tareaRepository = tareaRepository;
        this.documentoRepository = documentoRepository;
    }

    @GetMapping("/documento")
    List<Documento> getAllDocuments() {
        return documentoRepository.findAll();
    }

    @GetMapping("/documento/{id}")
    Documento getOneDocument(@PathVariable Long id) {
        return documentoRepository.findById(id).orElseThrow(() -> new DocumentoNotFoundException(id));
    }

    @GetMapping(value = "/documento/blob/{id}/{nombre}")
    ResponseEntity<StreamingResponseBody> getDocBlob(@PathVariable Long id, @PathVariable String nombre) {
        Documento doc = documentoRepository.findByIdAndNombre(id, nombre)
                .orElseThrow(() -> new DocumentoNotFoundException(id));
        StreamingResponseBody responseBody = outputStream -> outputStream.write(doc.getBlobDoc());
        Tika tika = new Tika();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.inline().filename(doc.getNombre()).build());
        headers.setContentLength(doc.getBlobDoc().length);
        headers.setContentType(MediaType.parseMediaType(tika.detect(doc.getBlobDoc())));
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    @PostMapping("/documento")
    Documento insertarDocumento(@RequestBody Map<String, Object> map) {
        var json = new JSONObject(map);
        Documento doc = new Documento(json.getString("nombre"));
        byte[] blob = DatatypeConverter.parseBase64Binary(json.getString("base64"));
        doc.setBlobDoc(blob);
        return documentoRepository.save(doc);
    }
}
