package gui;

import javafx.scene.layout.Pane;

/**
 * An application view consists of one pane that is to be displayed in the center of the application.
 * It also has a reference to the window it is contained in, so that it can change the application's state (go back to the menu, start the game, ...).
 */
public abstract class ApplicationView {
    private final MainGUI gui;

    public ApplicationView(MainGUI gui) {
        this.gui = gui;
    }

    protected MainGUI getGUI() {
        return gui;
    }

    /**
     * Returns the content pane of this view. This will be displayed in the center of the MainGUI.
     */
    public abstract Pane getPane();
}
