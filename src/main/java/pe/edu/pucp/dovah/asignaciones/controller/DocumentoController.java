package pe.edu.pucp.dovah.asignaciones.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.pucp.dovah.asignaciones.model.Documento;
import pe.edu.pucp.dovah.asignaciones.repository.DocumentoRepository;
import pe.edu.pucp.dovah.asignaciones.repository.TareaRepository;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.Map;

@BasePathAwareController
@RestController
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

    @PostMapping("/documento")
    Documento insertarDocumento(@RequestBody Map<String, Object> map) {
        var json = new JSONObject(map);
        Documento doc = new Documento(json.getString("nombre"));
        byte[] blob = DatatypeConverter.parseBase64Binary(json.getString("base64"));
        doc.setBlobDoc(blob);
        return documentoRepository.save(doc);
    }
}
