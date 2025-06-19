/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.Anamnesis;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class AnamnesisDAO {
    
    // Agregar anamnesis
    public boolean agregarAnamnesis(Anamnesis a) {
        String sql = "INSERT INTO anamnesis(folio_hc, fecha_hc, dieta, frecuencia, modo, tiempo_posesion_caballo, otro_animal, medio_ambiente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getFolioHc());
            ps.setDate(2, new java.sql.Date(a.getFechaHc().getTime()));
            ps.setString(3, a.getDieta());
            ps.setString(4, a.getFrecuencia());
            ps.setString(5, a.getModo());
            ps.setDate(6, new java.sql.Date(a.getTiempoPosesionCaballo().getTime()));
            ps.setBoolean(7, a.isOtroAnimal());
            ps.setString(8, a.getMedioAmbiente());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar anamnesis: " + ex.getMessage());
            return false;
        }
    }

    // Buscar anamnesis por PK compuesta
    public Anamnesis obtenerAnamnesisPorPK(int folioHc, java.util.Date fechaHc) {
        String sql = "SELECT * FROM anamnesis WHERE folio_hc = ? AND fecha_hc = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, folioHc);
            ps.setDate(2, new java.sql.Date(fechaHc.getTime()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Anamnesis(
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc"),
                    rs.getString("dieta"),
                    rs.getString("frecuencia"),
                    rs.getString("modo"),
                    rs.getDate("tiempo_posesion_caballo"),
                    rs.getBoolean("otro_animal"),
                    rs.getString("medio_ambiente")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar anamnesis: " + ex.getMessage());
        }
        return null;
    }

    // Listar todas las anamnesis
    public List<Anamnesis> obtenerTodasLasAnamnesis() {
        List<Anamnesis> lista = new ArrayList<>();
        String sql = "SELECT * FROM anamnesis";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Anamnesis a = new Anamnesis(
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc"),
                    rs.getString("dieta"),
                    rs.getString("frecuencia"),
                    rs.getString("modo"),
                    rs.getDate("tiempo_posesion_caballo"),
                    rs.getBoolean("otro_animal"),
                    rs.getString("medio_ambiente")
                );
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar anamnesis: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar anamnesis por PK compuesta
    public boolean actualizarAnamnesis(Anamnesis a) {
        String sql = "UPDATE anamnesis SET dieta=?, frecuencia=?, modo=?, tiempo_posesion_caballo=?, otro_animal=?, medio_ambiente=? WHERE folio_hc=? AND fecha_hc=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getDieta());
            ps.setString(2, a.getFrecuencia());
            ps.setString(3, a.getModo());
            ps.setDate(4, new java.sql.Date(a.getTiempoPosesionCaballo().getTime()));
            ps.setBoolean(5, a.isOtroAnimal());
            ps.setString(6, a.getMedioAmbiente());
            ps.setInt(7, a.getFolioHc());
            ps.setDate(8, new java.sql.Date(a.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar anamnesis: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar anamnesis por PK compuesta
    public boolean eliminarAnamnesis(int folioHc, java.util.Date fechaHc) {
        String sql = "DELETE FROM anamnesis WHERE folio_hc=? AND fecha_hc=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, folioHc);
            ps.setDate(2, new java.sql.Date(fechaHc.getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar anamnesis: " + ex.getMessage());
            return false;
        }
    }
    
}
