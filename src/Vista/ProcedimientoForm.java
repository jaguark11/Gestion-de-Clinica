package Vista;

import Controladores.ControladorProcedimiento;
import Controladores.ControladorTratamiento;
import Modelo.Procedimiento;
import Modelo.Tratamiento;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Connection;

public class ProcedimientoForm {
    private final Connection conexion;
    private final ControladorTratamiento ctrlTrat;
    private final ControladorProcedimiento ctrlProc;

    public ProcedimientoForm(Connection conexion) {
        this.conexion    = conexion;
        this.ctrlTrat    = new ControladorTratamiento(conexion);
        this.ctrlProc    = new ControladorProcedimiento(conexion);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Formulario Procedimiento");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        // 1) Campo ID del procedimiento
        TextField idField = new TextField();
        grid.add(new Label("ID Procedimiento:"), 0, 0);
        grid.add(idField, 1, 0);

        // 2) ComboBox con todos los tratamientos
        ComboBox<Tratamiento> cbTrat = new ComboBox<>();
        cbTrat.getItems().addAll(ctrlTrat.obtenerTodos());
        cbTrat.setConverter(new StringConverter<Tratamiento>() {
            @Override
            public String toString(Tratamiento t) {
                return t == null ? "" : t.getIdTratamiento() + " – " + t.getNombre();
            }
            @Override
            public Tratamiento fromString(String string) {
                return null;
            }
        });
        grid.add(new Label("Tratamiento:"), 0, 1);
        grid.add(cbTrat, 1, 1);

        // 3) Nombre del procedimiento
        TextField nombreField = new TextField();
        grid.add(new Label("Nombre Procedimiento:"), 0, 2);
        grid.add(nombreField, 1, 2);

        // 4) Botón Guardar
        Button guardarBtn = new Button("Guardar");
        grid.add(guardarBtn, 1, 3);

        guardarBtn.setOnAction(e -> {
            Tratamiento sel = cbTrat.getValue();
            if (sel == null) {
                new Alert(Alert.AlertType.WARNING,
                    "Debe seleccionar un Tratamiento primero."
                ).showAndWait();
                return;
            }

            try {
                Procedimiento p = new Procedimiento();
                p.setIdProcedimiento(Integer.parseInt(idField.getText()));
                p.setIdTratamiento(sel.getIdTratamiento());
                p.setNombre(nombreField.getText());
                p.setNombreTratamiento(sel.getNombre());

                boolean ok = ctrlProc.agregarProcedimiento(p);
                Alert a = new Alert(ok
                    ? Alert.AlertType.INFORMATION
                    : Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText(ok
                    ? "Procedimiento guardado."
                    : "Error al guardar procedimiento.");
                a.showAndWait();

            } catch (NumberFormatException nfe) {
                new Alert(Alert.AlertType.ERROR,
                    "ID de Procedimiento debe ser un número."
                ).showAndWait();
            }
        });

        stage.setScene(new Scene(grid, 450, 300));
        stage.show();
    }
}
