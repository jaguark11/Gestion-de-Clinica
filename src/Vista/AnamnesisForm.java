package Vista;

import Controladores.ControladorAnamnesis;
import Modelo.Anamnesis;
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

public class AnamnesisForm {
    private final Connection conexion;
    public AnamnesisForm(Connection conexion) {
        this.conexion = conexion;
    }
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Anamnesis");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField folioField = new TextField();
        TextField fechaField = new TextField();
        TextField dietaField = new TextField();
        TextField freqField  = new TextField();
        TextField modoField  = new TextField();
        TextField tiempoField= new TextField();
        CheckBox otroChk     = new CheckBox("¿Otro animal?");
        TextField medioField = new TextField();

        grid.add(new Label("Folio HC:"),       0, 0);
        grid.add(folioField,  1, 0);
        grid.add(new Label("Fecha HC (YYYY-MM-DD):"), 0, 1);
        grid.add(fechaField,  1, 1);
        grid.add(new Label("Dieta:"),          0, 2);
        grid.add(dietaField,  1, 2);
        grid.add(new Label("Frecuencia:"),     0, 3);
        grid.add(freqField,   1, 3);
        grid.add(new Label("Modo:"),           0, 4);
        grid.add(modoField,   1, 4);
        grid.add(new Label("Tiempo posesión:"),0, 5);
        grid.add(tiempoField, 1, 5);
        grid.add(otroChk,     1, 6);
        grid.add(new Label("Medio ambiente:"), 0, 7);
        grid.add(medioField,  1, 7);

        Button btn = new Button("Guardar");
        grid.add(btn, 1, 8);

        btn.setOnAction(e -> {
            try {
                ControladorAnamnesis ctrl = new ControladorAnamnesis(conexion);
                Anamnesis a = new Anamnesis();
                a.setFolioHc(Integer.parseInt(folioField.getText()));
                a.setFechaHc(Date.valueOf(fechaField.getText()));
                a.setDieta(dietaField.getText());
                a.setFrecuencia(freqField.getText());
                a.setModo(modoField.getText());
                a.setTiempoPosesionCaballo(Date.valueOf(tiempoField.getText()));
                a.setOtroAnimal(otroChk.isSelected());
                a.setMedioAmbiente(medioField.getText());
                boolean ok = ctrl.agregarAnamnesis(a);
                Alert x = new Alert(ok ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                x.setHeaderText(null);
                x.setContentText(ok ? "Anamnesis registrada." : "Error al guardar.");
                x.show();
            } catch(Exception ex) {
                Alert x = new Alert(Alert.AlertType.ERROR);
                x.setHeaderText(null);
                x.setContentText("Error: " + ex.getMessage());
                x.show();
            }
        });

        stage.setScene(new Scene(grid, 520, 380));
        stage.show();
    }
}
