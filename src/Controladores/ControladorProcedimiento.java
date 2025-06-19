package Controladores;

import Modelo.Procedimiento;    // ← Asegúrate de que coincida mayúsculas/minúsculas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorProcedimiento {
    private final Connection conexion;

    public ControladorProcedimiento(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarProcedimiento(Procedimiento p) {
        String sql = "INSERT INTO procedimiento "
                   + "(id_procedimiento, id_tratamiento, nombre, nombre_tratamiento) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, p.getIdProcedimiento());
            ps.setInt   (2, p.getIdTratamiento());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getNombreTratamiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar procedimiento: " + e.getMessage());
            return false;
        }
    }
}
