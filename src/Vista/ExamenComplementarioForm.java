package Vista;

import Controladores.ControladorExamenComplementario;
import Modelo.ExamenComplementario;
import java.sql.Connection;
import java.sql.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExamenComplementarioForm {
    private final Connection conexion;

    public ExamenComplementarioForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Examen Complementario");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nombreField = new TextField();
        TextField folioField  = new TextField();
        TextField fechaField  = new TextField();

        grid.add(new Label("Nombre examen:"),           0, 0);
        grid.add(nombreField,                            1, 0);
        grid.add(new Label("Folio HC:"),                0, 1);
        grid.add(folioField,                             1, 1);
        grid.add(new Label("Fecha HC (YYYY-MM-DD):"),   0, 2);
        grid.add(fechaField,                             1, 2);

        Button btn = new Button("Guardar");
        grid.add(btn, 1, 3);

        btn.setOnAction(e -> {
            try {
                ControladorExamenComplementario ctrl = new ControladorExamenComplementario(conexion);
                ExamenComplementario ec = new ExamenComplementario();
                ec.setNombreExamen(nombreField.getText());
                ec.setFolioHc(Integer.parseInt(folioField.getText()));
                ec.setFechaHc(Date.valueOf(fechaField.getText()));

                boolean ok = ctrl.agregarExamenComplementario(ec);
                Alert a = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText(ok ? "Examen complementario guardado." : "Error al guardar.");
                a.show();
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Error inesperado");
                a.setContentText(ex.getMessage());
                a.show();
            }
        });

        stage.setScene(new Scene(grid, 480, 300));
        stage.show();
    }
}
