package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameMode {
    private RandomItemFetcher itemFetcher;

    private Item item1;
    private Item item2;

    private IntegerProperty scoreProperty;

    /**
     * @param dataset URL of the Dataset containing the Items compared in the Game
     */
    public GameMode(String dataset){
        itemFetcher = new RandomItemFetcher(dataset);
        item1 = itemFetcher.fetchItem();
        item2 = itemFetcher.fetchItem(item1);
        scoreProperty = new SimpleIntegerProperty(0);
    }

    /**
     * @param rightClicked true if the Player clicked right, false if the Player clicked left
     * @return true if the Player seleced the correct Item, false if the Player seleced the wrong Item
     */
    public boolean receiveInput(boolean rightClicked){
        if (item1.emissions() == item2.emissions() || rightClicked == rightHigher()){
            item1 = item2;
            item2 = itemFetcher.fetchItem(item1);
            scoreProperty.set(scoreProperty.get() + 1);
            return true;
        }else {
            return false;
        }
    }

    /**
     * @return true if the right item emits more emissions, false if the left item emits more emissions
     */
    public boolean rightHigher(){
        return (item1.emissions() >= item2.emissions());
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    public int getScore() {
        return scoreProperty.get();
    }

    public IntegerProperty scorePropertyProperty() {
        return scoreProperty;
    }
}
