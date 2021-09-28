package gui;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Item;
import model.*;

import java.util.Timer;
import java.util.TimerTask;


public class GameView extends ApplicationView {
    private GameMode game;
    private GridPane pane;
    private ItemPane left, right;

    public GameView() {
        game = new GameMode();
        pane = new GridPane();
        left = new ItemPane(event -> reactOnClick(false));
        right = new ItemPane(event -> reactOnClick(true));

        pane.add(left, 0, 0);
        pane.add(right, 1, 0);

        left.setItem(game.getItem1());
        right.setItem(game.getItem2());

    }

    @Override
    public Pane getPane() {
        return pane;
    }

    private void reactOnClick(boolean rightClick) {
        boolean correct = game.receiveInput(rightClick);
        left.showResult();
        right.showResult();
        System.out.println("Changed");
//        Platform.runLater(() -> {
//            System.out.println("Going to sleep now.");
//            try {
////                Thread.sleep(1000);
//                System.out.println("SLEPT");
//                if(correct) {
//                    left.setItem(game.getItem1());
//                    right.setItem(game.getItem2());
//                } else {
//                    System.out.println("FALSCH!!!!");
////            getGUI().setPane(LostPane);
//                }
//            } catch(InterruptedException e) {
//                System.err.println("Interrupted: "+e);
//            }
//        });
    }

    private void showResultsAndWait() {
    }
}
