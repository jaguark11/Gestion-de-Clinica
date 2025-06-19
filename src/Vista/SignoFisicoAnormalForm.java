package Vista;

import Controladores.ControladorSignoFisicoAnormal;
import Modelo.SignoFisicoAnormal;
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

public class SignoFisicoAnormalForm {
    private final Connection conexion;

    public SignoFisicoAnormalForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Signo Físico Anormal");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField     = new TextField();
        TextField nombreField = new TextField();
        TextField folioField  = new TextField();
        TextField fechaField  = new TextField();

        grid.add(new Label("ID Signo:"),               0, 0);
        grid.add(idField,                                1, 0);
        grid.add(new Label("Nombre:"),                 0, 1);
        grid.add(nombreField,                            1, 1);
        grid.add(new Label("Folio HC:"),               0, 2);
        grid.add(folioField,                             1, 2);
        grid.add(new Label("Fecha HC (YYYY-MM-DD):"),  0, 3);
        grid.add(fechaField,                             1, 3);

        Button btn = new Button("Guardar");
        grid.add(btn, 1, 4);

        btn.setOnAction(e -> {
            try {
                ControladorSignoFisicoAnormal ctrl = new ControladorSignoFisicoAnormal(conexion);
                SignoFisicoAnormal s = new SignoFisicoAnormal();
                s.setIdSignoFisicoAnormal(Integer.parseInt(idField.getText()));
                s.setNombre(nombreField.getText());
                s.setFolioHc(Integer.parseInt(folioField.getText()));
                s.setFechaHc(Date.valueOf(fechaField.getText()));

                boolean ok = ctrl.agregarSignoFisicoAnormal(s);
                Alert a = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText(ok ? "Signo físico anormal guardado." : "Error al guardar.");
                a.show();
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Error inesperado");
                a.setContentText(ex.getMessage());
                a.show();
            }
        });

        stage.setScene(new Scene(grid, 500, 340));
        stage.show();
    }
}
