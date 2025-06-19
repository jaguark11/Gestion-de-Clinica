/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Lenovo
 */
public class MedicoVeterinario {
    
    private int cedulaProfesional;
    private String nombre;
    private String firmaClinicoResponsable;
    
    public MedicoVeterinario() {}

    public MedicoVeterinario(int cedulaProfesional, String nombre, String firmaClinicoResponsable) {
        this.cedulaProfesional = cedulaProfesional;
        this.nombre = nombre;
        this.firmaClinicoResponsable = firmaClinicoResponsable;
    }

    public int getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(int cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFirmaClinicoResponsable() {
        return firmaClinicoResponsable;
    }

    public void setFirmaClinicoResponsable(String firmaClinicoResponsable) {
        this.firmaClinicoResponsable = firmaClinicoResponsable;
    }

    @Override
    public String toString() {
        return "MedicoVeterinario{" + "cedulaProfesional=" + cedulaProfesional + ", nombre=" + nombre + ", firmaClinicoResponsable=" + firmaClinicoResponsable + '}';
    }
    
    
    
}
