package Odtwarzacz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception{

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("mainWindowInterface.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Odtwarzacz muzyki");
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });

    }

    public void run(){launch();}
}