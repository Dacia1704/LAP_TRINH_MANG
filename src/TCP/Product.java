package TCP;

import java.io.Serializable;

/**
 * Lớp Product dùng để nhận/gửi qua ObjectStream trong TCP.
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 20231107L;

    private int id;
    private String name;
    private double price;
    private int discount;

    // Constructor mặc định
    public Product() {
    }

    // Constructor tiện lợi
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = 0;
    }

    // Getter / Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
