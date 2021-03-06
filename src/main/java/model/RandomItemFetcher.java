package model;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class RandomItemFetcher {
    private List<Item> items;

    public RandomItemFetcher(String dataset){
        items = new CSVFileManagement().retrieve(dataset);
    }

    /**
     * @return a random Item from an Itemlist
     */
    public Item fetchItem(){
        if(items.size() == 0) {
            throw new IllegalArgumentException("No items loaded!");
        }
        int rd = new Random().nextInt(items.size());
        return items.get(rd);
    }

    /**
     * @param comp a refrence Item that will not be selected
     * @return a random Item from an Itemlist
     */
    public Item fetchItem(Item comp){
        Item it = fetchItem();
        while (it==comp){
            it = fetchItem();
        }
        return it;
    }
}
