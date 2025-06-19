package Controladores;

import Modelo.SignoFisicoAnormal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorSignoFisicoAnormal {
    private final Connection conexion;

    public ControladorSignoFisicoAnormal(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarSignoFisicoAnormal(SignoFisicoAnormal s) {
        String sql = "INSERT INTO SignoFisicoAnormal "
                   + "(idSignoFisicoAnormal, nombre, folioHc, fechaHc) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, s.getIdSignoFisicoAnormal());
            ps.setString(2, s.getNombre());
            ps.setInt(3, s.getFolioHc());
            ps.setDate(4, new Date(s.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar signo físico anormal: " + e.getMessage());
            return false;
        }
    }
}
