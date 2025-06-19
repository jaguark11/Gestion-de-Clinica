package BaseDatos;

import Modelo.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private final Connection conexion;

    public PacienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int agregarPaciente(Paciente p) {
        String sql = "INSERT INTO paciente (nombre, especie, raza, sexo, edad, color, peso, sena_particular, id_propietario) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getEspecie());
            stmt.setString(3, p.getRaza());
            stmt.setString(4, p.getSexo());
            stmt.setInt(5, p.getEdad());
            stmt.setString(6, p.getColor());
            stmt.setDouble(7, p.getPeso());
            stmt.setString(8, p.getSena_particular());

            // Asignar automáticamente el primer propietario existente
            int propietarioId = obtenerPrimerIdPropietario();
            if (propietarioId == -1) {
                System.err.println("No hay propietarios registrados. No se puede agregar paciente.");
                return -1;
            }
            stmt.setInt(9, propietarioId);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // ID generado
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Paciente> obtenerTodos(boolean incluirEliminados) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente" + (incluirEliminados ? "" : " WHERE eliminado = FALSE");

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId_paciente(rs.getInt("id_paciente"));
                p.setNombre(rs.getString("nombre"));
                p.setEspecie(rs.getString("especie"));
                p.setRaza(rs.getString("raza"));
                p.setSexo(rs.getString("sexo"));
                p.setEdad(rs.getInt("edad"));
                p.setColor(rs.getString("color"));
                p.setPeso(rs.getDouble("peso"));
                p.setSena_particular(rs.getString("sena_particular"));
                p.setEliminado(rs.getBoolean("eliminado"));
                p.setCreated_at(rs.getTimestamp("created_at").toString());
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminarLogico(int id) {
        String sql = "UPDATE paciente SET eliminado = TRUE WHERE id_paciente = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean restaurarPaciente(int id) {
        String sql = "UPDATE paciente SET eliminado = FALSE WHERE id_paciente = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Este método es clave para acceder desde fuera a la conexión:
    public Connection getConexion() {
        return this.conexion;
    }

    // Obtener el primer propietario disponible
    private int obtenerPrimerIdPropietario() {
        String sql = "SELECT id_propietario FROM propietario LIMIT 1";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("id_propietario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    

    public Paciente obtenerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Paciente> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void eliminarFisico(int idPaciente) {
    String sql = "DELETE FROM paciente WHERE id_paciente = ?";
    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, idPaciente);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al eliminar paciente físicamente");
    }
}

}