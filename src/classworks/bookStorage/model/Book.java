package classworks.bookStorage.model;

import classworks.bookStorage.storage.util.DateUtil;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Objects;

public class Book {

    private String id;
    private String title;
    private Author author;
    private double price;
    private int qty;
    private Date createdAt;

    public Book(String id, String title, Author author, double price, int qty, Date createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(price, book.price) == 0 && qty == book.qty && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(createdAt, book.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price, qty, createdAt);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", price=" + price +
                ", qty=" + qty +
                ", createdAt=" + DateUtil.fromDateToString(createdAt) +
                '}';
    }
}