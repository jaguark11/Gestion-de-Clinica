// src/Modelo/Procedimiento.java
package Modelo;

/**
 * Representa un Procedimiento médico asociado a un Tratamiento.
 */
public class Procedimiento {
    private int idProcedimiento;
    private int idTratamiento;
    private String nombre;
    private String nombreTratamiento;

    public Procedimiento() { }

    public Procedimiento(int idProcedimiento, int idTratamiento,
                         String nombre, String nombreTratamiento) {
        this.idProcedimiento = idProcedimiento;
        this.idTratamiento   = idTratamiento;
        this.nombre          = nombre;
        this.nombreTratamiento = nombreTratamiento;
    }

    public int getIdProcedimiento() {
        return idProcedimiento;
    }
    public void setIdProcedimiento(int idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
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

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }
    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }
}
