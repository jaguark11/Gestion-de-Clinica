package Vista;

import BaseDatos.PacienteDAO;
import Modelo.Paciente;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class PacienteForm {

    private final Connection conexion;

    public PacienteForm(Connection conexion) {
        this.conexion = conexion;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario de Paciente");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nombreField   = new TextField();
        TextField especieField  = new TextField();
        TextField razaField     = new TextField();
        TextField sexoField     = new TextField();
        TextField edadField     = new TextField();
        TextField colorField    = new TextField();
        TextField pesoField     = new TextField();
        TextField senasField    = new TextField();

        grid.add(new Label("Nombre:"),                0, 0); grid.add(nombreField, 1, 0);
        grid.add(new Label("Especie:"),               0, 1); grid.add(especieField, 1, 1);
        grid.add(new Label("Raza:"),                  0, 2); grid.add(razaField, 1, 2);
        grid.add(new Label("Sexo:"),                  0, 3); grid.add(sexoField, 1, 3);
        grid.add(new Label("Edad:"),                  0, 4); grid.add(edadField, 1, 4);
        grid.add(new Label("Color:"),                 0, 5); grid.add(colorField, 1, 5);
        grid.add(new Label("Peso:"),                  0, 6); grid.add(pesoField, 1, 6);
        grid.add(new Label("Señas Particulares:"),    0, 7); grid.add(senasField, 1, 7);

        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 8);

        guardarBtn.setOnAction(e -> {
            try {
                Paciente p = new Paciente();
                p.setNombre(nombreField.getText());
                p.setEspecie(especieField.getText());
                p.setRaza(razaField.getText());
                p.setSexo(sexoField.getText());
                p.setEdad(Integer.parseInt(edadField.getText()));
                p.setColor(colorField.getText());
                p.setPeso(Double.parseDouble(pesoField.getText()));
                p.setSena_particular(senasField.getText());
                p.setId_propietario(1); // o el que toque

                PacienteDAO dao = new PacienteDAO(conexion);
                int idGenerado = dao.agregarPaciente(p);

                Alert alert = new Alert(idGenerado != -1
                        ? Alert.AlertType.INFORMATION
                        : Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(idGenerado != -1
                        ? "Paciente guardado con ID: " + idGenerado
                        : "Error al guardar.");
                alert.show();

                if (idGenerado != -1) {
                    stage.close();
                }

            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR, "Edad y peso deben ser números.").show();
            } catch (Exception ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error inesperado: " + ex.getMessage()).show();
            }
        });

        stage.setScene(new Scene(grid, 450, 500));
        stage.show();
    }
}
