package Vista;

import Controladores.ControladorEnfermedad;
import Modelo.Enfermedad;
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

public class EnfermedadForm {
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Enfermedad");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idEnfer = new TextField();
        TextField folioHc = new TextField();
        DatePicker fechaHc = new DatePicker();
        TextField nombre = new TextField();
        TextField padecido = new TextField();
        TextField infecciosa = new TextField();
        TextField tipo = new TextField();
        TextField anio = new TextField();

        grid.add(new Label("ID Enfermedad:"), 0, 0);
        grid.add(idEnfer, 1, 0);
        grid.add(new Label("Folio HC:"), 0, 1);
        grid.add(folioHc, 1, 1);
        grid.add(new Label("Fecha HC:"), 0, 2);
        grid.add(fechaHc, 1, 2);
        grid.add(new Label("Nombre:"), 0, 3);
        grid.add(nombre, 1, 3);
        grid.add(new Label("Padecido:"), 0, 4);
        grid.add(padecido, 1, 4);
        grid.add(new Label("Infecciosa:"), 0, 5);
        grid.add(infecciosa, 1, 5);
        grid.add(new Label("Tipo:"), 0, 6);
        grid.add(tipo, 1, 6);
        grid.add(new Label("Año:"), 0, 7);
        grid.add(anio, 1, 7);

        Button guardar = new Button("Guardar");
        grid.add(guardar, 1, 8);

        guardar.setOnAction(e -> {
            try (Connection conn = ConexionBD.conectar()) {
                ControladorEnfermedad ctrl = new ControladorEnfermedad(conn);
                Enfermedad en = new Enfermedad();

                if (idEnfer.getText().isEmpty() || folioHc.getText().isEmpty() || fechaHc.getValue() == null) {
                    throw new IllegalArgumentException("ID, Folio HC y Fecha HC son obligatorios.");
                }

                en.setIdEnfermedad(Integer.parseInt(idEnfer.getText()));
                en.setFolioHc(Integer.parseInt(folioHc.getText()));
                en.setFechaHc((Date) Date.from(fechaHc.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                en.setNombre(nombre.getText());
                en.setPadecido(padecido.getText());
                en.setInfecciosa(infecciosa.getText());
                en.setTipo(tipo.getText());
                en.setAño(Integer.parseInt(anio.getText()));

                boolean exito = ctrl.agregarEnfermedad(en);
                Alert alert = new Alert(exito ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                alert.setContentText(exito ? "Enfermedad guardada correctamente." : "Error al guardar enfermedad.");
                alert.show();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error: " + ex.getMessage());
                alert.show();
            }
        });

        stage.setScene(new Scene(grid, 500, 450));
        stage.show();
    }
}
