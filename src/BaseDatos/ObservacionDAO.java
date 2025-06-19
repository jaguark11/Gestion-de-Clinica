package BaseDatos;

import Modelo.Observacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Observacion.
 */
public class ObservacionDAO {

    private final Connection conexion;

    /**
     * Recibe la conexión desde afuera (por ejemplo desde ConexionBD.conectar()).
     */
    public ObservacionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una nueva observación en la base de datos.
     */
    public boolean agregarObservacion(Observacion o) {
        String sql = "INSERT INTO observacion "
                   + "(id_observacion, folio_hc, fecha_hc, contenido) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt   (1, o.getIdObservacion());
            ps.setInt   (2, o.getFolioHc());
            ps.setDate  (3, o.getFechaHc());
            ps.setString(4, o.getContenido());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar observación: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene una observación por su ID.
     */
    public Observacion obtenerPorId(int id) {
        String sql = "SELECT * FROM observacion WHERE id_observacion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Observacion(
                        rs.getInt("id_observacion"),
                        rs.getInt("folio_hc"),
                        rs.getDate("fecha_hc"),
                        rs.getString("contenido")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar observación: " + e.getMessage());
        }
        return null;
    }

    /**
     * Lista todas las observaciones.
     */
    public List<Observacion> obtenerTodos() {
        List<Observacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM observacion ORDER BY id_observacion DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Observacion(
                    rs.getInt("id_observacion"),
                    rs.getInt("folio_hc"),
                    rs.getDate("fecha_hc"),
                    rs.getString("contenido")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar observaciones: " + e.getMessage());
        }
        return lista;
    }

    // Aquí podrías agregar métodos de actualizar, eliminar lógico/físico, etc.
}
