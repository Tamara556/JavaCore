package classworks.onlineStore.util;

import classworks.onlineStore.model.Order;
import classworks.onlineStore.model.Product;
import classworks.onlineStore.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SerializeUtil {

    private static final String USERS_DATA_PATH = "C:\\Users\\MaGa Mobile\\IdeaProjects\\JavaCore\\src\\classworks\\onlineStore\\data\\users.data";
    private static final String PRODUCTS_DATA_PATH = "C:\\Users\\MaGa Mobile\\IdeaProjects\\JavaCore\\src\\classworks\\onlineStore\\data\\products.data";
    private static final String ORDERS_DATA_PATH = "C:\\Users\\MaGa Mobile\\IdeaProjects\\JavaCore\\src\\classworks\\onlineStore\\data\\orders.data";

    public static void serializeUserData(Map<String, User> userMap) {
        File usersFile = new File(USERS_DATA_PATH);
        try {
            if (!usersFile.exists()) {
                usersFile.createNewFile();
            }
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(usersFile))) {
                outputStream.writeObject(userMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void serializeProductData(List<Product> productList) {
        File productFile = new File(PRODUCTS_DATA_PATH);
        try {
            if (!productFile.exists()) {
                productFile.createNewFile();
            }
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(productFile))) {
                outputStream.writeObject(productList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeOrderData(List<Order> orderList) {
        File orderFile = new File(PRODUCTS_DATA_PATH);
        try {
            if (!orderFile.exists()) {
                orderFile.createNewFile();
            }
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(orderFile))) {
                outputStream.writeObject(orderList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> deSerializeUserData() {
        File usersFile = new File(USERS_DATA_PATH);
        try {
            if (usersFile.exists()) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(usersFile))) {
                    return (Map<String, User>) inputStream.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static List<Product> deSerializeProductData() {
        File productFile = new File(PRODUCTS_DATA_PATH);
        try {
            if (!productFile.exists()) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(productFile))) {
                  return (List<Product>) inputStream.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Order> deSerializeOrderData() {
        File orderFile = new File(ORDERS_DATA_PATH);
        try {
            if (!orderFile.exists()) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(orderFile))) {
                    return (List<Order>) inputStream.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
