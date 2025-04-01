package classworks.onlineStore.storage;

import classworks.onlineStore.model.*;
import classworks.onlineStore.util.SerializeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {

    private Map<String, User> userMap = new HashMap<>();
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
        SerializeUtil.serializeUserData(userMap);
    }

    public User getUserByEmail(String email) {
        return userMap.get(email);
    }

    public void addProduct(Product product) {
        products.add(product);
        SerializeUtil.serializeProductData(products);
    }

    public void addOrder(Order order) {
        orders.add(order);
        SerializeUtil.serializeOrderData(orders);
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrdersByUser(User user) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().equals(user)) {
                result.add(order);
            }
        }
        return result;
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void printUsers() {
        for (User value : userMap.values()) {
            if (value.getUserType() == UserType.USER){
                System.out.println(value);
            }
        }
    }

    public void printOrders(){
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void changeOrderStatus(Order order, OrderStatus orderStatus) {
        order.setOrderStatus(OrderStatus.CANCELED);
        SerializeUtil.serializeOrderData(orders);
    }

    public void deserializeData() {
        userMap = SerializeUtil.deSerializeUserData();
        products = SerializeUtil.deSerializeProductData();
        orders = SerializeUtil.deSerializeOrderData();
    }

    public void changeProductStockQty(Product product, int qty) {
        product.setStockQty(qty);
        SerializeUtil.serializeProductData(products);
    }
}
