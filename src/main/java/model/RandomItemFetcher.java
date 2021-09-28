package model;

import java.util.Random;
import java.util.ArrayList;

public class RandomItemFetcher {
    private ArrayList<Item> items;

    public RandomItemFetcher(){
        items = (ArrayList<Item>) new CSVFileManagement().retrieve();
    }

    public Item fetchItem(){
        int rd = new Random().nextInt(items.size());
        return items.get(rd);
    }
    public Item fetchItem(Item comp){
        Item it = fetchItem();
        while (it==comp){
            it = fetchItem();
        }
        return it;
    }
}
