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
import model.Item;
import model.ItemSet;

public class ItemPane extends BorderPane {
    private final ItemSet gameSet;
    private final Label titleLabel, emissionLabel, descriptionLabel;
    //save the background image to be able to colorize it
    private Image backgroundImage;

    public ItemPane(EventHandler<MouseEvent> onclick, Item item, ItemSet set) {
        gameSet = set;

        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);
        addEventHandler(MouseEvent.MOUSE_CLICKED, onclick);
        setStyle("-fx-cursor: hand; ");

        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-size: 30; -fx-text-fill: white");
        emissionLabel = new Label();
        emissionLabel.setStyle("-fx-font-size: 50; -fx-text-fill: white");
        descriptionLabel = new Label();
        descriptionLabel.setStyle("-fx-font-size: 15; -fx-text-fill: white");
        VBox centerBox = new VBox(titleLabel, descriptionLabel, emissionLabel);
        centerBox.setAlignment(Pos.CENTER);
        setCenter(centerBox);

        switchTo(item);
    }

    /**
     * Change the item that is displayed in this item pane
     */
    public void switchTo(Item item) {
        backgroundImage = darkenImage(new Image("file:src/main/resources/" + item.image()));
        setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)));

        titleLabel.setText(item.title());
        descriptionLabel.setText(item.description());
        emissionLabel.setText(item.emissions() +gameSet.getUnit()+" CO₂");
    }

    public void showResult() {
        emissionLabel.setOpacity(1);
    }
    public void hideResult() {
        emissionLabel.setOpacity(0);
    }

    public void colorize(boolean isCorrect) {
        Image colorized = isCorrect ?
                dyeImage(backgroundImage, -0.5, +0.5, -0.5) :
                dyeImage(backgroundImage, +0.5, -0.5, -0.5);

        setBackground(new Background(new BackgroundImage(colorized, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null)));
    }

    public static Image darkenImage(Image i) {
        WritableImage coloredImage = new WritableImage((int) i.getWidth(), (int) i.getHeight());
        PixelWriter ir = coloredImage.getPixelWriter();
        PixelReader pr = i.getPixelReader(); //read the pixels in the orignal image

        for (int x = 0; x < i.getWidth(); x++) { //loop through all the pixels
            for (int y = 0; y < i.getHeight(); y++) {
                //copy a darker pixel to the current position
                ir.setColor(x,y, pr.getColor(x, y).deriveColor(0, 1, 0.3, 1));
            }
        }
        return coloredImage;
    }

    public static Image dyeImage(Image i, double r, double g, double b) {
        WritableImage coloredImage = new WritableImage((int) i.getWidth(), (int) i.getHeight());
        PixelWriter ir = coloredImage.getPixelWriter();
        PixelReader pr = i.getPixelReader();

        for (int x = 0; x < i.getWidth(); x++) {
            for (int y = 0; y < i.getHeight(); y++) {
                Color pc = pr.getColor(x,y);
                //alter the color values accordingly
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
