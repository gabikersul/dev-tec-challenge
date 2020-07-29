package model;


public class Product {

    private String id;
    private Integer quantity;
    private Double price;
    private Double productTotalPrice;

    public Product(String id, Integer quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getProductTotalPrice(){
        return this.quantity * this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
