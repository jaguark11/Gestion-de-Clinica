// Controladores/ControladorEnfermedad.java
package Controladores;

import Modelo.Enfermedad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorEnfermedad {
    private final Connection conexion;

    public ControladorEnfermedad(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarEnfermedad(Enfermedad e) {
        String sql = "INSERT INTO Enfermedad "
                   + "(idEnfermedad, folioHc, fechaHc, nombre, padecido, infecciosa, tipo, año) "
                   + "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, e.getIdEnfermedad());
            ps.setInt   (2, e.getFolioHc());
            ps.setDate  (3, new Date(e.getFechaHc().getTime()));
            ps.setString(4, e.getNombre());
            ps.setString(5, e.getPadecido());
            ps.setString(6, e.getInfecciosa());
            ps.setString(7, e.getTipo());
            ps.setInt   (8, e.getAño());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error al insertar enfermedad: " + ex.getMessage());
            return false;
        }
    }
}
