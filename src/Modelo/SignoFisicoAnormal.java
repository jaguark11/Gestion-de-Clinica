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
public class SignoFisicoAnormal {
    
    private int idSignoFisicoAnormal;
    private String nombre;
    private int folioHc;
    private Date fechaHc;

    public SignoFisicoAnormal() {}

    public SignoFisicoAnormal(int idSignoFisicoAnormal, String nombre, int folioHc, Date fechaHc) {
        this.idSignoFisicoAnormal = idSignoFisicoAnormal;
        this.nombre = nombre;
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
    }

    public int getIdSignoFisicoAnormal() {
        return idSignoFisicoAnormal;
    }

    public void setIdSignoFisicoAnormal(int idSignoFisicoAnormal) {
        this.idSignoFisicoAnormal = idSignoFisicoAnormal;
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
        return "SignoFisicoAnormal{" + "idSignoFisicoAnormal=" + idSignoFisicoAnormal + ", nombre=" + nombre + ", folioHc=" + folioHc + ", fechaHc=" + fechaHc + '}';
    }
    
        
}
