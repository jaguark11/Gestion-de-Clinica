// Vista/HistoriaClinicaForm.java
package Vista;

import Controladores.ControladorHistoriaClinica;
import Modelo.HistoriaClinica;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.Date;

public class HistoriaClinicaForm {
    private final Connection conexion;

    public HistoriaClinicaForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Historia Clínica");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField folioField    = new TextField();
        DatePicker fechaPicker  = new DatePicker(LocalDate.now());
        TextField pacField      = new TextField();
        TextField cedulaField   = new TextField();

        grid.add(new Label("Folio HC:"),           0, 0);
        grid.add(folioField,  1, 0);
        grid.add(new Label("Fecha:"),              0, 1);
        grid.add(fechaPicker, 1, 1);
        grid.add(new Label("ID Paciente:"),        0, 2);
        grid.add(pacField,    1, 2);
        grid.add(new Label("Cédula Profesional:"), 0, 3);
        grid.add(cedulaField, 1, 3);

        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 4);

        guardarBtn.setOnAction(e -> {
            try {
                int folio = Integer.parseInt(folioField.getText());
                int idPac = Integer.parseInt(pacField.getText());
                int cedula = Integer.parseInt(cedulaField.getText());

                HistoriaClinica h = new HistoriaClinica();
                h.setFolio(folio);
                h.setFecha(Date.valueOf(fechaPicker.getValue()));
                h.setIdPaciente(idPac);
                h.setCedulaProfesional(cedula);

                ControladorHistoriaClinica ctrl = new ControladorHistoriaClinica(conexion);
                boolean ok = ctrl.agregarHistoriaClinica(h);

                Alert alerta = new Alert(ok
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.WARNING);
                alerta.setHeaderText(null);
                alerta.setContentText(ok
                    ? "Historia clínica guardada correctamente."
                    : "Error: verifica que el Paciente exista.");
                alerta.showAndWait();

            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR,
                    "Folio, ID Paciente y Cédula deben ser números.")
                  .showAndWait();
            }
        });

        stage.setScene(new Scene(grid, 440, 300));
        stage.show();
    }
}
