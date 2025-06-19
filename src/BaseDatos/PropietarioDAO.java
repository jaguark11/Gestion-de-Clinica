package BaseDatos;

import Modelo.Propietario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Propietario.
 */
public class PropietarioDAO {

    private final Connection conexion;

    public PropietarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un nuevo propietario. id_propietario es AUTO_INCREMENT en la BBDD.
     */
    public boolean agregarPropietario(Propietario p) {
        String sql = "INSERT INTO propietario (nombre, telefono, domicilio) VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDomicilio());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        p.setIdPropietario(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar propietario: " + e.getMessage());
        }
        return false;
    }

    /**
     * Recupera un propietario por ID (incluye eliminados si includeDeleted=true).
     */
    public Propietario obtenerPorId(int id) {
        String sql = "SELECT * FROM propietario WHERE id_propietario=?"; 
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar propietario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Lista todos los propietarios.
     * @param includeDeleted si false solo trae los que eliminado=FALSE
     */
    public List<Propietario> obtenerTodos(boolean includeDeleted) {
        List<Propietario> lista = new ArrayList<>();
        String sql = "SELECT * FROM propietario"
                   + (includeDeleted ? "" : " WHERE eliminado = FALSE")
                   + " ORDER BY id_propietario DESC";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar propietarios (incluidos eliminados=" 
                                + includeDeleted + "): " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza los datos básicos de un propietario.
     */
    public boolean actualizarPropietario(Propietario p) {
        String sql = "UPDATE propietario SET nombre=?, telefono=?, domicilio=? "
                   + "WHERE id_propietario=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDomicilio());
            ps.setInt(4, p.getIdPropietario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar propietario: " + e.getMessage());
        }
        return false;
    }

    /**
     * Borrado lógico: marca eliminado = TRUE.
     */
    public boolean eliminarLogico(int id) {
        String sql = "UPDATE propietario SET eliminado = TRUE WHERE id_propietario=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar propietario (lógico): " + e.getMessage());
        }
        return false;
    }

    /**
     * Restaura un registro marcado como eliminado.
     */
    public boolean restaurarPropietario(int id) {
        String sql = "UPDATE propietario SET eliminado = FALSE WHERE id_propietario=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al restaurar propietario: " + e.getMessage());
        }
        return false;
    }

    /**
     * Eliminación física del registro.
     */
    public boolean eliminarFisico(int id) {
        String sql = "DELETE FROM propietario WHERE id_propietario=?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar propietario (físico): " + e.getMessage());
        }
        return false;
    }

    /**
     * Mapea una fila de ResultSet a un objeto Propietario.
     */
    private Propietario mapRow(ResultSet rs) throws SQLException {
    Propietario p = new Propietario();
    p.setIdPropietario( rs.getInt("id_propietario") );
    p.setNombre(        rs.getString("nombre")     );
    // <<<<<<<< aquí corregimos:
    p.setTelefono(      rs.getString("telefono")   );
    p.setDomicilio(     rs.getString("domicilio")  );
    p.setDeleted(       rs.getBoolean("eliminado") );
    p.setCreatedAt(     rs.getTimestamp("created_at").toLocalDateTime() );
    return p;
}

}
