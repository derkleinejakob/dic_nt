package gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import model.Item;


public class ItemPane extends BorderPane {
    public ItemPane(EventHandler<MouseEvent> onclick) {
        setStyle("-fx-cursor: hand");
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        addEventHandler(MouseEvent.MOUSE_CLICKED, onclick);
    }
    public void setItem(Item item) {
        Label titleLabel = new Label(item.title());
        titleLabel.setFont(new Font(30));
        Label descriptionLabel = new Label(item.description());
        descriptionLabel.setFont(new Font(15));

        VBox centerBox = new VBox(titleLabel, descriptionLabel);
        centerBox.setAlignment(Pos.CENTER);
        setCenter(centerBox);
    }
}
