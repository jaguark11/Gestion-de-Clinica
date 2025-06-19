// Controladores/ControladorVacunacion.java
package Controladores;

import Modelo.Vacunacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorVacunacion {
    private final Connection conexion;

    public ControladorVacunacion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva vacunación.
     * Asume que id_vacuna es AUTO_INCREMENT, por eso no lo incluimos en la lista de columnas.
     */
    public boolean agregarVacunacion(Vacunacion v) {
        String sql = "INSERT INTO vacunacion " +
                     "(folio_hc, fecha_hc, otra_vacuna, fecha_aplicacion) " +
                     "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1,   v.getFolioHc());
            ps.setDate(2, (Date) v.getFechaHc());
            ps.setString(3,v.getOtraVacuna());
            ps.setDate(4, (Date) v.getFechaAplicacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar vacunación: " + e.getMessage());
            return false;
        }
    }
}
