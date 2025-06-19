package Controladores;

import BaseDatos.PacienteDAO;
import Modelo.Paciente;
import java.sql.Connection;
import java.util.List;

public class ControladorPaciente {

    private final PacienteDAO dao;

    public ControladorPaciente(Connection conexion) {
        // delega toda la lógica SQL al DAO
        this.dao = new PacienteDAO(conexion);
    }

    /** Llama al DAO para insertar */
    public int agregarPaciente(Paciente p) {
        return dao.agregarPaciente(p);
    }

    /** Ejemplo de otros métodos */
    public Paciente buscarPaciente(int id) {
        return dao.obtenerPorId(id);
    }

    public List<Paciente> listarPacientes() {
        return dao.obtenerTodos();
    }
}
