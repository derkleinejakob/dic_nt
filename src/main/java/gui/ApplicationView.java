package gui;

import javafx.scene.layout.Pane;

public abstract class ApplicationView {
    private final MainGUI gui;

    public ApplicationView(MainGUI gui) {
        this.gui = gui;
    }

    protected MainGUI getGUI() {
        return gui;
    }

    public abstract Pane getPane();
}
