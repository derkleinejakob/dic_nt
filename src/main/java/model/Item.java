package model;

public class Item {
    private String title;
    private String description;
    private double emisions;
    private String image;

    public Item(){
        title = "Apfel";
        description = "Ein Apfel";
        emisions = 1.0;
        image = "";
    }

    public Item(String title, String description, int emisions, String image){
        this.title = title;
        this.description = description;
        this.image = image;
        this.emisions = emisions;
    }

    public double getEmisions(){
        return emisions;
    }
}
