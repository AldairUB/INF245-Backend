package pe.edu.pucp.dovah.asignaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.activation.MimeType;
import javax.persistence.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(value = { "blobDoc" })
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "listaDocumentos")
    private List<Tarea> listaTareas;
    @ColumnDefault("true")
    private boolean activo;
    private String nombre;
    private String url;
    @Lob
    private byte[] blobDoc;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime fechaCreacion;

    protected Documento() {}

    public Documento(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
        this.url = "";
    }

    @Override
    public String toString() {
        return String.format("Documento(Nombre='%s')", this.getNombre());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        String urlToDecode = String.format("http://localhost:8081/api/v1/documento/blob/%d/%s", id, nombre);
        String decodedURL = URLDecoder.decode(urlToDecode, StandardCharsets.UTF_8);
        URL url;
        URI uri;
        try {
            url = new URL(decodedURL);
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
                    url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return uri.toASCIIString();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public byte[] getBlobDoc() {
        return blobDoc;
    }

    public void setBlobDoc(byte[] blobDoc) {
        this.blobDoc = blobDoc;
    }
}
