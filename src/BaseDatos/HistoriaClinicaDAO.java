/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.HistoriaClinica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class HistoriaClinicaDAO {
    
     // Agregar historia clínica
    public boolean agregarHistoria(HistoriaClinica h) {
        String sql = "INSERT INTO historias_clinicas(folio, fecha, id_propietario, id_paciente, cedula_profesional) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getFolio());
            ps.setDate(2, new java.sql.Date(h.getFecha().getTime()));
            ps.setInt(3, h.getIdPropietario());
            ps.setInt(4, h.getIdPaciente());
            ps.setInt(5, h.getCedulaProfesional());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar historia clínica: " + e.getMessage());
            return false;
        }
    }

    // Buscar historia por folio y fecha
    public HistoriaClinica obtenerHistoriaPorFolioYFecha(int folio, java.sql.Date fecha) {
        String sql = "SELECT * FROM historias_clinicas WHERE folio = ? AND fecha = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, folio);
            ps.setDate(2, fecha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new HistoriaClinica(
                    rs.getInt("folio"),
                    rs.getDate("fecha"),
                    rs.getInt("id_propietario"),
                    rs.getInt("id_paciente"),
                    rs.getInt("cedula_profesional")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar historia clínica: " + e.getMessage());
        }
        return null;
    }

    // Listar todas las historias clínicas
    public List<HistoriaClinica> obtenerTodasLasHistorias() {
        List<HistoriaClinica> lista = new ArrayList<>();
        String sql = "SELECT * FROM historias_clinicas";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                HistoriaClinica h = new HistoriaClinica(
                    rs.getInt("folio"),
                    rs.getDate("fecha"),
                    rs.getInt("id_propietario"),
                    rs.getInt("id_paciente"),
                    rs.getInt("cedula_profesional")
                );
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar historias clínicas: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar historia clínica
    public boolean actualizarHistoria(HistoriaClinica h) {
        String sql = "UPDATE historias_clinicas SET id_propietario=?, id_paciente=?, cedula_profesional=? WHERE folio=? AND fecha=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getIdPropietario());
            ps.setInt(2, h.getIdPaciente());
            ps.setInt(3, h.getCedulaProfesional());
            ps.setInt(4, h.getFolio());
            ps.setDate(5, new java.sql.Date(h.getFecha().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar historia clínica: " + e.getMessage());
            return false;
        }
    }

    // Eliminar historia clínica
    public boolean eliminarHistoria(int folio, java.sql.Date fecha) {
        String sql = "DELETE FROM historias_clinicas WHERE folio=? AND fecha=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, folio);
            ps.setDate(2, fecha);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar historia clínica: " + e.getMessage());
            return false;
        }
    }
    
}
