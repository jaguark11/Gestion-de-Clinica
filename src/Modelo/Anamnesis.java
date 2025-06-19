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
public class Anamnesis {
    
    private int folioHc;
    private Date fechaHc;
    private String dieta;
    private String frecuencia;
    private String modo;
    private Date tiempoPosesionCaballo;
    private boolean otroAnimal;
    private String medioAmbiente;

    public Anamnesis() {}

    public Anamnesis(int folioHc, Date fechaHc, String dieta, String frecuencia, String modo, Date tiempoPosesionCaballo, boolean otroAnimal, String medioAmbiente) {
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
        this.dieta = dieta;
        this.frecuencia = frecuencia;
        this.modo = modo;
        this.tiempoPosesionCaballo = tiempoPosesionCaballo;
        this.otroAnimal = otroAnimal;
        this.medioAmbiente = medioAmbiente;
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

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public Date getTiempoPosesionCaballo() {
        return tiempoPosesionCaballo;
    }

    public void setTiempoPosesionCaballo(Date tiempoPosesionCaballo) {
        this.tiempoPosesionCaballo = tiempoPosesionCaballo;
    }

    public boolean isOtroAnimal() {
        return otroAnimal;
    }

    public void setOtroAnimal(boolean otroAnimal) {
        this.otroAnimal = otroAnimal;
    }

    public String getMedioAmbiente() {
        return medioAmbiente;
    }

    public void setMedioAmbiente(String medioAmbiente) {
        this.medioAmbiente = medioAmbiente;
    }

    @Override
    public String toString() {
        return "Anamnesis{" + "folioHc=" + folioHc + ", fechaHc=" + fechaHc + ", dieta=" + dieta + ", frecuencia=" + frecuencia + ", modo=" + modo + ", tiempoPosesionCaballo=" + tiempoPosesionCaballo + ", otroAnimal=" + otroAnimal + ", medioAmbiente=" + medioAmbiente + '}';
    }
    
    
    
}
