package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameMode {
    private RandomItemFetcher itemFetcher;

    private Item item1;
    private Item item2;

    private IntegerProperty scoreProperty;

    public GameMode(String dataset){
        itemFetcher = new RandomItemFetcher(dataset);
        item1 = itemFetcher.fetchItem();
        item2 = itemFetcher.fetchItem(item1);
        scoreProperty = new SimpleIntegerProperty(0);
    }

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
