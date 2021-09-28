package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import model.Item;


public class ItemPane extends BorderPane {
    public ItemPane(EventHandler<MouseEvent> onclick) {
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        addEventHandler(MouseEvent.MOUSE_CLICKED, onclick);
    }
    public void setItem(Item item) {
        Label titleLabel = new Label(item.title());
        titleLabel.setFont(new Font(30));

        setCenter(titleLabel);

        Label descriptionLabel = new Label(item.description());
        descriptionLabel.setFont(new Font(15));
        setBottom(descriptionLabel);
    }
}
