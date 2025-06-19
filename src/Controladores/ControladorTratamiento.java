package Controladores;

import BaseDatos.TratamientoDAO;
import Modelo.Tratamiento;
import java.util.List;
import java.sql.Connection;

/**
 * Controlador de negocio para Tratamiento.
 */
public class ControladorTratamiento {
    private final TratamientoDAO dao;

    public ControladorTratamiento(Connection conexion) {
        this.dao = new TratamientoDAO(conexion);
    }

    /**
     * Inserta un nuevo tratamiento.
     */
    public boolean agregarTratamiento(Tratamiento t) {
        return dao.agregarTratamiento(t);
    }

    /**
     * Devuelve la lista de todos los tratamientos.
     */
    public List<Tratamiento> obtenerTodos() {
        return dao.obtenerTodos();
    }
}
