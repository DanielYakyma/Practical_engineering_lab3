package algebraforge.pracitalengineering3;

import algebraforge.pracitalengineering3.main.MainController;
import devtoolsfx.gui.GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApplication extends Application {
    public static MainController main;

    @Override
    public void start(Stage stage) {
        main = new MainController();

        Scene scene = new Scene(main.getView(), 320, 240);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("application.css")).toExternalForm());

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        scene.getAccelerators().putAll(main.getAccelerators());

        GUI.openToolStage(stage, getHostServices());
    }

    public static void main(String[] args) {
        launch();
    }
}