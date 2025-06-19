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
public class Vacunacion {
    
    private int idVacuna;
    private int folioHc;
    private Date fechaHc;
    private String otraVacuna;
    private Date fechaAplicacion;

    public Vacunacion() {}

    public Vacunacion(int idVacuna, int folioHc, Date fechaHc, String otraVacuna, Date fechaAplicacion) {
        this.idVacuna = idVacuna;
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
        this.otraVacuna = otraVacuna;
        this.fechaAplicacion = fechaAplicacion;
    }

    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
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

    public String getOtraVacuna() {
        return otraVacuna;
    }

    public void setOtraVacuna(String otraVacuna) {
        this.otraVacuna = otraVacuna;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    @Override
    public String toString() {
        return "Vacunacion{" + "idVacuna=" + idVacuna + ", folioHc=" + folioHc + ", fechaHc=" + fechaHc + ", otraVacuna=" + otraVacuna + ", fechaAplicacion=" + fechaAplicacion + '}';
    }
    
    
    
}
