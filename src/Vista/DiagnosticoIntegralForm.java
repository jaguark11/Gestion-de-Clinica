// Vista/DiagnosticoIntegralForm.java
package Vista;

import Controladores.ControladorDiagnosticoIntegral;
import Modelo.DiagnosticoIntegral;
import Modelo.ConexionBD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.time.ZoneId;

public class DiagnosticoIntegralForm {
    private final Connection conexion;
    public DiagnosticoIntegralForm(Connection conexion) {
        this.conexion = conexion;
    }
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Diagnóstico Integral");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField    = new TextField();
        TextField nombreField= new TextField();
        TextField folioField = new TextField();
        TextField fechaField = new TextField();

        grid.add(new Label("ID Diagnóstico:"),0, 0);
        grid.add(idField,   1,0);
        grid.add(new Label("Nombre:"),        0,1);
        grid.add(nombreField,1,1);
        grid.add(new Label("Folio HC:"),      0,2);
        grid.add(folioField, 1,2);
        grid.add(new Label("Fecha HC (YYYY-MM-DD):"),0,3);
        grid.add(fechaField,1,3);

        Button btn = new Button("Guardar");
        grid.add(btn,1,4);

        btn.setOnAction(e -> {
            try {
                ControladorDiagnosticoIntegral ctrl = new ControladorDiagnosticoIntegral(conexion);
                DiagnosticoIntegral d = new DiagnosticoIntegral();
                d.setIdDiagnosticoIntegral(Integer.parseInt(idField.getText()));
                d.setNombre(nombreField.getText());
                d.setFolioHc(Integer.parseInt(folioField.getText()));
                d.setFechaHc(Date.valueOf(fechaField.getText()));
                boolean ok = ctrl.agregarDiagnosticoIntegral(d);
                Alert a = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText(ok ? "Diagnóstico guardado." : "Error al guardar.");
                a.show();
            } catch(Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText("Error: " + ex.getMessage());
                a.show();
            }
        });

        stage.setScene(new Scene(grid, 520, 340));
        stage.show();
    }
}
