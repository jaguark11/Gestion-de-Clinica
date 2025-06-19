package Controladores;

import Modelo.Observacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorObservacion {
    private final Connection conexion;

    public ControladorObservacion(Connection conexion) {
        this.conexion = conexion;
    }

    /** Inserta una nueva observación en la BBDD */
    public boolean agregarObservacion(Observacion o) {
        String sql = "INSERT INTO observacion "
                   + "(id_observacion, folio_hc, fecha_hc, contenido) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, o.getIdObservacion());
            ps.setInt   (2, o.getFolioHc());
            ps.setDate  (3, o.getFechaHc());
            ps.setString(4, o.getContenido());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar observación: " + e.getMessage());
            return false;
        }
    }
}
