package Vista;

import Controladores.ControladorAntecedente;
import Modelo.Antecedente;
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

public class AntecedenteForm {
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Antecedente");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField idAnt    = new TextField();
        TextArea contenido = new TextArea();
        contenido.setPrefRowCount(3);
        TextField dieta    = new TextField();
        TextField frecuencia = new TextField();
        TextField modo     = new TextField();
        DatePicker tiempoPose = new DatePicker();
        CheckBox otroAnimal   = new CheckBox("¿Otro animal?");
        TextField medioAmbiente = new TextField();

        grid.add(new Label("ID Antecedente:"),     0, 0);
        grid.add(idAnt,                             1, 0);
        grid.add(new Label("Contenido:"),           0, 1);
        grid.add(contenido,                         1, 1);
        grid.add(new Label("Dieta:"),               0, 2);
        grid.add(dieta,                             1, 2);
        grid.add(new Label("Frecuencia:"),          0, 3);
        grid.add(frecuencia,                        1, 3);
        grid.add(new Label("Modo:"),                0, 4);
        grid.add(modo,                              1, 4);
        grid.add(new Label("Tiempo posesión caballo:"), 0, 5);
        grid.add(tiempoPose,                        1, 5);
        grid.add(otroAnimal,                        1, 6);
        grid.add(new Label("Medio ambiente:"),      0, 7);
        grid.add(medioAmbiente,                     1, 7);

        Button guardar = new Button("Guardar");
        grid.add(guardar, 1, 8);

        guardar.setOnAction(e -> {
            try (Connection conn = ConexionBD.conectar()) {
                ControladorAntecedente ctrl = new ControladorAntecedente(conn);
                Antecedente a = new Antecedente();

                if (idAnt.getText().isEmpty() || contenido.getText().isEmpty()) {
                    throw new IllegalArgumentException("ID y contenido obligatorios.");
                }

                a.setIdAntecedente(Integer.parseInt(idAnt.getText()));
                a.setContenido(contenido.getText());
                a.setDietaAnamnesis(dieta.getText());
                a.setFrecuenciaAnamnesis(frecuencia.getText());
                a.setModoAnamnesis(modo.getText());
                a.setTiempoPosesionCaballoAnamnesis(
                    Date.from(tiempoPose.getValue()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant())
                );
                a.setOtroAnimalAnamnesis(otroAnimal.isSelected());
                a.setMedioAmbienteAnamnesis(medioAmbiente.getText());

                boolean exito = ctrl.agregarAntecedente(a);
                Alert alert = new Alert(exito
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.ERROR);
                alert.setContentText(exito
                    ? "Antecedente guardado."
                    : "Error al guardar antecedente.");
                alert.show();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error: "+ex.getMessage()).show();
            }
        });

        stage.setScene(new Scene(grid, 500, 450));
        stage.show();
    }
}
