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
public class HistoriaClinica {
    
    private int folio;
    private Date fecha;
    private int idPropietario;
    private int idPaciente;
    private int cedulaProfesional;

    public HistoriaClinica() {}

    public HistoriaClinica(int folio, Date fecha, int idPropietario, int idPaciente, int cedulaProfesional) {
        this.folio = folio;
        this.fecha = fecha;
        this.idPropietario = idPropietario;
        this.idPaciente = idPaciente;
        this.cedulaProfesional = cedulaProfesional;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(int cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" + "folio=" + folio + ", fecha=" + fecha + ", idPropietario=" + idPropietario + ", idPaciente=" + idPaciente + ", cedulaProfesional=" + cedulaProfesional + '}';
    }
    
    
    
}
