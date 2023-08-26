import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private List<CartItem> cart = new ArrayList<>();
    private List<Order> orderHistory = new ArrayList<>();

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public List<CartItem> getCart() {
        return this.cart;
    }

    public List<Order> getOrderHistory() {
        return this.orderHistory;
    }
} 