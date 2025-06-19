/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.ExamenComplementario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ExamenComplementarioDAO {
    
    // Agregar examen complementario
    public boolean agregarExamen(ExamenComplementario e) {
        String sql = "INSERT INTO examenes_complementarios(nombre_examen, folio_hc, fecha_hc) VALUES (?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombreExamen());
            ps.setInt(2, e.getFolioHc());
            ps.setDate(3, new java.sql.Date(e.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar examen complementario: " + ex.getMessage());
            return false;
        }
    }

    // Buscar examen complementario por clave primaria compuesta
    public ExamenComplementario obtenerExamenPorPK(String nombreExamen, int folioHc, java.util.Date fechaHc) {
        String sql = "SELECT * FROM examenes_complementarios WHERE nombre_examen = ? AND folio_hc = ? AND fecha_hc = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreExamen);
            ps.setInt(2, folioHc);
            ps.setDate(3, new java.sql.Date(fechaHc.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ExamenComplementario(
                    rs.getString("nombre_examen"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar examen complementario: " + ex.getMessage());
        }
        return null;
    }

    // Listar todos los exámenes complementarios
    public List<ExamenComplementario> obtenerTodosLosExamenes() {
        List<ExamenComplementario> lista = new ArrayList<>();
        String sql = "SELECT * FROM examenes_complementarios";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ExamenComplementario e = new ExamenComplementario(
                    rs.getString("nombre_examen"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar exámenes complementarios: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar nombre del examen complementario
    // (No se recomienda cambiar la PK compuesta, por lo general solo actualizas algún campo extra)
    // Aquí lo dejamos solo como ejemplo para campos modificables adicionales

    // Eliminar examen complementario por PK compuesta
    public boolean eliminarExamen(String nombreExamen, int folioHc, java.util.Date fechaHc) {
        String sql = "DELETE FROM examenes_complementarios WHERE nombre_examen=? AND folio_hc=? AND fecha_hc=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreExamen);
            ps.setInt(2, folioHc);
            ps.setDate(3, new java.sql.Date(fechaHc.getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar examen complementario: " + ex.getMessage());
            return false;
        }
    }
    
}
