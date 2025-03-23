package classworks.student;

public class Student {
    String name;
    String surname;
    String phoneNumber;
    int age;
    String lessonName;
    String email;

    Student(String name, String surname, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    String printStudent(){
        return "Name:" + name + " Surname:" + surname + " Phone Number:" + phoneNumber;
    }

}
