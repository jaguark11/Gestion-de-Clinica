/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.MedicoVeterinario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lenovo
 */
public class MedicoVeterinarioDAO {
    
    // Agregar médico veterinario
    public boolean agregarMedico(MedicoVeterinario m) {
        String sql = "INSERT INTO medicos_veterinarios(cedula_profesional, nombre, firma_clinico_responsable) VALUES (?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, m.getCedulaProfesional());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getFirmaClinicoResponsable());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al agregar médico veterinario: " + e.getMessage());
            return false;
        }
    }

    // Buscar médico por cédula
    public MedicoVeterinario obtenerMedicoPorCedula(int cedula) {
        String sql = "SELECT * FROM medicos_veterinarios WHERE cedula_profesional = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new MedicoVeterinario(
                    rs.getInt("cedula_profesional"),
                    rs.getString("nombre"),
                    rs.getString("firma_clinico_responsable")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar médico veterinario: " + e.getMessage());
        }
        return null;
    }

    // Listar todos los médicos veterinarios
    public List<MedicoVeterinario> obtenerTodosLosMedicos() {
        List<MedicoVeterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM medicos_veterinarios";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MedicoVeterinario m = new MedicoVeterinario(
                    rs.getInt("cedula_profesional"),
                    rs.getString("nombre"),
                    rs.getString("firma_clinico_responsable")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar médicos veterinarios: " + e.getMessage());
        }
        return lista;
    }

    // Actualizar médico veterinario
    public boolean actualizarMedico(MedicoVeterinario m) {
        String sql = "UPDATE medicos_veterinarios SET nombre=?, firma_clinico_responsable=? WHERE cedula_profesional=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getFirmaClinicoResponsable());
            ps.setInt(3, m.getCedulaProfesional());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar médico veterinario: " + e.getMessage());
            return false;
        }
    }

    // Eliminar médico veterinario
    public boolean eliminarMedico(int cedula) {
        String sql = "DELETE FROM medicos_veterinarios WHERE cedula_profesional=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cedula);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar médico veterinario: " + e.getMessage());
            return false;
        }
    }
    
}
