package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main window of the application.
 */
public class MainGUI extends Application {
    public static void startApp(String[] args) {
        launch(args);
    }

    //the scene contains only this one pane, whose center can be changed
    private BorderPane mainPane;

    @Override
    public void start(Stage primaryStage) {
        mainPane = new BorderPane();
        Scene mainScene = new Scene(mainPane);
        setCenter(new MenuView(this));

        primaryStage.setTitle("Higher Impact!");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Changes the view displayed in the center of the window (e.g. gameplay / menu)
     */
    public void setCenter(ApplicationView view) {
        mainPane.setCenter(view.getPane());
    }
}
