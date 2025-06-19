package BaseDatos;

import Modelo.Tratamiento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Tratamiento.
 */
public class TratamientoDAO {

    private final Connection conexion;

    /**
     * Recibe la conexión desde afuera (por ejemplo ConexionBD.conectar()).
     */
    public TratamientoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo tratamiento en la BBDD.
     */
    public boolean agregarTratamiento(Tratamiento t) {
        String sql = """
            INSERT INTO tratamiento
              (id_tratamiento, nombre, folio_hc, fecha_hc)
            VALUES (?,?,?,?)
            """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, t.getIdTratamiento());
            ps.setString(2, t.getNombre());
            ps.setInt(3, t.getFolioHc());
            ps.setDate(4, new Date(t.getFechaHc().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar tratamiento: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene un tratamiento por su ID.
     */
    public Tratamiento obtenerPorId(int id) {
        String sql = "SELECT * FROM tratamiento WHERE id_tratamiento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Tratamiento t = new Tratamiento();
                    t.setIdTratamiento(rs.getInt("id_tratamiento"));
                    t.setNombre(rs.getString("nombre"));
                    t.setFolioHc(rs.getInt("folio_hc"));
                    t.setFechaHc(rs.getDate("fecha_hc"));
                    return t;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar tratamiento: " + e.getMessage());
        }
        return null;
    }

    /**
     * Lista todos los tratamientos.
     */
    public List<Tratamiento> obtenerTodos() {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM tratamiento ORDER BY id_tratamiento DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(rs.getInt("id_tratamiento"));
                t.setNombre(rs.getString("nombre"));
                t.setFolioHc(rs.getInt("folio_hc"));
                t.setFechaHc(rs.getDate("fecha_hc"));
                lista.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tratamientos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza un tratamiento existente.
     */
    public boolean actualizarTratamiento(Tratamiento t) {
        String sql = """
            UPDATE tratamiento
               SET nombre   = ?,
                   folio_hc = ?,
                   fecha_hc = ?
             WHERE id_tratamiento = ?
            """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getFolioHc());
            ps.setDate(3, new Date(t.getFechaHc().getTime()));
            ps.setInt(4, t.getIdTratamiento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar tratamiento: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un tratamiento por su ID.
     */
    public boolean eliminarTratamiento(int id) {
        String sql = "DELETE FROM tratamiento WHERE id_tratamiento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar tratamiento: " + e.getMessage());
            return false;
        }
    }
}
