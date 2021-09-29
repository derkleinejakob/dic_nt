package gui;

import javafx.animation.AnimationTimer;
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
import model.ItemSet;


public class GameView extends ApplicationView {
    private final ItemSet itemSet;
    private GameMode game;
    private BorderPane pane;
    private ItemPane left, right;
    private boolean waiting;

    /**
     *
     * @param gui
     * @param set
     */
    public GameView(MainGUI gui, ItemSet set) {
        super(gui);

        itemSet = set;
        game = new GameMode(itemSet.getDataset());
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
                if (correct) {
                    AnimationTimer textTimer = new AnimationTimer() {
                        private double currentMargin;

                        public void handle(long arg0) {
                            if (this.currentMargin >= left.getWidth()) {
                                GridPane.setMargin(left, null);
                                GridPane.setMargin(right, null);
                                left.switchTo(game.getItem1());
                                right.setItem(game.getItem2());
                                this.stop();
                                waiting = false;
                            } else {
                                this.currentMargin += 20.0D;
                                GridPane.setMargin(left, new Insets(0.0D, this.currentMargin, 0.0D, -this.currentMargin));
                                GridPane.setMargin(right, new Insets(0.0D, this.currentMargin, 0.0D, -this.currentMargin));
                            }

                        }
                    };
                    textTimer.start();
                } else {
                    getGUI().setCenter(new LostView(getGUI(), game.getScore(), itemSet));
                }
            });
            new Thread(sleeper).start();
        }
    }
}
