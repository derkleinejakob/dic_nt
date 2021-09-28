package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MenuView extends ApplicationView {
    private VBox pane;
    public MenuView(MainGUI gui) {
        super(gui);

        Label title = new Label("Higher Impact!");
        title.setFont(new Font(50));
        Button startButton = new Button("Los");
        startButton.setOnAction(a -> getGUI().setPane(new GameView(getGUI())));

        pane = new VBox(title, startButton);
        pane.setAlignment(Pos.CENTER);
    }

    @Override
    public Pane getPane() {
        return pane;
    }
}
