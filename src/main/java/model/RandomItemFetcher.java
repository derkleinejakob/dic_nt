package model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class RandomItemFetcher {
    private List<Item> items;

    public RandomItemFetcher(){
        items = new CSVFileManagement().retrieve();
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
