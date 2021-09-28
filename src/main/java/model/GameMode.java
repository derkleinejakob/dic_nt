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

    public boolean reciveInput(boolean rightKlicked){
        if (rightKlicked == rightHiegher()){
            item1 = item2;
            item2 = itemFetcher.fetchItem(item1);
            score++;
            return true;
        }else {
            return false;
        }
    }
    public boolean rightHiegher(){
        return (item1.getEmisions() >= item2.getEmisions());
    }
}
