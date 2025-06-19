/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.DiagnosticoIntegral;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class DiagnosticoIntegralDAO {
    
    // Agregar diagnóstico integral
    public boolean agregarDiagnostico(DiagnosticoIntegral d) {
        String sql = "INSERT INTO diagnosticos_integrales(id_diagnostico_integral, nombre, folio_hc, fecha_hc) VALUES (?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getIdDiagnosticoIntegral());
            ps.setString(2, d.getNombre());
            ps.setInt(3, d.getFolioHc());
            ps.setDate(4, new java.sql.Date(d.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar diagnóstico integral: " + ex.getMessage());
            return false;
        }
    }

    // Buscar diagnóstico integral por ID
    public DiagnosticoIntegral obtenerDiagnosticoPorId(int idDiagnostico) {
        String sql = "SELECT * FROM diagnosticos_integrales WHERE id_diagnostico_integral = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDiagnostico);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DiagnosticoIntegral(
                    rs.getInt("id_diagnostico_integral"),
                    rs.getString("nombre"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar diagnóstico integral: " + ex.getMessage());
        }
        return null;
    }

    // Listar todos los diagnósticos integrales
    public List<DiagnosticoIntegral> obtenerTodosLosDiagnosticos() {
        List<DiagnosticoIntegral> lista = new ArrayList<>();
        String sql = "SELECT * FROM diagnosticos_integrales";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                DiagnosticoIntegral d = new DiagnosticoIntegral(
                    rs.getInt("id_diagnostico_integral"),
                    rs.getString("nombre"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc")
                );
                lista.add(d);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar diagnósticos integrales: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar diagnóstico integral
    public boolean actualizarDiagnostico(DiagnosticoIntegral d) {
        String sql = "UPDATE diagnosticos_integrales SET nombre=?, folio_hc=?, fecha_hc=? WHERE id_diagnostico_integral=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getFolioHc());
            ps.setDate(3, new java.sql.Date(d.getFechaHc().getTime()));
            ps.setInt(4, d.getIdDiagnosticoIntegral());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar diagnóstico integral: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar diagnóstico integral por ID
    public boolean eliminarDiagnostico(int idDiagnostico) {
        String sql = "DELETE FROM diagnosticos_integrales WHERE id_diagnostico_integral=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDiagnostico);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar diagnóstico integral: " + ex.getMessage());
            return false;
        }
    }
    
}
