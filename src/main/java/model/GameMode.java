package model;

public class GameMode {
    private RandomItemFetcher itemFetcher;

    private Item item1;
    private Item item2;

    private int score;

    public GameMode(){
        itemFetcher = new RandomItemFetcher();
        item1 = itemFetcher.fetchItem();
        item2 = itemFetcher.fetchItem(item1);
        score = 0;
    }

    public boolean receiveInput(boolean rightClicked){
        if (item1.emissions() == item2.emissions() || rightClicked == rightHigher()){
            item1 = item2;
            item2 = itemFetcher.fetchItem(item1);
            score++;
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
}
