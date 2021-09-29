package model;

public enum ItemSet {
    PRODUCTS("Produkte", "src/main/resources/data/items.csv"),
    COUNTRIES("Länder", "src/main/resources/data/countries.csv");

    private final String name, dataset;

    ItemSet(String name, String dataset) {
        this.name = name;
        this.dataset = dataset;
    }

    public String toString() {
        return name;
    }

    public String getDataset() {
        return dataset;
    }
}
