package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainGUI extends Application {
    public static void startApp(String[] args) {
        launch(args);
    }
    private BorderPane mainPane;

    @Override
    public void start(Stage primaryStage) {
        mainPane = new BorderPane();
        Scene mainScene = new Scene(mainPane);
        setPane(new MenuView(this));

        primaryStage.setTitle("Higher Impact - prototyp 0.0");
        primaryStage.setScene(mainScene);
//        primaryStage.setResizable(false);
//        primaryStage.setOnCloseRequest(closeEvent -> ApplicationUI.gameBoardUI().quitGame());
        primaryStage.show();
    }

    public void setPane(ApplicationView view) {
        mainPane.setCenter(view.getPane());
    }

}
