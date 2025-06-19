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
public class Antecedente {
    
    private int idAntecedente;
    private String contenido;
    private String dietaAnamnesis;
    private String frecuenciaAnamnesis;
    private String modoAnamnesis;
    private Date tiempoPosesionCaballoAnamnesis;
    private boolean otroAnimalAnamnesis;
    private String medioAmbienteAnamnesis;

    public Antecedente() {}

    public Antecedente(int idAntecedente, String contenido, String dietaAnamnesis, String frecuenciaAnamnesis, String modoAnamnesis, Date tiempoPosesionCaballoAnamnesis, boolean otroAnimalAnamnesis, String medioAmbienteAnamnesis) {
        this.idAntecedente = idAntecedente;
        this.contenido = contenido;
        this.dietaAnamnesis = dietaAnamnesis;
        this.frecuenciaAnamnesis = frecuenciaAnamnesis;
        this.modoAnamnesis = modoAnamnesis;
        this.tiempoPosesionCaballoAnamnesis = tiempoPosesionCaballoAnamnesis;
        this.otroAnimalAnamnesis = otroAnimalAnamnesis;
        this.medioAmbienteAnamnesis = medioAmbienteAnamnesis;
    }

    public int getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(int idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDietaAnamnesis() {
        return dietaAnamnesis;
    }

    public void setDietaAnamnesis(String dietaAnamnesis) {
        this.dietaAnamnesis = dietaAnamnesis;
    }

    public String getFrecuenciaAnamnesis() {
        return frecuenciaAnamnesis;
    }

    public void setFrecuenciaAnamnesis(String frecuenciaAnamnesis) {
        this.frecuenciaAnamnesis = frecuenciaAnamnesis;
    }

    public String getModoAnamnesis() {
        return modoAnamnesis;
    }

    public void setModoAnamnesis(String modoAnamnesis) {
        this.modoAnamnesis = modoAnamnesis;
    }

    public Date getTiempoPosesionCaballoAnamnesis() {
        return tiempoPosesionCaballoAnamnesis;
    }

    public void setTiempoPosesionCaballoAnamnesis(Date tiempoPosesionCaballoAnamnesis) {
        this.tiempoPosesionCaballoAnamnesis = tiempoPosesionCaballoAnamnesis;
    }

    public boolean isOtroAnimalAnamnesis() {
        return otroAnimalAnamnesis;
    }

    public void setOtroAnimalAnamnesis(boolean otroAnimalAnamnesis) {
        this.otroAnimalAnamnesis = otroAnimalAnamnesis;
    }

    public String getMedioAmbienteAnamnesis() {
        return medioAmbienteAnamnesis;
    }

    public void setMedioAmbienteAnamnesis(String medioAmbienteAnamnesis) {
        this.medioAmbienteAnamnesis = medioAmbienteAnamnesis;
    }

    @Override
    public String toString() {
        return "Antecedente{" + "idAntecedente=" + idAntecedente + ", contenido=" + contenido + ", dietaAnamnesis=" + dietaAnamnesis + ", frecuenciaAnamnesis=" + frecuenciaAnamnesis + ", modoAnamnesis=" + modoAnamnesis + ", tiempoPosesionCaballoAnamnesis=" + tiempoPosesionCaballoAnamnesis + ", otroAnimalAnamnesis=" + otroAnimalAnamnesis + ", medioAmbienteAnamnesis=" + medioAmbienteAnamnesis + '}';
    }
    
    
    
}
