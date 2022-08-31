package entities;

public class OrderItem {
    private Product products;
    
    private Double price;
    private Integer quantity;

    public OrderItem(Product products, Double price, Integer quantity) {
        this.products = products;
        this.price = price;
        this.quantity = quantity;
    }

    public Product getProducts() {
        return products;
    }
    public void setProducts(Product products) {
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    } 
    
    public Double subTotal() {
        return quantity * price;
    }
    
    @Override
    public String toString() {
        return products.getName() 
                + ", $" 
                + String.format("%.2f", price)
                + ", Quantity: " 
                + quantity
                + ", Subtotal: $"
                + String.format("%.2f", this.subTotal());
    }
}
