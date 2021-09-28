package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LostView extends ApplicationView {
    private VBox pane;

    public LostView(MainGUI gui, int score) {
        super(gui);

        Label titleLabel = new Label("Oops, das war falsch!");
        titleLabel.setFont(new Font(15));
        Label scoreLabel = new Label("Dein Score war "+score+".");
        scoreLabel.setFont(new Font(30));
        Button retry = new Button("Nochmal!");
        retry.setOnAction(e -> getGUI().setPane(new GameView(getGUI())));

        pane = new VBox(titleLabel, scoreLabel, retry);
        pane.setAlignment(Pos.CENTER);
    }

    @Override
    public Pane getPane() {
        return pane;
    }
}
