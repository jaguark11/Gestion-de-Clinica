// Controladores/ControladorAntecedente.java
package Controladores;

import Modelo.Antecedente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControladorAntecedente {
    private final Connection conexion;

    public ControladorAntecedente(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarAntecedente(Antecedente a) {
        String sql = "INSERT INTO Antecedente "
                   + "(idAntecedente, contenido, dietaAnamnesis, frecuenciaAnamnesis,"
                   + "modoAnamnesis, tiempoPosesionCaballoAnamnesis,"
                   + "otroAnimalAnamnesis, medioAmbienteAnamnesis) "
                   + "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, a.getIdAntecedente());
            ps.setString(2, a.getContenido());
            ps.setString(3, a.getDietaAnamnesis());
            ps.setString(4, a.getFrecuenciaAnamnesis());
            ps.setString(5, a.getModoAnamnesis());
            ps.setDate  (6, new Date(a.getTiempoPosesionCaballoAnamnesis().getTime()));
            ps.setBoolean(7, a.isOtroAnimalAnamnesis());
            ps.setString(8, a.getMedioAmbienteAnamnesis());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar antecedente: " + e.getMessage());
            return false;
        }
    }
}

