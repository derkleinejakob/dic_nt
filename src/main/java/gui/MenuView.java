package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.ItemSet;

public class MenuView extends ApplicationView {
    private VBox pane;
    public MenuView(MainGUI gui) {
        super(gui);

        Label title = new Label("Higher Impact!");
        title.setFont(new Font(50));

        class SetButton extends Button {
            public SetButton(ItemSet set) {
                setText(set.toString());
                setOnAction(e -> getGUI().setPane(new GameView(getGUI(), set)));
            }
        }

        SetButton[] setButtons = {
            new SetButton(ItemSet.PRODUCTS),
            new SetButton(ItemSet.COUNTRIES),
        };

        pane = new VBox(title);
        pane.setSpacing(10);
        pane.getChildren().addAll(setButtons);
        pane.setAlignment(Pos.CENTER);
    }

    @Override
    public Pane getPane() {
        return pane;
    }
}
