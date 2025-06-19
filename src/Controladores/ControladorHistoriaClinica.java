// Controladores/ControladorHistoriaClinica.java
package Controladores;

import Modelo.HistoriaClinica;

import java.sql.*;

public class ControladorHistoriaClinica {
    private final Connection conexion;

    public ControladorHistoriaClinica(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva Historia Clínica.
     * Ya no requiere idPropietario.
     */
    public boolean agregarHistoriaClinica(HistoriaClinica h) {
        // 1) Verificar que el paciente exista
        if (!existe("paciente", "id_paciente", h.getIdPaciente())) {
            System.err.println("El paciente con ID " + h.getIdPaciente() + " no existe.");
            return false;
        }

        // 2) Intentar el INSERT (sin id_propietario)
        String sql = "INSERT INTO historiaclinica "
                   + "(folio, fecha, id_paciente, cedula_profesional) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, h.getFolio());
            ps.setDate  (2, new java.sql.Date(h.getFecha().getTime()));
            ps.setInt   (3, h.getIdPaciente());
            ps.setInt   (4, h.getCedulaProfesional());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar historia clínica: " + e.getMessage());
            return false;
        }
    }

    private boolean existe(String tabla, String columna, int valor) {
        String sql = String.format("SELECT 1 FROM %s WHERE %s = ?", tabla, columna);
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, valor);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
