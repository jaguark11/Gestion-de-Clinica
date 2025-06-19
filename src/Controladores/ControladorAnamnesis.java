// Controladores/ControladorAnamnesis.java
package Controladores;

import Modelo.Anamnesis;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorAnamnesis {
    private final Connection conexion;

    public ControladorAnamnesis(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarAnamnesis(Anamnesis a) {
        String sql = "INSERT INTO Anamnesis "
                   + "(folioHc, fechaHc, dieta, frecuencia, modo, tiempoPosesionCaballo, otroAnimal, medioAmbiente) "
                   + "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, a.getFolioHc());
            ps.setDate  (2, new Date(a.getFechaHc().getTime()));
            ps.setString(3, a.getDieta());
            ps.setString(4, a.getFrecuencia());
            ps.setString(5, a.getModo());
            ps.setDate  (6, new Date(a.getTiempoPosesionCaballo().getTime()));
            ps.setBoolean(7, a.isOtroAnimal());
            ps.setString(8, a.getMedioAmbiente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar anamnesis: " + e.getMessage());
            return false;
        }
    }
}
