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
public class DiagnosticoIntegral {
    
    private int idDiagnosticoIntegral;
    private String nombre;
    private int folioHc;
    private Date fechaHc;

    public DiagnosticoIntegral() {}

    public DiagnosticoIntegral(int idDiagnosticoIntegral, String nombre, int folioHc, Date fechaHc) {
        this.idDiagnosticoIntegral = idDiagnosticoIntegral;
        this.nombre = nombre;
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
    }

    public int getIdDiagnosticoIntegral() {
        return idDiagnosticoIntegral;
    }

    public void setIdDiagnosticoIntegral(int idDiagnosticoIntegral) {
        this.idDiagnosticoIntegral = idDiagnosticoIntegral;
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
        return "DiagnosticoIntegral{" + "idDiagnosticoIntegral=" + idDiagnosticoIntegral + ", nombre=" + nombre + ", folioHc=" + folioHc + ", fechaHc=" + fechaHc + '}';
    }
    
    

    
}
