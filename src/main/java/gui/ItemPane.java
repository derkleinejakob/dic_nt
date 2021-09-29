package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.Item;

import java.awt.*;


public class ItemPane extends BorderPane {
    private Label titleLabel, emissionLabel, descriptionLabel;

    public ItemPane(EventHandler<MouseEvent> onclick) {
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        addEventHandler(MouseEvent.MOUSE_CLICKED, onclick);
        setStyle("-fx-cursor: hand; ");

        titleLabel = new Label();
        titleLabel.setFont(new Font(30));
        emissionLabel = new Label();
        emissionLabel.setFont(new Font(50));
        descriptionLabel = new Label();
        descriptionLabel.setFont(new Font(15));
        VBox centerBox = new VBox(titleLabel, descriptionLabel, emissionLabel);
        centerBox.setAlignment(Pos.CENTER);
        setCenter(centerBox);

    }

    public void setItem(Item item) {
        switchTo(item);
        emissionLabel.setOpacity(0);
    }

    public void switchTo(Item item) {
//        setBackground(null);
        setStyle("-fx-background-image: url(file:src/main/resources/" + item.image() + ");");
        titleLabel.setText(item.title());
        descriptionLabel.setText(item.description());
        emissionLabel.setText(item.emissions() + "g CO₂");
    }

    public void showResult() {
        emissionLabel.setOpacity(1);
    }

    public void colorize(boolean isCorrect) {
        //setBackground(new Background(new BackgroundFill(Paint.valueOf(isCorrect ? "GREEN" : "RED"), null, null)));
        setStyle("-fx-background-color: " + (isCorrect ? "GREEN" : "RED") );
    }
}
