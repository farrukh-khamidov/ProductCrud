package models;

public class Product {

    private String id;
    private String name;
    private String color;
    private int size;
    private String store;

    public Product(String id, String name, String color, int size, String store) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.store = store;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String getStore() {
        return store;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", store='" + store + '\'' +
                '}';
    }
}
