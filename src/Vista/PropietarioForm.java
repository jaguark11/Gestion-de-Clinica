// Vista/PropietarioForm.java
package Vista;

import Controladores.ControladorPropietario;
import Modelo.Propietario;
import java.sql.Connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PropietarioForm {
    private final Connection conexion;

    public PropietarioForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Propietario");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        // Ya no pedimos ID: es AUTO_INCREMENT
        TextField nombreField    = new TextField();
        TextField telefonoField  = new TextField();
        TextField domicilioField = new TextField();

        grid.add(new Label("Nombre:"),   0, 0);
        grid.add(nombreField,           1, 0);
        grid.add(new Label("Teléfono:"), 0, 1);
        grid.add(telefonoField,         1, 1);
        grid.add(new Label("Domicilio:"),0, 2);
        grid.add(domicilioField,        1, 2);

        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 3);

        guardarBtn.setOnAction(e -> {
            try {
                // Validación básica
                if (nombreField.getText().isEmpty() ||
                    telefonoField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Nombre y Teléfono son obligatorios.");
                }

                Propietario p = new Propietario();
                p.setNombre(nombreField.getText());
                p.setTelefono(telefonoField.getText());
                p.setDomicilio(domicilioField.getText());

                ControladorPropietario ctrl = new ControladorPropietario(conexion);
                boolean ok = ctrl.agregarPropietario(p);

                Alert alert = new Alert(ok
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ok
                    ? "Propietario guardado correctamente."
                    : "Error al guardar propietario.");
                alert.showAndWait();

                if (ok) stage.close();

            } catch (IllegalArgumentException iae) {
                new Alert(Alert.AlertType.WARNING, iae.getMessage()).showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR,
                    "Error inesperado: " + ex.getMessage()).showAndWait();
            }
        });

        stage.setScene(new Scene(grid, 400, 240));
        stage.show();
    }
}
