package Vista;

import Controladores.ControladorObservacion;
import Modelo.Observacion;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ObservacionForm {
    private final Connection conexion;

    public ObservacionForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Observación");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idField      = new TextField();
        TextField folioField   = new TextField();
        DatePicker fechaPicker = new DatePicker(LocalDate.now());
        TextArea contenidoArea = new TextArea();
        contenidoArea.setPrefRowCount(4);

        grid.add(new Label("ID Observación:"),    0, 0);
        grid.add(idField,                         1, 0);
        grid.add(new Label("Folio HC (número):"),0, 1);
        grid.add(folioField,                      1, 1);
        grid.add(new Label("Fecha (YYYY-MM-DD):"),0, 2);
        grid.add(fechaPicker,                     1, 2);
        grid.add(new Label("Contenido:"),         0, 3);
        grid.add(contenidoArea,                   1, 3);

        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 4);

        guardarBtn.setOnAction(e -> {
            try {
                // Validaciones
                if (idField.getText().isEmpty() ||
                    folioField.getText().isEmpty() ||
                    contenidoArea.getText().isEmpty()) {
                    throw new IllegalArgumentException("Todos los campos son obligatorios.");
                }

                int idObs = Integer.parseInt(idField.getText().trim());
                int folio = Integer.parseInt(folioField.getText().trim());
                LocalDate ld = fechaPicker.getValue();

                Observacion o = new Observacion();
                o.setIdObservacion(idObs);
                o.setFolioHc(folio);
                o.setFechaHc(Date.valueOf(ld));
                o.setContenido(contenidoArea.getText().trim());

                ControladorObservacion ctrl = new ControladorObservacion(conexion);
                boolean ok = ctrl.agregarObservacion(o);

                Alert alert = new Alert(ok
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ok
                    ? "Observación guardada correctamente."
                    : "Error al guardar observación.");
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

        stage.setScene(new Scene(grid, 480, 360));
        stage.show();
    }
}
