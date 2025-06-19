/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.Antecedente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class AntecedenteDAO {
    
    // Agregar antecedente
    public boolean agregarAntecedente(Antecedente a) {
        String sql = "INSERT INTO antecedentes(id_antecedente, contenido, dieta_anamnesis, frecuencia_anamnesis, modo_anamnesis, tiempo_posesion_caballo_anamnesis, otro_animal_anamnesis, medio_ambiente_anamnesis) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdAntecedente());
            ps.setString(2, a.getContenido());
            ps.setString(3, a.getDietaAnamnesis());
            ps.setString(4, a.getFrecuenciaAnamnesis());
            ps.setString(5, a.getModoAnamnesis());
            ps.setDate(6, new java.sql.Date(a.getTiempoPosesionCaballoAnamnesis().getTime()));
            ps.setBoolean(7, a.isOtroAnimalAnamnesis());
            ps.setString(8, a.getMedioAmbienteAnamnesis());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar antecedente: " + ex.getMessage());
            return false;
        }
    }

    // Buscar antecedente por ID
    public Antecedente obtenerAntecedentePorId(int idAntecedente) {
        String sql = "SELECT * FROM antecedentes WHERE id_antecedente = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAntecedente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Antecedente(
                    rs.getInt("id_antecedente"),
                    rs.getString("contenido"),
                    rs.getString("dieta_anamnesis"),
                    rs.getString("frecuencia_anamnesis"),
                    rs.getString("modo_anamnesis"),
                    rs.getDate("tiempo_posesion_caballo_anamnesis"),
                    rs.getBoolean("otro_animal_anamnesis"),
                    rs.getString("medio_ambiente_anamnesis")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar antecedente: " + ex.getMessage());
        }
        return null;
    }

    // Listar todos los antecedentes
    public List<Antecedente> obtenerTodosLosAntecedentes() {
        List<Antecedente> lista = new ArrayList<>();
        String sql = "SELECT * FROM antecedentes";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Antecedente a = new Antecedente(
                    rs.getInt("id_antecedente"),
                    rs.getString("contenido"),
                    rs.getString("dieta_anamnesis"),
                    rs.getString("frecuencia_anamnesis"),
                    rs.getString("modo_anamnesis"),
                    rs.getDate("tiempo_posesion_caballo_anamnesis"),
                    rs.getBoolean("otro_animal_anamnesis"),
                    rs.getString("medio_ambiente_anamnesis")
                );
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar antecedentes: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar antecedente
    public boolean actualizarAntecedente(Antecedente a) {
        String sql = "UPDATE antecedentes SET contenido=?, dieta_anamnesis=?, frecuencia_anamnesis=?, modo_anamnesis=?, tiempo_posesion_caballo_anamnesis=?, otro_animal_anamnesis=?, medio_ambiente_anamnesis=? WHERE id_antecedente=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getContenido());
            ps.setString(2, a.getDietaAnamnesis());
            ps.setString(3, a.getFrecuenciaAnamnesis());
            ps.setString(4, a.getModoAnamnesis());
            ps.setDate(5, new java.sql.Date(a.getTiempoPosesionCaballoAnamnesis().getTime()));
            ps.setBoolean(6, a.isOtroAnimalAnamnesis());
            ps.setString(7, a.getMedioAmbienteAnamnesis());
            ps.setInt(8, a.getIdAntecedente());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar antecedente: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar antecedente por ID
    public boolean eliminarAntecedente(int idAntecedente) {
        String sql = "DELETE FROM antecedentes WHERE id_antecedente=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAntecedente);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar antecedente: " + ex.getMessage());
            return false;
        }
    }
    
}
