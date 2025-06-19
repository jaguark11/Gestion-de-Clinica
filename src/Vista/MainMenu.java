package Vista;

import Modelo.ConexionBD;
import Controladores.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.sql.Connection;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private Connection conexion;

    @Override
    public void start(Stage primaryStage) {
        // 1) Conectar a la BBDD
        conexion = ConexionBD.conectar();
        if (conexion == null) {
            Alert err = new Alert(Alert.AlertType.ERROR);
            err.setHeaderText(null);
            err.setContentText("No se pudo conectar a la base de datos.");
            err.showAndWait();
            return;
        }

        primaryStage.setTitle("📋 Menú Principal - Clínica Veterinaria");

        // 2) Top: logo + título
        HBox topBar = new HBox(12);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));
        ImageView logo = new ImageView(new Image(
            "https://cdn-icons-png.flaticon.com/512/616/616408.png",
            60, 0, true, true
        ));
        Label title = new Label("Clínica Veterinaria");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, thirtySix()));
        title.setTextFill(Color.web("#01579b"));
        title.setEffect(new DropShadow(4, Color.gray(0.4)));
        topBar.getChildren().addAll(logo, title);

        // 3) Center: botones
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        String[] texts = {
            "🧑 Paciente", "👨‍🌾 Propietario",
            "📋 Historia Clínica", "💊 Tratamiento",
            "💉 Vacunación", "🔍 Observación",
            "📝 Examen Complementario", "⚕️ Examen Físico",
            "🔖 Diagnóstico Integral", "📖 Anamnesis",
            "🛠 Procedimiento", "⚠️ Signo Anormal",
            "👥 Gestionar Pacientes", "❌ Salir"
        };

        Button[] btns = new Button[texts.length];
        for (int i = 0; i < texts.length; i++) {
            btns[i] = createButton(texts[i]);
            btns[i].setMaxWidth(Double.MAX_VALUE);
            GridPane.setHgrow(btns[i], Priority.ALWAYS);
            grid.add(btns[i], i % 2, i / 2);
        }

        // 4) Asignar acciones
        btns[0].setOnAction(e -> new PacienteForm(conexion).show());
        btns[1].setOnAction(e -> new PropietarioForm(conexion).show());
        btns[2].setOnAction(e -> new HistoriaClinicaForm(conexion).show());
        btns[3].setOnAction(e -> new TratamientoForm(conexion).show());
        btns[4].setOnAction(e -> new VacunacionForm(conexion).show());
        btns[5].setOnAction(e -> new ObservacionForm(conexion).show());
        btns[6].setOnAction(e -> new ExamenComplementarioForm(conexion).show());
        btns[7].setOnAction(e -> new ExamenFisicoForm(conexion).show());
        btns[8].setOnAction(e -> new DiagnosticoIntegralForm(conexion).show());
        btns[9].setOnAction(e -> new AnamnesisForm(conexion).show());

        // **Aquí ya no pedimos ID**, simplemente abrimos el form:
        btns[10].setOnAction(e -> new ProcedimientoForm(conexion).show());

        btns[11].setOnAction(e -> new SignoFisicoAnormalForm(conexion).show());
        btns[12].setOnAction(e -> new PacienteManagerForm(conexion).show());
        btns[13].setOnAction(e -> primaryStage.close());

        // 5) Footer
        Label footer = new Label("© 2025 Clínica Veterinaria - Todos los derechos reservados");
        footer.setFont(Font.font("Segoe UI", FontPosture.ITALIC, 11));
        footer.setTextFill(Color.web("#546e7a"));
        footer.setAlignment(Pos.CENTER);

        // 6) Construir layout
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(grid);
        root.setBottom(footer);
        BorderPane.setAlignment(footer, Pos.CENTER);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e1f5fe, #b3e5fc);");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /** Crea un botón con estilo hover */
    private Button createButton(String text) {
        Button b = new Button(text);
        b.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 14));
        b.setStyle(
            "-fx-background-color: #0288d1; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 8; " +
            "-fx-padding: 8 16;"
        );
        b.setOnMouseEntered(e ->
            b.setStyle("-fx-background-color: #26c6da; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 8 16;")
        );
        b.setOnMouseExited(e ->
            b.setStyle("-fx-background-color: #0288d1; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 8 16;")
        );
        return b;
    }

    /** Atajo para tamaño de fuente 36 */
    private double thirtySix() {
        return 36;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
