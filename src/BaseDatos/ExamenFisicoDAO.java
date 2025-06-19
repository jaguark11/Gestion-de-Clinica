/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.ExamenFisico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ExamenFisicoDAO {
    
    // Agregar examen físico
    public boolean agregarExamenFisico(ExamenFisico e) {
        String sql = "INSERT INTO examenes_fisicos(id_examen, folio_hc, fecha_hc, temperatura, pulso, caracteristicas, frecuencia_cardiaca, frecuencia_respiratoria, llenado_capilar, mucosas, motilidad, pulso_digital, aspecto_general, aparato_locomotor, aparato_circulatorio, aparato_digestivo, aparato_genitourinario, sistema_nervioso, oidos, ojos, ganglios_linfaticos, piel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getIdExamen());
            ps.setInt(2, e.getFolioHc());
            ps.setDate(3, new java.sql.Date(e.getFechaHc().getTime()));
            ps.setDouble(4, e.getTemperatura());
            ps.setString(5, e.getPulso());
            ps.setString(6, e.getCaracteristicas());
            ps.setInt(7, e.getFrecuenciaCardiaca());
            ps.setInt(8, e.getFrecuenciaRespiratoria());
            ps.setString(9, e.getLlenadoCapilar());
            ps.setString(10, e.getMucosas());
            ps.setString(11, e.getMotilidad());
            ps.setString(12, e.getPulsoDigital());
            ps.setString(13, e.getAspectoGeneral());
            ps.setString(14, e.getAparatoLocomotor());
            ps.setString(15, e.getAparatoCirculatorio());
            ps.setString(16, e.getAparatoDigestivo());
            ps.setString(17, e.getAparatoGenitourinario());
            ps.setString(18, e.getSistemaNervioso());
            ps.setString(19, e.getOidos());
            ps.setString(20, e.getOjos());
            ps.setString(21, e.getGangliosLinfaticos());
            ps.setString(22, e.getPiel());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar examen físico: " + ex.getMessage());
            return false;
        }
    }

    // Buscar examen físico por PK compuesta
    public ExamenFisico obtenerExamenFisicoPorPK(int idExamen, int folioHc, java.util.Date fechaHc) {
        String sql = "SELECT * FROM examenes_fisicos WHERE id_examen = ? AND folio_hc = ? AND fecha_hc = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idExamen);
            ps.setInt(2, folioHc);
            ps.setDate(3, new java.sql.Date(fechaHc.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ExamenFisico(
                    rs.getInt("id_examen"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc"),
                    rs.getDouble("temperatura"),
                    rs.getString("pulso"),
                    rs.getString("caracteristicas"),
                    rs.getInt("frecuencia_cardiaca"),
                    rs.getInt("frecuencia_respirtoria"),
                    rs.getString("llenado_capilar"),
                    rs.getString("mucosas"),
                    rs.getString("motilidad"),
                    rs.getString("pulso_digital"),
                    rs.getString("aspecto_general"),
                    rs.getString("aparato_locomotor"),
                    rs.getString("aparato_circulatorio"),
                    rs.getString("aparato_digestivo"),
                    rs.getString("aparato_genitourinario"),
                    rs.getString("sistema_nervioso"),
                    rs.getString("oidos"),
                    rs.getString("ojos"),
                    rs.getString("ganglios_linfaticos"),
                    rs.getString("piel")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar examen físico: " + ex.getMessage());
        }
        return null;
    }

    // Listar todos los exámenes físicos
    public List<ExamenFisico> obtenerTodosLosExamenesFisicos() {
        List<ExamenFisico> lista = new ArrayList<>();
        String sql = "SELECT * FROM examenes_fisicos";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ExamenFisico e = new ExamenFisico(
                    rs.getInt("id_examen"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc"),
                    rs.getDouble("temperatura"),
                    rs.getString("pulso"),
                    rs.getString("caracteristicas"),
                    rs.getInt("frecuencia_cardiaca"),
                    rs.getInt("frecuencia_respirtoria"),
                    rs.getString("llenado_capilar"),
                    rs.getString("mucosas"),
                    rs.getString("motilidad"),
                    rs.getString("pulso_digital"),
                    rs.getString("aspecto_general"),
                    rs.getString("aparato_locomotor"),
                    rs.getString("aparato_circulatorio"),
                    rs.getString("aparato_digestivo"),
                    rs.getString("aparato_genitourinario"),
                    rs.getString("sistema_nervioso"),
                    rs.getString("oidos"),
                    rs.getString("ojos"),
                    rs.getString("ganglios_linfaticos"),
                    rs.getString("piel")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar exámenes físicos: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar examen físico por PK compuesta
    public boolean actualizarExamenFisico(ExamenFisico e) {
        String sql = "UPDATE examenes_fisicos SET temperatura=?, pulso=?, caracteristicas=?, frecuencia_cardiaca=?, frecuencia_respirtoria=?, llenado_capilar=?, mucosas=?, motilidad=?, pulso_digital=?, aspecto_general=?, aparato_locomotor=?, aparato_circulatorio=?, aparato_digestivo=?, aparato_genitourinario=?, sistema_nervioso=?, oidos=?, ojos=?, ganglios_linfaticos=?, piel=? WHERE id_examen=? AND folio_hc=? AND fecha_hc=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, e.getTemperatura());
            ps.setString(2, e.getPulso());
            ps.setString(3, e.getCaracteristicas());
            ps.setInt(4, e.getFrecuenciaCardiaca());
            ps.setInt(5, e.getFrecuenciaRespiratoria());
            ps.setString(6, e.getLlenadoCapilar());
            ps.setString(7, e.getMucosas());
            ps.setString(8, e.getMotilidad());
            ps.setString(9, e.getPulsoDigital());
            ps.setString(10, e.getAspectoGeneral());
            ps.setString(11, e.getAparatoLocomotor());
            ps.setString(12, e.getAparatoCirculatorio());
            ps.setString(13, e.getAparatoDigestivo());
            ps.setString(14, e.getAparatoGenitourinario());
            ps.setString(15, e.getSistemaNervioso());
            ps.setString(16, e.getOidos());
            ps.setString(17, e.getOjos());
            ps.setString(18, e.getGangliosLinfaticos());
            ps.setString(19, e.getPiel());
            ps.setInt(20, e.getIdExamen());
            ps.setInt(21, e.getFolioHc());
            ps.setDate(22, new java.sql.Date(e.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar examen físico: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar examen físico por PK compuesta
    public boolean eliminarExamenFisico(int idExamen, int folioHc, java.util.Date fechaHc) {
        String sql = "DELETE FROM examenes_fisicos WHERE id_examen=? AND folio_hc=? AND fecha_hc=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idExamen);
            ps.setInt(2, folioHc);
            ps.setDate(3, new java.sql.Date(fechaHc.getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar examen físico: " + ex.getMessage());
            return false;
        }
    }
    
}
