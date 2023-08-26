public class App {
    public static void main(String[] args) throws Exception {
        OnlineMarket onlinemarket = new OnlineMarket();

        //Create user
        User user1 = onlinemarket.createUser("Harish", "harish@gmail.com", "hello123");
        System.out.println("Username: "+ user1.getName() + ", email: " + user1.getEmail());

        User user2 = onlinemarket.createUser("Nidhi ", "nidhi@gmail.com", "hello12");
        System.out.println("Username: "+ user2.getName() + ", email: " + user2.getEmail());


        // Get user
        System.out.println("Get User: " + onlinemarket.getUser(user1.getUserId()).getName());
        //System.out.println("Get User: " + onlinemarket.getUser("10").getName());

        // Get product
        Product product1 = onlinemarket.createProduct("TV", 40000, 5);
        Product product2 = onlinemarket.createProduct("PHONE", 10000, 3);
        System.out.println("Get Product: " + onlinemarket.getProduct(product1.getProductId()).getName());
        System.out.println("Get Product quantity: " + onlinemarket.getProduct(product2.getProductId()).getStock());
        //System.out.println("Get Product: " + onlinemarket.getProduct("10").getName());

        // Add to cart, get cart
        onlinemarket.addToCart(user1.getUserId(), product1.getProductId(), 2);
        onlinemarket.addToCart(user1.getUserId(), product2.getProductId(), 4);
        onlinemarket.addToCart(user2.getUserId(), product2.getProductId(), 1);
        System.out.println("User 1 Cart: " + onlinemarket.getCart(user1.getUserId()));
        System.out.println("User 2 Cart: " + onlinemarket.getCart(user2.getUserId()));


        // Checkout
        onlinemarket.checkout(user1.getUserId());

        // Get order history
        System.out.println("User 1 Order History: " + onlinemarket.getOrderHistory(user1.getUserId()));
    }
}
