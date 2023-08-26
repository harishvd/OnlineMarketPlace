import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.CartEmptyException;
import exceptions.InvalidEmailException;
import exceptions.ProductNotFoundException;
import exceptions.UserNotFoundException;

public class OnlineMarket {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Order> orders = new HashMap<>();
    private int userCounter = 1;
    private int productCounter = 1;
    private int orderCounter = 1;

    private boolean validateUserEmail(String email) {
        // Validate and return true or false
        return true;
    }

    public User createUser(String name, String email, String password) {
        if (!validateUserEmail(email)) {
            throw new InvalidEmailException("Invalid Email");
        }
        String userId = Integer.toString(userCounter++);
        User user = new User(userId, name, email, password);
        users.put(userId, user);
        return user;
    }

    public User getUser(String userId) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("User not found");
        }
        return users.get(userId);
    }

    public Product createProduct(String name, double price, int stock) {
        String productId = Integer.toString(productCounter++);
        Product product = new Product(productId, name, price, stock);
        products.put(productId, product);
        return product;
    }

    public Product getProduct(String productId) {
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found");
        }
        return products.get(productId);
    }

    public List<CartItem> getCart(String userId) {
        User user = getUser(userId);
        return (user != null) ? user.getCart() : new ArrayList<>();
    }

    public void addToCart(String userId, String productId, int quantity) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("User not found");
        }
        if (!products.containsKey(productId)) {
            throw new ProductNotFoundException("Product not found");
        }

        User user = getUser(userId);
        Product product = getProduct(productId);

        for (CartItem item : user.getCart()) {
            if (item.getProduct().getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem cartItem = new CartItem(product, quantity);
        user.getCart().add(cartItem);
    }

    public void checkout(String userId) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("User not found");
        }
        User user = getUser(userId);
        if (user.getCart().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }
        
        List<CartItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem cartItem : user.getCart()) {
            Product product = cartItem.getProduct();
            if (product.getStock() >= cartItem.getQuantity()) {
                orderItems.add(cartItem);
                totalAmount += product.getPrice() * cartItem.getQuantity();
                product.setStock(product.getStock() - cartItem.getQuantity());
            }
        }

        if (!orderItems.isEmpty()) {
            String orderId = Integer.toString(orderCounter++);
            Order order = new Order(orderId, user, orderItems, totalAmount);
            user.getOrderHistory().add(order);
            orders.put(orderId, order);
            user.getCart().clear();
        }
        
    }

    public List<Order> getOrderHistory(String userId) {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("User not found");
        }
        User user = getUser(userId);
        return user.getOrderHistory();
    }
}
