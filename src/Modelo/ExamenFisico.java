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
public class ExamenFisico {
    private int idExamen;
    private int folioHc;
    private Date fechaHc;
    private double temperatura;
    private String pulso;
    private String caracteristicas;
    private int frecuenciaCardiaca;
    private int frecuenciaRespiratoria;
    private String llenadoCapilar;
    private String mucosas;
    private String motilidad;
    private String pulsoDigital;
    private String aspectoGeneral;
    private String aparatoLocomotor;
    private String aparatoCirculatorio;
    private String aparatoDigestivo;
    private String aparatoGenitourinario;
    private String sistemaNervioso;
    private String oidos;
    private String ojos;
    private String gangliosLinfaticos;
    private String piel;

    public ExamenFisico() {}

    public ExamenFisico(int idExamen, int folioHc, Date fechaHc, double temperatura, String pulso, String caracteristicas, int frecuenciaCardiaca, int frecuenciaRespiratoria, String llenadoCapilar, String mucosas, String motilidad, String pulsoDigital, String aspectoGeneral, String aparatoLocomotor, String aparatoCirculatorio, String aparatoDigestivo, String aparatoGenitourinario, String sistemaNervioso, String oidos, String ojos, String gangliosLinfaticos, String piel) {
        this.idExamen = idExamen;
        this.folioHc = folioHc;
        this.fechaHc = fechaHc;
        this.temperatura = temperatura;
        this.pulso = pulso;
        this.caracteristicas = caracteristicas;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.llenadoCapilar = llenadoCapilar;
        this.mucosas = mucosas;
        this.motilidad = motilidad;
        this.pulsoDigital = pulsoDigital;
        this.aspectoGeneral = aspectoGeneral;
        this.aparatoLocomotor = aparatoLocomotor;
        this.aparatoCirculatorio = aparatoCirculatorio;
        this.aparatoDigestivo = aparatoDigestivo;
        this.aparatoGenitourinario = aparatoGenitourinario;
        this.sistemaNervioso = sistemaNervioso;
        this.oidos = oidos;
        this.ojos = ojos;
        this.gangliosLinfaticos = gangliosLinfaticos;
        this.piel = piel;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
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

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public String getLlenadoCapilar() {
        return llenadoCapilar;
    }

    public void setLlenadoCapilar(String llenadoCapilar) {
        this.llenadoCapilar = llenadoCapilar;
    }

    public String getMucosas() {
        return mucosas;
    }

    public void setMucosas(String mucosas) {
        this.mucosas = mucosas;
    }

    public String getMotilidad() {
        return motilidad;
    }

    public void setMotilidad(String motilidad) {
        this.motilidad = motilidad;
    }

    public String getPulsoDigital() {
        return pulsoDigital;
    }

    public void setPulsoDigital(String pulsoDigital) {
        this.pulsoDigital = pulsoDigital;
    }

    public String getAspectoGeneral() {
        return aspectoGeneral;
    }

    public void setAspectoGeneral(String aspectoGeneral) {
        this.aspectoGeneral = aspectoGeneral;
    }

    public String getAparatoLocomotor() {
        return aparatoLocomotor;
    }

    public void setAparatoLocomotor(String aparatoLocomotor) {
        this.aparatoLocomotor = aparatoLocomotor;
    }

    public String getAparatoCirculatorio() {
        return aparatoCirculatorio;
    }

    public void setAparatoCirculatorio(String aparatoCirculatorio) {
        this.aparatoCirculatorio = aparatoCirculatorio;
    }

    public String getAparatoDigestivo() {
        return aparatoDigestivo;
    }

    public void setAparatoDigestivo(String aparatoDigestivo) {
        this.aparatoDigestivo = aparatoDigestivo;
    }

    public String getAparatoGenitourinario() {
        return aparatoGenitourinario;
    }

    public void setAparatoGenitourinario(String aparatoGenitourinario) {
        this.aparatoGenitourinario = aparatoGenitourinario;
    }

    public String getSistemaNervioso() {
        return sistemaNervioso;
    }

    public void setSistemaNervioso(String sistemaNervioso) {
        this.sistemaNervioso = sistemaNervioso;
    }

    public String getOidos() {
        return oidos;
    }

    public void setOidos(String oidos) {
        this.oidos = oidos;
    }

    public String getOjos() {
        return ojos;
    }

    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    public String getGangliosLinfaticos() {
        return gangliosLinfaticos;
    }

    public void setGangliosLinfaticos(String gangliosLinfaticos) {
        this.gangliosLinfaticos = gangliosLinfaticos;
    }

    public String getPiel() {
        return piel;
    }

    public void setPiel(String piel) {
        this.piel = piel;
    }

    @Override
    public String toString() {
        return "ExamenFisico{" + "idExamen=" + idExamen + ", folioHc=" + folioHc + ", fechaHc=" + fechaHc + ", temperatura=" + temperatura + ", pulso=" + pulso + ", caracteristicas=" + caracteristicas + ", frecuenciaCardiaca=" + frecuenciaCardiaca + ", frecuenciaRespiratoria=" + frecuenciaRespiratoria + ", llenadoCapilar=" + llenadoCapilar + ", mucosas=" + mucosas + ", motilidad=" + motilidad + ", pulsoDigital=" + pulsoDigital + ", aspectoGeneral=" + aspectoGeneral + ", aparatoLocomotor=" + aparatoLocomotor + ", aparatoCirculatorio=" + aparatoCirculatorio + ", aparatoDigestivo=" + aparatoDigestivo + ", aparatoGenitourinario=" + aparatoGenitourinario + ", sistemaNervioso=" + sistemaNervioso + ", oidos=" + oidos + ", ojos=" + ojos + ", gangliosLinfaticos=" + gangliosLinfaticos + ", piel=" + piel + '}';
    }
    
    
    
}
