package Modelo;    // o el paquete que hayas elegido: e.g. package Pruebas;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection c = ConexionBD.conectar();
        if (c != null) {
            System.out.println("✅ Conexión OK");
            try { c.close(); } catch (Exception ignore) {}
        } else {
            System.out.println("❌ Error en la conexión");
        }
    }
}
