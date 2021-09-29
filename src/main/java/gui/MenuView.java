package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
                setOnAction(e -> getGUI().setCenter(new GameView(getGUI(), set)));
                setFont(new Font(30));
            }
        }

        SetButton[] setButtons = {
            new SetButton(ItemSet.PRODUCTS),
            new SetButton(ItemSet.COUNTRIES),
        };

        HBox buttonBox = new HBox(setButtons);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        pane = new VBox(title, buttonBox);
        pane.setSpacing(10);
        pane.setAlignment(Pos.CENTER);
    }

    @Override
    public Pane getPane() {
        return pane;
    }
}
