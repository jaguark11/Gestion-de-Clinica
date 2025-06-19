/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.SignoFisicoAnormal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class SignoFisicoAnormalDAO {
    
    // Agregar signo físico anormal
    public boolean agregarSignoFisicoAnormal(SignoFisicoAnormal s) {
        String sql = "INSERT INTO signos_fisicos_anormales(id_signo_fisico_anormal, nombre, folio_hc, fecha_hc) VALUES (?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getIdSignoFisicoAnormal());
            ps.setString(2, s.getNombre());
            ps.setInt(3, s.getFolioHc());
            ps.setDate(4, new java.sql.Date(s.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar signo físico anormal: " + ex.getMessage());
            return false;
        }
    }

    // Buscar signo físico anormal por ID
    public SignoFisicoAnormal obtenerSignoFisicoAnormalPorId(int idSigno) {
        String sql = "SELECT * FROM signos_fisicos_anormales WHERE id_signo_fisico_anormal = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSigno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SignoFisicoAnormal(
                    rs.getInt("id_signo_fisico_anormal"),
                    rs.getString("nombre"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar signo físico anormal: " + ex.getMessage());
        }
        return null;
    }

    // Listar todos los signos físicos anormales
    public List<SignoFisicoAnormal> obtenerTodosLosSignosFisicosAnormales() {
        List<SignoFisicoAnormal> lista = new ArrayList<>();
        String sql = "SELECT * FROM signos_fisicos_anormales";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                SignoFisicoAnormal s = new SignoFisicoAnormal(
                    rs.getInt("id_signo_fisico_anormal"),
                    rs.getString("nombre"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc")
                );
                lista.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar signos físicos anormales: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar signo físico anormal
    public boolean actualizarSignoFisicoAnormal(SignoFisicoAnormal s) {
        String sql = "UPDATE signos_fisicos_anormales SET nombre=?, folio_hc=?, fecha_hc=? WHERE id_signo_fisico_anormal=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getNombre());
            ps.setInt(2, s.getFolioHc());
            ps.setDate(3, new java.sql.Date(s.getFechaHc().getTime()));
            ps.setInt(4, s.getIdSignoFisicoAnormal());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar signo físico anormal: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar signo físico anormal por ID
    public boolean eliminarSignoFisicoAnormal(int idSigno) {
        String sql = "DELETE FROM signos_fisicos_anormales WHERE id_signo_fisico_anormal=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSigno);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar signo físico anormal: " + ex.getMessage());
            return false;
        }
    }
    
}
