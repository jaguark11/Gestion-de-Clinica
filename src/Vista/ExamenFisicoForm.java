package Vista;

import Controladores.ControladorExamenFisico;
import Modelo.ExamenFisico;
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

public class ExamenFisicoForm {
    private final Connection conexion;

    public ExamenFisicoForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Examen Físico");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField       = new TextField();
        TextField folioField    = new TextField();
        TextField fechaField    = new TextField();
        TextField tempField     = new TextField();
        TextField pulsoField    = new TextField();
        TextField llenadoField  = new TextField();
        TextField mucosasField  = new TextField();

        grid.add(new Label("ID Examen:"),              0, 0);
        grid.add(idField,                               1, 0);
        grid.add(new Label("Folio HC:"),               0, 1);
        grid.add(folioField,                            1, 1);
        grid.add(new Label("Fecha HC (YYYY-MM-DD):"),  0, 2);
        grid.add(fechaField,                            1, 2);
        grid.add(new Label("Temperatura:"),            0, 3);
        grid.add(tempField,                             1, 3);
        grid.add(new Label("Pulso:"),                  0, 4);
        grid.add(pulsoField,                            1, 4);
        grid.add(new Label("Llenado capilar:"),        0, 5);
        grid.add(llenadoField,                          1, 5);
        grid.add(new Label("Mucosas:"),                0, 6);
        grid.add(mucosasField,                          1, 6);

        Button btn = new Button("Guardar");
        grid.add(btn, 1, 7);

        btn.setOnAction(e -> {
            try {
                ControladorExamenFisico ctrl = new ControladorExamenFisico(conexion);
                ExamenFisico ex = new ExamenFisico();
                ex.setIdExamen(Integer.parseInt(idField.getText()));
                ex.setFolioHc(Integer.parseInt(folioField.getText()));
                ex.setFechaHc(Date.valueOf(fechaField.getText()));
                ex.setTemperatura(Double.parseDouble(tempField.getText()));
                ex.setPulso(pulsoField.getText());
                ex.setLlenadoCapilar(llenadoField.getText());
                ex.setMucosas(mucosasField.getText());

                boolean ok = ctrl.agregarExamenFisico(ex);
                Alert a = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText(ok ? "Examen físico guardado." : "Error al guardar.");
                a.show();
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Error inesperado");
                a.setContentText(ex.getMessage());
                a.show();
            }
        });

        stage.setScene(new Scene(grid, 500, 400));
        stage.show();
    }
}
