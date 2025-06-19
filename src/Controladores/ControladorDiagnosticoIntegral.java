// Controladores/ControladorDiagnosticoIntegral.java
package Controladores;

import Modelo.DiagnosticoIntegral;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorDiagnosticoIntegral {
    private final Connection conexion;

    public ControladorDiagnosticoIntegral(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarDiagnostico(DiagnosticoIntegral d) {
        String sql = "INSERT INTO DiagnosticoIntegral "
                   + "(idDiagnosticoIntegral, nombre, folioHc, fechaHc) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt  (1, d.getIdDiagnosticoIntegral());
            ps.setString(2, d.getNombre());
            ps.setInt  (3, d.getFolioHc());
            ps.setDate (4, new Date(d.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar diagnóstico integral: " + e.getMessage());
            return false;
        }
    }

    public boolean agregarDiagnosticoIntegral(DiagnosticoIntegral d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
