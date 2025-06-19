package Modelo;

import java.sql.Date;

public class Enfermedad {
    private int idEnfermedad;
    private int folioHc;
    private Date fechaHc;
    private String nombre;
    private String padecido;
    private String infecciosa;
    private String tipo;
    private int año;

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadecido() {
        return padecido;
    }

    public void setPadecido(String padecido) {
        this.padecido = padecido;
    }

    public String getInfecciosa() {
        return infecciosa;
    }

    public void setInfecciosa(String infecciosa) {
        this.infecciosa = infecciosa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}
