package gui;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Item;


public class ItemPane extends BorderPane {
    private Label titleLabel, emissionLabel, descriptionLabel;
    private double emissionsValue;

    public ItemPane(EventHandler<MouseEvent> onclick) {
        setStyle("-fx-cursor: hand");
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        addEventHandler(MouseEvent.MOUSE_CLICKED, onclick);

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
        titleLabel.setText(item.title());
        descriptionLabel.setText(item.description());
        emissionLabel.setText(String.valueOf(item.emissions()));
        emissionLabel.setOpacity(0);

        emissionsValue = item.emissions();
    }

    public void showResult() {
        emissionLabel.setOpacity(1);
    }
}
