package model;

public enum ItemSet {
    PRODUCTS("Produkte", "dataset.csv"),
    COUNTRIES("Länder", "dataset.csv");

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
