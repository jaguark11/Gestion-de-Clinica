package Vista;

import Controladores.ControladorVacunacion;
import Modelo.Vacunacion;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VacunacionForm {
    private final Connection conexion;

    public VacunacionForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Vacunación");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField        = new TextField();
        TextField folioField     = new TextField();
        DatePicker fechaHcPicker = new DatePicker(LocalDate.now());
        TextField otraVacField   = new TextField();
        DatePicker fechaAppPicker= new DatePicker(LocalDate.now());

        grid.add(new Label("ID Vacuna:"),           0, 0);
        grid.add(idField,                            1, 0);
        grid.add(new Label("Folio HC:"),            0, 1);
        grid.add(folioField,                         1, 1);
        grid.add(new Label("Fecha HC:"),            0, 2);
        grid.add(fechaHcPicker,                      1, 2);
        grid.add(new Label("Otra Vacuna:"),         0, 3);
        grid.add(otraVacField,                       1, 3);
        grid.add(new Label("Fecha Aplicación:"),    0, 4);
        grid.add(fechaAppPicker,                     1, 4);

        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 5);

        guardarBtn.setOnAction(e -> {
            try {
                if (idField.getText().isEmpty() ||
                    folioField.getText().isEmpty() ||
                    otraVacField.getText().isEmpty()) {
                    throw new IllegalArgumentException("ID, Folio y Vacuna son obligatorios.");
                }
                int idVac = Integer.parseInt(idField.getText().trim());
                int folio = Integer.parseInt(folioField.getText().trim());
                LocalDate fhc = fechaHcPicker.getValue();
                LocalDate fapp = fechaAppPicker.getValue();

                Vacunacion v = new Vacunacion();
                v.setIdVacuna(idVac);
                v.setFolioHc(folio);
                v.setFechaHc(Date.valueOf(fhc));
                v.setOtraVacuna(otraVacField.getText().trim());
                v.setFechaAplicacion(Date.valueOf(fapp));

                ControladorVacunacion ctrl = new ControladorVacunacion(conexion);
                boolean ok = ctrl.agregarVacunacion(v);

                Alert alert = new Alert(ok
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ok
                    ? "Vacunación guardada correctamente."
                    : "Error al guardar vacunación.");
                alert.showAndWait();

                if (ok) stage.close();
            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR,
                    "ID y Folio deben ser números enteros.").showAndWait();
            } catch (IllegalArgumentException iae) {
                new Alert(Alert.AlertType.WARNING,
                    iae.getMessage()).showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR,
                    "Error inesperado: " + ex.getMessage()).showAndWait();
            }
        });

        stage.setScene(new Scene(grid, 520, 380));
        stage.show();
    }
}
