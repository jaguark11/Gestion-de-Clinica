package Modelo;

import java.time.LocalDateTime;

public class Propietario {
    private int idPropietario;
    private String nombre;
    private String telefono;
    private String domicilio;
    private boolean eliminado;
    private LocalDateTime createdAt;

    public Propietario() {}

    public int getIdPropietario() {
        return idPropietario;
    }
    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // <<<<<<<<<<<<<<<<< aquí estaban los errores — faltaban estos getters/setters
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    // >>>>>>>>>>>>>>>>

    public boolean isDeleted() {
        return eliminado;
    }
    public void setDeleted(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
