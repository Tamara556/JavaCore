package classworks.bookStorage;

import classworks.bookStorage.model.Author;
import classworks.bookStorage.model.Book;
import classworks.bookStorage.model.Gender;
import classworks.bookStorage.storage.AuthorStorage;
import classworks.bookStorage.storage.BookStorage;
import classworks.bookStorage.storage.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Library implements LibraryCommands {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookStorage bookStorage = new BookStorage();
    private static final AuthorStorage authorStorage = new AuthorStorage();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            LibraryCommands.printCommands();
            String command = scanner.nextLine().trim();
            switch (command) {
                case EXIT -> isRun = false;
                case ADD_BOOK -> addBook();
                case ADD_AUTHOR -> addAuthor();
                case PRINT_ALL_BOOKS -> bookStorage.print();
                case PRINT_ALL_AUTHORS -> authorStorage.print();
                case SEARCH_BOOK_BY_NAME -> searchBookByName();
                case UPDATE_BOOK -> updateBook();
                case DELETE_BOOK -> deleteBook();
                case SEARCH_BY_PRICE -> searchBookByPrice();
                default -> System.out.println("❌ Wrong command! Please try again.");
            }
        }
    }

    private static void addAuthor() {
        System.out.println("Please input: id, name, surname, phone, birth date (dd-MM-yyyy), gender (comma-separated)");
        String[] authorDataArr = scanner.nextLine().split(",");

        if (authorDataArr.length != 6) {
            System.out.println("❌ Invalid input format. Please enter 6 values separated by commas.");
            return;
        }

        String id = authorDataArr[0].trim();
        if (authorStorage.getAuthorById(id) != null) {
            System.out.println("❌ Author with ID " + id + " already exists.");
            return;
        }

        try {
            Date birthDate = DateUtil.fromStringToDate(authorDataArr[4].trim());
            Gender gender = Gender.valueOf(authorDataArr[5].trim().toUpperCase());

            Author author = new Author(id, authorDataArr[1].trim(), authorDataArr[2].trim(), authorDataArr[3].trim(), birthDate, gender);
            authorStorage.add(author);
            System.out.println("✅ Author added successfully!");
        } catch (ParseException e) {
            System.out.println("❌ Invalid date format. Please use dd-MM-yyyy.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid gender. Please enter MALE, FEMALE, or OTHER.");
        }
    }

    private static void searchBookByPrice() {
        System.out.println("Please input min-max prices (e.g., 10-50):");
        String[] pricesArray = scanner.nextLine().split("-");

        if (pricesArray.length != 2) {
            System.out.println("❌ Invalid format. Please use min-max format.");
            return;
        }

        try {
            double min = Double.parseDouble(pricesArray[0].trim());
            double max = Double.parseDouble(pricesArray[1].trim());
            bookStorage.searchByPrice(min, max);
        } catch (NumberFormatException e) {
            System.out.println("❌ Please input valid numeric values for prices.");
        }
    }

    private static void deleteBook() {
        bookStorage.print();
        System.out.println("Please input the book ID to delete:");
        String bookId = scanner.nextLine().trim();

        if (bookStorage.getBookById(bookId) == null) {
            System.out.println("❌ Book with ID " + bookId + " does not exist.");
        } else {
            bookStorage.deleteBook(bookId);
            System.out.println("✅ Book deleted successfully!");
        }
    }

    private static void updateBook() {
        bookStorage.print();
        System.out.println("Please input the book ID to update:");
        String bookId = scanner.nextLine().trim();
        Book book = bookStorage.getBookById(bookId);

        if (book == null) {
            System.out.println("❌ Invalid book ID.");
            return;
        }

        authorStorage.print();
        System.out.println("Please choose a new author ID:");
        String authorId = scanner.nextLine().trim();
        Author author = authorStorage.getAuthorById(authorId);

        if (author == null) {
            System.out.println("❌ Invalid author ID.");
            return;
        }

        System.out.println("Please input the book's new title (or press Enter to keep the current title):");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) {
            book.setTitle(title);
        }

        System.out.println("Please input the book's new price (or press Enter to keep the current price):");
        String priceStr = scanner.nextLine().trim();
        if (!priceStr.isEmpty()) {
            try {
                book.setPrice(Double.parseDouble(priceStr));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid price format. Keeping the old price.");
            }
        }

        System.out.println("Please input the book's new quantity (or press Enter to keep the current quantity):");
        String qtyStr = scanner.nextLine().trim();
        if (!qtyStr.isEmpty()) {
            try {
                book.setQty(Integer.parseInt(qtyStr));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid quantity format. Keeping the old quantity.");
            }
        }

        book.setAuthor(author);
        System.out.println("✅ Book updated successfully!");
    }

    private static void searchBookByName() {
        System.out.println("Please input a keyword to search for books:");
        bookStorage.searchByBookName(scanner.nextLine().trim());
    }

    private static void addBook() {
        authorStorage.print();
        System.out.println("Please choose an author ID:");
        String authorId = scanner.nextLine().trim();
        Author author = authorStorage.getAuthorById(authorId);

        if (author == null) {
            System.out.println("❌ Invalid author ID.");
            return;
        }

        System.out.println("Please input book ID:");
        String id = scanner.nextLine().trim();
        if (bookStorage.getBookById(id) != null) {
            System.out.println("❌ Book with ID " + id + " already exists.");
            return;
        }

        System.out.println("Please input book title:");
        String title = scanner.nextLine().trim();

        double price;
        System.out.println("Please input book price:");
        try {
            price = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid price format. Book not added.");
            return;
        }

        int qty;
        System.out.println("Please input quantity:");
        try {
            qty = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid quantity format. Book not added.");
            return;
        }

        bookStorage.add(new Book(id, title, author, price, qty, new Date()));
        System.out.println("✅ Book added successfully!");
    }
}
