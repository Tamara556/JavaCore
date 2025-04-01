package classworks.onlineStore;

import classworks.onlineStore.model.*;
import classworks.onlineStore.storage.DataStorage;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class OnlineStore {

    private static DataStorage dataStorage = new DataStorage();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        dataStorage.deserializeData();
        boolean isRun = true;
        while (isRun) {
            printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    loginUser();
                    break;
                case "2":
                    registerUser();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    private static void loginUser() {
        System.out.println("Please input email, password");
        String userDataStr = scanner.nextLine();
        String[] userDataArr = userDataStr.split(",");
        String email = userDataArr[0];
        String password = userDataArr[1];
        User userByEmail = dataStorage.getUserByEmail(email);
        if (userByEmail != null && userByEmail.getPassword().equals(password)) {
            currentUser = userByEmail;
            if (userByEmail.getUserType() == UserType.USER) {
                userCommands();
            } else {
                adminCommands();
            }
        }
    }

    private static void userCommands() {
        boolean isRun = true;
        while (isRun) {
            printUserCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    currentUser = null;
                    isRun = false;
                    break;
                case "1":
                    dataStorage.printProducts();
                    break;
                case "2":
                    buyProduct();
                    break;
                case "3":
                    System.out.println(dataStorage.getOrdersByUser(currentUser));
                    break;
                case "4":
                    cancelOrderById();
                    break;
                default:
                    System.out.println("Ivalid command");
            }
        }
    }

    private static void cancelOrderById() {
        System.out.println((dataStorage.getOrdersByUser(currentUser)));
        System.out.println("Please input order id");
        String orderId = scanner.nextLine();
        Order order = dataStorage.getOrderById(orderId);
        if(order != null && order.getUser().equals(currentUser)){
            dataStorage.changeOrderStatus(order, OrderStatus.CANCELED);
            System.out.println("Order status is CANCELED");
        } else {
            System.out.println("Invalid order");
        }
    }

    private static void buyProduct() {
        dataStorage.printProducts();
        System.out.println("Please input product id");
        String productId = scanner.nextLine();
        Product productById = dataStorage.getProductById(productId);
        if(productById != null){
            System.out.println("Please input qty, paymentType CARD/CASH/PAYPAL");
            String orderDetailsStr = scanner.nextLine();
            String[] orderDetailsArr = orderDetailsStr.split(",");
            Order order = new Order();
            order.setId("ID" + new Random().nextInt(1500));
            order.setProduct(productById);
            order.setQty(Integer.parseInt(orderDetailsArr[0]));
            order.setDate(new Date());
            order.setUser(currentUser);
            order.setPaymentMethod(PaymentMethod.valueOf(orderDetailsArr[1]));
            order.setPrice(order.getQty() * productById.getPrice());
            order.setOrderStatus(OrderStatus.NEW);
            dataStorage.addOrder(order);
        }
    }

    private static void adminCommands() {
        boolean isRun = true;
        while (isRun) {
            printAdminCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    currentUser = null;
                    isRun = false;
                    break;
                case "1":
                    addProduct();
                    break;
                case "2":
                    removeProduct();
                    break;
                case "3":
                    dataStorage.printProducts();
                    break;
                case "4":
                    dataStorage.printUsers();
                    break;
                case "5":
                    dataStorage.printOrders();
                    break;
                case "6":
                    changeOrderStatus();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static void changeOrderStatus() {
        dataStorage.printOrders();
        System.out.println("Please choose order id");
        String orderId = scanner.nextLine();
        Order orderById = dataStorage.getOrderById(orderId);
        if (orderById != null){
            System.out.println("Please input new order status NEW/DELIVERED/CANCELED");
            String newOrderStr = scanner.nextLine();
            OrderStatus orderStatus = OrderStatus.valueOf(newOrderStr);
            if (orderStatus == OrderStatus.DELIVERED){
                Product product = orderById.getProduct();
                if (product.getStockQty() < orderById.getQty()){
                    System.out.println("Products qty less then order's qty");
                    return;
                }
                product.setStockQty(product.getStockQty() - orderById.getQty());
                dataStorage.changeProductStockQty(product, product.getStockQty() - orderById.getQty());
                dataStorage.changeOrderStatus(orderById, OrderStatus.DELIVERED);
                System.out.println("Order status has been updated!");
            } else {
                dataStorage.changeOrderStatus(orderById, orderStatus);
                System.out.println("Order status has been updated!");

            }
        }
    }

    private static void removeProduct() {

    }

    private static void addProduct() {
        System.out.println("Please input id, name, description, price, stockQty, type(ELECTRONICS,CLOTHING,BOOKS),");
        String productDataStr = scanner.nextLine();
        String[] productDataArr = productDataStr.split(",");
        Product product = new Product();
        product.setId(productDataArr[0]);
        product.setName(productDataArr[1]);
        product.setDescription(productDataArr[2]);
        product.setPrice(Double.parseDouble(productDataArr[3]));
        product.setStockQty(Integer.parseInt(productDataArr[4]));
        product.setProductType(ProductType.valueOf(productDataArr[5]));
        dataStorage.addProduct(product);
        System.out.println("Product added");
    }

    private static void printAdminCommands() {
        System.out.println("Please input 0 for LOGOUT");
        System.out.println("Please input 1 for ADD PRODUCT");
        System.out.println("Please input 2 for REMOVE PRODUCT");
        System.out.println("Please input 3 for PRINT PRODUCTS");
        System.out.println("Please input 4 for PRINT USERS");
        System.out.println("Please input 5 for PRINT ORDERS");
        System.out.println("Please input 6 for CHANGE ORDER STATUS");
    }

    private static void printUserCommands() {
        System.out.println("Please input 0 for LOGOUT");
        System.out.println("Please input 1 for PRINT PRODUCTS");
        System.out.println("Please input 2 for BUY PRODUCTS");
        System.out.println("Please input 3 for PRINT MY ORDERS");
        System.out.println("Please input 4 for CHANCEL ORDER BY ID");


    }

    private static void registerUser() {
        System.out.println("Please input id, name, surname, email, password, type(USER/ADMIN)");
        String userDataStr = scanner.nextLine();
        String[] userDataArr = userDataStr.split(",");
        String email = userDataArr[3];
        if (dataStorage.getUserByEmail(email) == null) {
            User user = new User(userDataArr[0], userDataArr[1], userDataArr[2], userDataArr[3], userDataArr[4], UserType.valueOf(userDataArr[5]));
            dataStorage.addUser(user);
            System.out.println("You have registered successfully");
        } else {
            System.out.println("User already exist by this email");
        }
    }

    private static void printCommands() {
        System.out.println("Please input 0 for EXIT");
        System.out.println("Please input 1 for LOGIN");
        System.out.println("Please input 2 for REGISTER");

    }
}
