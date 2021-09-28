package gui;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Item;

public class GameView extends ApplicationView {
//    private Game game;
    private GridPane pane;
    private ItemPane left, right;

    public GameView() {
        pane = new GridPane();
        left = new ItemPane(event -> {
            System.out.println("l");/* game.receiveInput(false); */});
        right = new ItemPane(event -> {
            System.out.println("r");/* game.receiveInput(true); */});

        left.setItem(new Item("Apfel", "Aus dem Kühlhaus", ""));
        right.setItem(new Item("Mango", "Aus Peru eingeflogen", ""));
        pane.add(left, 0, 0);
        pane.add(right, 1, 0);
    }

    @Override
    public Pane getPane() {
        return pane;
    }
}
