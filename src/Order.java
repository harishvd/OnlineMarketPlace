import java.util.List;

public class Order {
    private String orderId;
    private User user;
    private List<CartItem> items;
    private double totalAmount;

    public Order(String orderId, User user, List<CartItem> orderItems, double totalAmount) {
        this.orderId = orderId;
        this.user = user;
        this.items = orderItems;
        this.totalAmount = totalAmount;
    }
}