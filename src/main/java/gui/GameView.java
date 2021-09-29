package gui;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.GameMode;
import model.ItemSet;


public class GameView extends ApplicationView {
    private final ItemSet itemSet;
    private final GameMode game;
    private final BorderPane pane;
    private final ItemPane left, right;

    //to ensure that the user cannot click on the items again while the animation is playing, the program has to wait
    private boolean waiting;

    /**
     * @param gui a reference to the main gui, so that the game can change to a lost view.
     * @param set the dataset to be used for this game: currently either COUNTRIES or PRODUCTS (more could be added).
     */
    public GameView(MainGUI gui, ItemSet set) {
        super(gui);

        itemSet = set;
        game = new GameMode(itemSet.getDataset());
        left = new ItemPane(event -> reactOnClick(false), game.getItem1(), set);
        right = new ItemPane(event -> reactOnClick(true), game.getItem2(), set);

        //in the center of the game are the two items in a grid
        GridPane centerPane = new GridPane();
        centerPane.add(left, 0, 0);
        centerPane.add(right, 1, 0);

        pane = new BorderPane(centerPane);

        //in the bottom, a label displays the score (number of correct answers in a row)
        Label scoreLabel = new Label();
        scoreLabel.setFont(new Font(22));
        //bind the score to the labels value
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
        if(! waiting) { //ignore the click if the program is currently in animation
            waiting = true;

            //update the game and change to new comparing items. returns whether the user's input was correct.
            boolean correct = game.receiveInput(rightClick);

            //display the real emissions and paint the clicked item in either red or green
            right.showResult();
            (rightClick ? right : left).colorize(correct);

            //wait for some short time, to allow the user to read the correct emissions.
            //wait in a different thread, otherwise, the whole gui would be blocked.
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() {
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException ignored) {
                    }
                    return null;
                }
            };
            //when the waiting is over, show the next item / game over screen
            task.setOnSucceeded(event -> {
                if (correct) {
                    /*
                    slide the next item into view:
                    the left item is slided out of frame. when the animation is over, the item panes are placed in their
                    correct positions and the right item is placed on the left and the new item is placed on the right.
                     */
                    AnimationTimer textTimer = new AnimationTimer() {
                        private double currentMargin;

                        public void handle(long arg0) {
                            if (this.currentMargin >= left.getWidth()) { //the item is fully out of frame, show new item
                                GridPane.setMargin(left, null);
                                GridPane.setMargin(right, null);
                                left.switchTo(game.getItem1());
                                right.switchTo(game.getItem2());
                                right.hideResult();

                                this.stop();
                                waiting = false; //now, the user input shall no longer be ignored
                            } else {
                                // increase the margin gradually, to slide the panes to the left
                                this.currentMargin += 20.0D;
                                GridPane.setMargin(left, new Insets(0.0D, this.currentMargin, 0.0D, -this.currentMargin));
                                GridPane.setMargin(right, new Insets(0.0D, this.currentMargin, 0.0D, -this.currentMargin));
                            }
                        }
                    };
                    textTimer.start();
                } else {
                    //the answer was wrong, switch to a game over screen
                    getGUI().setCenter(new LostView(getGUI(), game.getScore(), itemSet));
                }
            });
            new Thread(task).start();
        }
    }
}
