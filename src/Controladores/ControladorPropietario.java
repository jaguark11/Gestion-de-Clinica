// Controladores/ControladorPropietario.java
package Controladores;

import Modelo.Propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorPropietario {
    private final Connection conexion;

    public ControladorPropietario(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo propietario.
     * Asume que id_propietario es AUTO_INCREMENT en la BD.
     */
    public boolean agregarPropietario(Propietario p) {
        String sql = 
            "INSERT INTO propietario (nombre, telefono, domicilio) VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDomicilio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar propietario: " + e.getMessage());
            return false;
        }
    }

    // Aquí podrías agregar métodos como actualizarPropietario, eliminarPropietario, etc.
}
