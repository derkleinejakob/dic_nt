package model;

public record Item(
    String title,
    String description,
    double emissions,
    String image) {

    public Item(){
        this("Apfel", "Ein Apfel", 1.0, "");
    }
}
