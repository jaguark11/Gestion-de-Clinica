package Controladores;

import Modelo.ExamenComplementario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorExamenComplementario {
    private final Connection conexion;

    public ControladorExamenComplementario(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarExamenComplementario(ExamenComplementario ec) {
        String sql = "INSERT INTO ExamenComplementario "
                   + "(nombreExamen, folioHc, fechaHc) "
                   + "VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, ec.getNombreExamen());
            ps.setInt(2, ec.getFolioHc());
            ps.setDate(3, new Date(ec.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar examen complementario: " + e.getMessage());
            return false;
        }
    }
}
