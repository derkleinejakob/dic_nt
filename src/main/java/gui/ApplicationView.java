package gui;

import javafx.scene.layout.Pane;

public abstract class ApplicationView {
    private MainGUI gui;

    protected MainGUI getGUI() {
        return gui;
    }

    public abstract Pane getPane();
}
