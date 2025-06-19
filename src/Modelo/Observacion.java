package Modelo;

import java.sql.Date;

/**
 * Modelo para la tabla observacion.
 */
public class Observacion {
    private int idObservacion;
    private int folioHc;
    private Date fechaHc;
    private String contenido;

    /** Constructor vacío necesario para el formulario y DAO */
    public Observacion() {
    }

    /**
     * Constructor completo usado al leer de la base de datos
     * @param idObservacion  clave primaria
     * @param folioHc        folio de la historia clínica
     * @param fechaHc        fecha de la observación
     * @param contenido      texto de la observación
     */
    public Observacion(int idObservacion, int folioHc, Date fechaHc, String contenido) {
        this.idObservacion = idObservacion;
        this.folioHc       = folioHc;
        this.fechaHc       = fechaHc;
        this.contenido     = contenido;
    }

    // —— Getters y setters —— //

    public int getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(int idObservacion) {
        this.idObservacion = idObservacion;
    }

    public int getFolioHc() {
        return folioHc;
    }

    public void setFolioHc(int folioHc) {
        this.folioHc = folioHc;
    }

    public Date getFechaHc() {
        return fechaHc;
    }

    public void setFechaHc(Date fechaHc) {
        this.fechaHc = fechaHc;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Observacion{" +
               "idObservacion=" + idObservacion +
               ", folioHc=" + folioHc +
               ", fechaHc=" + fechaHc +
               ", contenido='" + contenido + '\'' +
               '}';
    }
}
