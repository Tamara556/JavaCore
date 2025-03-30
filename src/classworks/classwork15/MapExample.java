package classworks.classwork15;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<String, User> userMap = new HashMap<>();
        User user1 = new User("Poxos", "Poxosyan", "poxos@gmail.com");
        User user2 = new User("Petros", "Petrosyan", "petros@gmail.com");

        userMap.put("poxos@gmail.com", user1);
        userMap.put("petros@gmail.com", user2);

    }

    public static class User {
        public String name;
        public String surname;
        public String email;

        public User(String name, String surname, String email) {
            this.name = name;
            this.surname = surname;
            this.email = email;
        }
    }
}
