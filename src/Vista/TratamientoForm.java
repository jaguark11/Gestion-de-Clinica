package Vista;

import Controladores.ControladorTratamiento;
import Modelo.Tratamiento;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TratamientoForm {
    private final Connection conexion;

    public TratamientoForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Tratamiento");

        // 1) Construir el GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        // 2) Campos
        TextField idField      = new TextField();
        TextField nombreField  = new TextField();
        TextField folioField   = new TextField();
        DatePicker fechaPicker = new DatePicker(LocalDate.now());

        grid.add(new Label("ID Tratamiento:"),   0, 0);
        grid.add(idField,                         1, 0);
        grid.add(new Label("Nombre:"),           0, 1);
        grid.add(nombreField,                     1, 1);
        grid.add(new Label("Folio HC:"),          0, 2);
        grid.add(folioField,                      1, 2);
        grid.add(new Label("Fecha (YYYY-MM-DD):"),0, 3);
        grid.add(fechaPicker,                     1, 3);

        // 3) Botón Guardar
        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 4);

        // 4) Acción al hacer clic
        guardarBtn.setOnAction(e -> {
            try {
                int idFolio = Integer.parseInt(folioField.getText().trim());
                int idTrat  = Integer.parseInt(idField.getText().trim());
                LocalDate ld = fechaPicker.getValue();

                // Crear modelo y controlador
                Tratamiento t = new Tratamiento();
                t.setIdTratamiento(idTrat);
                t.setNombre(nombreField.getText().trim());
                t.setFolioHc(idFolio);
                t.setFechaHc(Date.valueOf(ld));

                ControladorTratamiento ctrl = new ControladorTratamiento(conexion);
                boolean ok = ctrl.agregarTratamiento(t);

                Alert alert = new Alert(ok
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ok
                    ? "Tratamiento guardado correctamente."
                    : "Error al guardar tratamiento.");
                alert.showAndWait();

                if (ok) {
                    stage.close();
                }

            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR,
                    "ID Tratamiento y Folio HC deben ser números enteros.").showAndWait();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR,
                    "Error: " + ex.getMessage()).showAndWait();
            }
        });

        // 5) Mostrar ventana
        Scene scene = new Scene(grid, 450, 300);
        stage.setScene(scene);
        stage.show();
    }
}
