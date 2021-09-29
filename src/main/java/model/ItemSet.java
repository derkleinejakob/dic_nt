package model;

public enum ItemSet {
    PRODUCTS("Produkte", "src/main/resources/data/items.csv", "g"),
    COUNTRIES("Länder", "src/main/resources/data/countries.csv", " Giga-Tonnen");

    private final String name, dataset, unit;

    ItemSet(String name, String dataset, String unit) {
        this.name = name;
        this.dataset = dataset;
        this.unit = unit;
    }

    public String toString() {
        return name;
    }

    public String getDataset() {
        return dataset;
    }

    public String getUnit() {
        return unit;
    }
}
