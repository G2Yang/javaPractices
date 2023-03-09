package cat.proven.guiprod.model;

import java.util.Objects;

/**
 *
 * @author jose
 */
public class Product {
    private long id;
    private String code;
    private String description;
    private double price;
    private int stock;

    public Product(long id, String code, String description, double price, int stock) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product(long id) {
        this.id = id;
    }

    public Product() {
    }
    
    public Product(Product other) {
        this.id = other.id;
        this.code = other.code;
        this.description = other.description;
        this.price = other.price;
        this.stock = other.stock;
    }    

    public Product(long id, String code) {
        this.id = id;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", description=").append(description);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append('}');
        return sb.toString();
    }
    
}
