package classworks.bookStorage.model;

import classworks.bookStorage.storage.util.DateUtil;

import java.util.Date;
import java.util.Objects;

public class Author {

    private String id;
    private String name;
    private String surname;
    private String phone;
    private Date dateOfBirthday;
    private Gender denger;

    public Author(String id, String name, String surname, String phone, Date dateOfBirthday, Gender denger) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.dateOfBirthday = dateOfBirthday;
        this.denger = denger;
    }

    public Author() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public Gender getDenger() {
        return denger;
    }

    public void setDenger(Gender denger) {
        this.denger = denger;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(surname, author.surname) && Objects.equals(phone, author.phone) && Objects.equals(dateOfBirthday, author.dateOfBirthday) && denger == author.denger;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phone, dateOfBirthday, denger);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirthday=" + DateUtil.fromDateToString(dateOfBirthday) +
                ", denger=" + denger +
                '}';
    }
}