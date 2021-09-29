package gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Item;

public class ItemPane extends BorderPane {
    private Image backgroundImage;
    private Label titleLabel, emissionLabel, descriptionLabel;
    private Item item;

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
        this.item = item;
        backgroundImage = new Image("file:src/main/resources/" + item.image());
        setBackground(new Background(new BackgroundImage(backgroundImage, null, null, null, null)));
        titleLabel.setText(item.title());
        descriptionLabel.setText(item.description());
        emissionLabel.setText(item.emissions() + "g CO₂");
    }

    public void showResult() {
        emissionLabel.setOpacity(1);
    }

    public void colorize(boolean isCorrect) {
        Image colorized = isCorrect ?
                dyeImage(backgroundImage, -0.5, +0.5, -0.5) :
                dyeImage(backgroundImage, +0.5, -0.5, -0.5);

        setBackground(new Background(new BackgroundImage(colorized, null, null, null, null)));
    }

    public static Image dyeImage(Image i, double r, double g, double b) {
        WritableImage coloredImage = new WritableImage((int) i.getWidth(), (int) i.getHeight());
        PixelWriter ir = coloredImage.getPixelWriter();
        PixelReader pr = i.getPixelReader(); //Der Pixel an der aktuellen Stelle im Originalbild

        for (int x = 0; x < i.getWidth(); x++) { //Alle Pixel werden durchgegangen
            for (int y = 0; y < i.getHeight(); y++) {
                Color pc = pr.getColor(x,y);
                ir.setColor(x,y, new Color(
                    Math.max(0, Math.min(1.0, pc.getRed() + r)),
                    Math.max(0, Math.min(1.0, pc.getGreen() + g)),
                    Math.max(0, Math.min(1.0, pc.getBlue() + b)),
                    pc.getOpacity()).darker()
                );
            }
        }
        return coloredImage;
    }
}
