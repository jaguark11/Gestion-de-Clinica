/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;
import Modelo.Procedimiento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ProcedimientoDAO {
    
    // Agregar procedimiento
    public boolean agregarProcedimiento(Procedimiento p) {
        String sql = "INSERT INTO procedimientos(id_procedimiento, id_tratamiento, nombre, nombre_tratamiento) VALUES (?, ?, ?, ?)";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdProcedimiento());
            ps.setInt(2, p.getIdTratamiento());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getNombreTratamiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al agregar procedimiento: " + ex.getMessage());
            return false;
        }
    }

    // Buscar procedimiento por ID
    public Procedimiento obtenerProcedimientoPorId(int idProcedimiento) {
        String sql = "SELECT * FROM procedimientos WHERE id_procedimiento = ?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProcedimiento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Procedimiento(
                    rs.getInt("id_procedimiento"),
                    rs.getInt("id_tratamiento"),
                    rs.getString("nombre"),
                    rs.getString("nombre_tratamiento")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar procedimiento: " + ex.getMessage());
        }
        return null;
    }

    // Listar todos los procedimientos
    public List<Procedimiento> obtenerTodosLosProcedimientos() {
        List<Procedimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM procedimientos";
        try (Connection con = BaseDatos.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Procedimiento p = new Procedimiento(
                    rs.getInt("id_procedimiento"),
                    rs.getInt("id_tratamiento"),
                    rs.getString("nombre"),
                    rs.getString("nombre_tratamiento")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar procedimientos: " + ex.getMessage());
        }
        return lista;
    }

    // Actualizar procedimiento
    public boolean actualizarProcedimiento(Procedimiento p) {
        String sql = "UPDATE procedimientos SET id_tratamiento=?, nombre=?, nombre_tratamiento=? WHERE id_procedimiento=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getIdTratamiento());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getNombreTratamiento());
            ps.setInt(4, p.getIdProcedimiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar procedimiento: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar procedimiento por ID
    public boolean eliminarProcedimiento(int idProcedimiento) {
        String sql = "DELETE FROM procedimientos WHERE id_procedimiento=?";
        try (Connection con = BaseDatos.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProcedimiento);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar procedimiento: " + ex.getMessage());
            return false;
        }
    }
    
}
