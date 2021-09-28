package gui;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.GameMode;


public class GameView extends ApplicationView {
    private GameMode game;
    private BorderPane pane;
    private ItemPane left, right;
    private boolean waiting;

    public GameView(MainGUI gui) {
        super(gui);

        game = new GameMode();
        GridPane centerPane = new GridPane();
        left = new ItemPane(event -> reactOnClick(false));
        right = new ItemPane(event -> reactOnClick(true));

        centerPane.setPadding(new Insets(0));
        centerPane.add(left, 0, 0);
        centerPane.add(new Separator(Orientation.VERTICAL), 1, 0);
        centerPane.add(right, 2, 0);

        left.setItem(game.getItem1());
        right.setItem(game.getItem2());
        pane = new BorderPane(centerPane);
        Label scoreLabel = new Label();
        scoreLabel.setFont(new Font(22));
        scoreLabel.textProperty().bind(game.scorePropertyProperty().asString("Score: %d"));
        HBox bottomPane = new HBox(scoreLabel);
        bottomPane.setAlignment(Pos.CENTER_RIGHT);
        pane.setBottom(bottomPane);
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    private void reactOnClick(boolean rightClick) {
        if(! waiting) {
            waiting = true;

            boolean correct = game.receiveInput(rightClick);

            left.showResult();
            right.showResult();
            (rightClick ? right : left).colorize(correct);

            Task<Void> sleeper = new Task<>() {
                @Override
                protected Void call() {
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ignored) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> {
                waiting = false;
                if (correct) {
                    left.switchTo(game.getItem1());
                    right.setItem(game.getItem2());
                } else {
                    getGUI().setPane(new LostView(getGUI(), game.getScore()));
                }
            });
            new Thread(sleeper).start();
        }
    }
}
