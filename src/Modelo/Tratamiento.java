/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Tratamiento {
    
    private int idTratamiento;
    private String nombre;
    private int folioHc;
    private Date fechaHc;

    public Tratamiento() {}

    public Tratamiento(int idTratamiento, String nombre, int folioHc, Date fechaHc) {
        this.idTratamiento = idTratamiento;
        this.nombre = nombre;
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Tratamiento{" + "idTratamiento=" + idTratamiento + ", nombre=" + nombre + ", folioHc=" + folioHc + ", fechaHc=" + fechaHc + '}';
    }
    
    
    
}
