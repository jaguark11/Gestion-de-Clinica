package Vista;

import BaseDatos.PacienteDAO;
import Modelo.Paciente;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PacienteManagerForm {

    private final PacienteDAO dao;
    private TableView<Paciente> table;

    public PacienteManagerForm(Connection conexion) {
        this.dao = new PacienteDAO(conexion);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("📋 Gestión de Pacientes");

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(450);

        TableColumn<Paciente, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getId_paciente()));
        colId.setPrefWidth(60);

        TableColumn<Paciente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getNombre()));

        TableColumn<Paciente, String> colRaza = new TableColumn<>("Raza");
        colRaza.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getRaza()));

        TableColumn<Paciente, String> colEspecie = new TableColumn<>("Especie");
        colEspecie.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getEspecie()));

        TableColumn<Paciente, String> colSexo = new TableColumn<>("Sexo");
        colSexo.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSexo()));

        TableColumn<Paciente, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getEdad()));

        TableColumn<Paciente, String> colColor = new TableColumn<>("Color");
        colColor.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getColor()));

        TableColumn<Paciente, Double> colPeso = new TableColumn<>("Peso");
        colPeso.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getPeso()));

        TableColumn<Paciente, String> colSena = new TableColumn<>("Signos");
        colSena.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSena_particular()));
        colSena.setCellFactory(tc -> new TableCell<>() {
            private final Tooltip tooltip = new Tooltip();
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item.length() > 30 ? item.substring(0, 30) + "..." : item);
                    tooltip.setText(item);
                    setTooltip(tooltip);
                }
            }
        });
        colSena.setOnEditStart(e -> mostrarDetalleSignos());

        colSena.setPrefWidth(300);

        TableColumn<Paciente, String> colFecha = new TableColumn<>("Creado");
        colFecha.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCreated_at()));

        table.getColumns().addAll(
            colId, colNombre, colRaza, colEspecie, colSexo,
            colEdad, colColor, colPeso, colSena, colFecha
        );

        table.setRowFactory(tv -> {
            TableRow<Paciente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    mostrarDetalleSignos(row.getItem().getSena_particular());
                }
            });
            return row;
        });

        Button btnAdd = new Button("➕ Agregar");
        Button btnDelete = new Button("🗑 Eliminar");
        Button btnResetID = new Button("🔁 Reiniciar IDs");

        for (Button b : new Button[]{btnAdd, btnDelete, btnResetID}) {
            b.setStyle("-fx-font-size: 14px; -fx-padding: 8 16 8 16; -fx-background-color: #4285f4; -fx-text-fill: white; -fx-background-radius: 8;");
        }

        HBox botones = new HBox(12, btnAdd, btnDelete, btnResetID);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(15));
        botones.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-width: 1;");

        VBox root = new VBox(15, table, botones);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #ffffff;");

        btnAdd.setOnAction(e -> mostrarFormularioPaciente());
        btnDelete.setOnAction(e -> {
            Paciente sel = table.getSelectionModel().getSelectedItem();
            if (sel != null) {
                dao.eliminarFisico(sel.getId_paciente());
                cargarDatos();
            }
        });
        btnResetID.setOnAction(e -> reiniciarIds());

        cargarDatos();

        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarDetalleSignos(String textoCompleto) {
        Stage ventana = new Stage();
        ventana.setTitle("Detalle de Signos del Paciente");

        TextArea area = new TextArea(textoCompleto);
        area.setWrapText(true);
        area.setEditable(false);

        VBox root = new VBox(area);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 600, 300);
        ventana.setScene(scene);
        ventana.show();
    }

    private void reiniciarIds() {
        try {
            Statement stmt = dao.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM paciente");
            if (rs.next() && rs.getInt(1) > 0) {
                new Alert(Alert.AlertType.WARNING, "❗ Primero debes eliminar todos los pacientes físicamente para reiniciar los IDs.").showAndWait();
                return;
            }
            stmt.execute("ALTER TABLE paciente AUTO_INCREMENT = 1");
            new Alert(Alert.AlertType.INFORMATION, "✅ ID reiniciado correctamente a 1.").showAndWait();
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "❌ Error al reiniciar el ID.").showAndWait();
        }
    }

    private void cargarDatos() {
        table.getItems().clear();
        table.getItems().addAll(dao.obtenerTodos(false));
    }

    private void mostrarFormularioPaciente() {
        Stage stage = new Stage();
        stage.setTitle("Formulario de Paciente");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(12);
        grid.setVgap(12);

        TextField txtNombre = new TextField();
        TextField txtRaza = new TextField();
        TextField txtEspecie = new TextField();
        TextField txtSexo = new TextField();
        TextField txtEdad = new TextField();
        TextField txtColor = new TextField();
        TextField txtPeso = new TextField();
        TextArea txtSena = new TextArea();
        txtSena.setPrefRowCount(4);
        txtSena.setWrapText(true);

        grid.addRow(0, new Label("Nombre:"), txtNombre);
        grid.addRow(1, new Label("Raza:"), txtRaza);
        grid.addRow(2, new Label("Especie:"), txtEspecie);
        grid.addRow(3, new Label("Sexo:"), txtSexo);
        grid.addRow(4, new Label("Edad:"), txtEdad);
        grid.addRow(5, new Label("Color:"), txtColor);
        grid.addRow(6, new Label("Peso:"), txtPeso);
        grid.addRow(7, new Label("Seña:"), txtSena);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setStyle("-fx-background-color: #34a853; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8 20 8 20; -fx-background-radius: 6;");
        grid.add(btnGuardar, 1, 8);

        btnGuardar.setOnAction(e -> {
            try {
                Paciente nuevoPaciente = new Paciente();
                nuevoPaciente.setNombre(txtNombre.getText());
                nuevoPaciente.setRaza(txtRaza.getText());
                nuevoPaciente.setEspecie(txtEspecie.getText());
                nuevoPaciente.setSexo(txtSexo.getText());
                nuevoPaciente.setEdad(Integer.parseInt(txtEdad.getText()));
                nuevoPaciente.setColor(txtColor.getText());
                nuevoPaciente.setPeso(Double.parseDouble(txtPeso.getText()));
                nuevoPaciente.setSena_particular(txtSena.getText());
                nuevoPaciente.setId_propietario(1);

                int idGenerado = dao.agregarPaciente(nuevoPaciente);

                Alert alert = new Alert(idGenerado != -1 ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(idGenerado != -1 ? "Paciente guardado con ID: " + idGenerado : "Error al guardar el paciente.");
                alert.show();

                if (idGenerado != -1) {
                    cargarDatos();
                    stage.close();
                }
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Error: " + ex.getMessage()).show();
            }
        });

        Scene scene = new Scene(grid, 420, 500);
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarDetalleSignos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}