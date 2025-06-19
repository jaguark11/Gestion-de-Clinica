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
public class ExamenComplementario {
    private String nombreExamen;
    private int folioHc;
    private Date fechaHc;

    public ExamenComplementario() {}

    public ExamenComplementario(String nombreExamen, int folioHc, Date fechaHc) {
        this.nombreExamen = nombreExamen;
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
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
        return "ExamenComplementario{" + "nombreExamen=" + nombreExamen + ", folioHc=" + folioHc + ", fechaHc=" + fechaHc + '}';
    }
    
    
    
}
