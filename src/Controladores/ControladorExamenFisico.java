package Controladores;

import Modelo.ExamenFisico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorExamenFisico {
    private final Connection conexion;

    public ControladorExamenFisico(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarExamenFisico(ExamenFisico ex) {
        String sql = "INSERT INTO ExamenFisico "
                   + "(idExamen, folioHc, fechaHc, temperatura, pulso, llenadoCapilar, mucosas) "
                   + "VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, ex.getIdExamen());
            ps.setInt(2, ex.getFolioHc());
            ps.setDate(3, new Date(ex.getFechaHc().getTime()));
            ps.setDouble(4, ex.getTemperatura());
            ps.setString(5, ex.getPulso());
            ps.setString(6, ex.getLlenadoCapilar());
            ps.setString(7, ex.getMucosas());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar examen físico: " + e.getMessage());
            return false;
        }
    }
}
