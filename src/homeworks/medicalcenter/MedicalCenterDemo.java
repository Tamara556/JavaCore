package homeworks.medicalcenter;

import homeworks.medicalcenter.commands.ChangeDataCommands;
import homeworks.medicalcenter.commands.Commands;
import homeworks.medicalcenter.model.Doctor;
import homeworks.medicalcenter.model.Patient;
import homeworks.medicalcenter.model.Profession;
import homeworks.medicalcenter.storage.Storage;
import homeworks.medicalcenter.util.DateUtil;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import static homeworks.medicalcenter.commands.ChangeDataCommands.*;
import static homeworks.medicalcenter.commands.Commands.*;

public class MedicalCenterDemo {

        static private final Scanner sc = new Scanner(System.in);
        static private final Storage storage = new Storage();
        static private final Profession[] values = Profession.values();

        private static boolean isValidEmail(String email) {
            return email.contains("@") && (email.endsWith(".ru") || email.endsWith(".com"));
        }
        private static boolean isDataEmpty(String data){
            return data.equals("");
        }

        public static void main(String[] args) throws ParseException {

            boolean isRun = true;
            while (isRun) {
                Commands.printCommands();
                String command = sc.nextLine();
                switch (command) {

                    case EXIT:
                        System.out.println("Bye!");
                        isRun = false;
                        break;

                    case ADD_DOCTOR:
                        addDoctor();
                        break;

                    case SEARCH_DOCTOR_BY_PROFESSION:
                        if (storage.getSize() == 0) {
                            System.out.println("There is no doctor!");
                        } else {
                            searchDoctorByProfession();
                        }
                        break;

                    case DELETE_DOCTOR_BY_ID:
                        if (storage.getSize() == 0) {
                            System.out.println("There is no doctor!");
                        } else {
                            storage.printDoctors();
                            System.out.println("Choose doctor!");
                            System.out.print("input id: ");
                            String delId = sc.nextLine();
                            storage.deleteDoctorById(delId);
                        }
                        break;

                    case CHANGE_DOCTOR_DATA_BY_ID:
                        if (storage.getSize() == 0) {
                            System.out.println("There is no doctor!");
                        } else {
                            changeDoctorDataById();
                        }
                        break;

                    case ADD_PATIENT:
                        addPatient();
                        break;

                    case PRINT_ALL_PATIENTS_BY_DOCTOR:
                        if (storage.getSize() == 0) {
                            System.out.println("There is no doctor!");
                        } else {
                            printAllPatientsByDoctor();
                        }
                        break;

                    case PRINT_TODAYS_PATIENTS:
                        storage.printTodaysPatients();
                        break;

                    default:
                        System.out.println("wrong command!");
                }
            }

        }

        private static void printAllPatientsByDoctor() {
            storage.printDoctors();
            System.out.println("Choose Doctor!");
            System.out.print("input doctor's id: ");
            String doctorID = sc.nextLine();

            Doctor doctor = storage.getDoctorById(doctorID);

            if (doctor == null) {  // if getDoctorById does not find the doctor it returns null
                System.out.println("Doctor with id " + doctorID + " not found!");
            } else {
                storage.printAllPatientsByDoctor(doctor);
            }
        }

        private static void addPatient() {

            if (storage.getSize() == 0) {
                System.out.println("There is no doctor!");
            } else {
                storage.printDoctors();
                System.out.println("Choose doctor!");
                System.out.print("input doctor's id: ");
                String patientsDoctorId = sc.nextLine();
                Doctor patientsDoctor = storage.getDoctorById(patientsDoctorId);

                if (patientsDoctor == null) {
                    System.out.println("Doctor with id " + patientsDoctorId + " not found");
                } else {
                    System.out.print("input id: ");
                    String patientId = sc.nextLine();
                    while(isDataEmpty(patientId) || !storage.idChecker(patientId)) {
                        if (isDataEmpty(patientId)) {
                            System.out.println("Id can’t be empty");
                            System.out.print("input id: ");
                            patientId = sc.nextLine();
                        }
                        if (!storage.idChecker(patientId)) {
                            System.out.println("This id is already used by another user");
                            System.out.println("Please input another id");
                            System.out.print("input id: ");
                            patientId = sc.nextLine();
                        }
                    }

                    System.out.print("input name: ");
                    String patientName = sc.nextLine();
                    while(isDataEmpty(patientName)){
                        System.out.println("Name can't be empty");
                        System.out.print("input name: ");
                        patientName = sc.nextLine();
                    }

                    System.out.print("input surname: ");
                    String patientSurname = sc.nextLine();
                    while(isDataEmpty(patientSurname)){
                        System.out.println("Surname can't be empty");
                        System.out.print("input surname: ");
                        patientSurname = sc.nextLine();
                    }

                    System.out.print("input email: ");
                    String patientEmail = sc.nextLine();
                    boolean ec = isValidEmail(patientEmail);
                    while (!ec) {
                        System.out.println("wrong email format, try again! (ex@mail.ru, ex@gmail.com)");
                        System.out.print("email: ");
                        patientEmail = sc.nextLine();
                        ec = isValidEmail(patientEmail);
                    }

                    System.out.print("input phone number: ");
                    String patientPhoneNumber = sc.nextLine();
                    while(isDataEmpty(patientPhoneNumber)){
                        System.out.println("Phone number can't be empty");
                        System.out.print("input phone number: ");
                        patientPhoneNumber = sc.nextLine();
                    }

                    Date date;
                    System.out.print("input date (dd/MM/yyyy hh:mm): ");
                    String dateSTR = sc.nextLine();

                    try {
                        date = DateUtil.dateMinute.parse(dateSTR);
                    } catch (ParseException e) {
                        System.out.println("Error: wrong date format! example (12/08/2000 12:30)");
                        return;
                    }

                    if (DateUtil.registeredPatientDateChecker(date)) { // false if inputted date already in forgiveness
                        if (storage.dateChecker(date)) { // false if inputted date already booked
                            Patient patient = new Patient(patientId, patientName, patientSurname, patientEmail,
                                    patientPhoneNumber, patientsDoctor, date);
                            storage.addPerson(patient);
                            System.out.println("Patient successfully registered");
                        } else {
                            System.out.println("This date is already booked");
                        }
                    } else {
                        System.out.println("The time you have given is already in the past!");
                    }
                }
            }
        }

        private static void changeDoctorDataById() {
            storage.printDoctors();
            System.out.println("Choose the id of the doctor whose data you want to change!");
            System.out.print("input id: ");
            String changeId = sc.nextLine();
            Doctor doctorDataChange = storage.getDoctorById(changeId);
            if (doctorDataChange != null) {
                System.out.println("Doctor with id " + changeId + " found!");
                System.out.println(doctorDataChange);

                boolean isRun = true;
                while (isRun) {
                    ChangeDataCommands.printCommands();
                    String command = sc.nextLine();
                    switch (command) {

                        case EXIT2:
                            isRun = false;
                            break;

                        case CHANGE_NAME:
                            System.out.print("new name: ");
                            String newName = sc.nextLine();
                            while(isDataEmpty(newName)){
                                System.out.println("Name can't be empty");
                                System.out.print("input name: ");
                                newName = sc.nextLine();
                            }
                            doctorDataChange.setName(newName);
                            System.out.println("Name changed!");
                            isRun = false;
                            break;

                        case CHANGE_SURNAME:
                            System.out.print("new surname: ");
                            String newSurname = sc.nextLine();
                            while(isDataEmpty(newSurname)){
                                System.out.println("Surname can't be empty");
                                System.out.print("input surname: ");
                                newSurname = sc.nextLine();
                            }
                            doctorDataChange.setSurname(newSurname);
                            System.out.println("Surname changed!");
                            isRun = false;
                            break;

                        case CHANGE_EMAIL:
                            System.out.print("new email: ");
                            String newEmail = sc.nextLine();
                            boolean ec = isValidEmail(newEmail);
                            while (!ec) {
                                System.out.println("wrong email format, try again! (ex@mail.ru, ex@gmail.com");
                                System.out.print("input email: ");
                                newEmail = sc.nextLine();
                                ec = isValidEmail(newEmail);
                            }
                            doctorDataChange.setEmail(newEmail);
                            System.out.println("Email changed!");
                            isRun = false;
                            break;

                        case CHANGE_PHONE_NUMBER:
                            System.out.print("new phone number: ");
                            String newPhoneNumber = sc.nextLine();
                            while(isDataEmpty(newPhoneNumber)){
                                System.out.println("Phone number can't be empty");
                                System.out.print("input phone number: ");
                                newPhoneNumber = sc.nextLine();
                            }
                            doctorDataChange.setPhoneNumber(newPhoneNumber);
                            System.out.println("Phone number changed!");
                            isRun = false;
                            break;

                        case CHANGE_PROFESSION:
                            System.out.println("Choose profession!");
                            for (Profession value : values) {
                                System.out.println(value);
                            }

                            System.out.print("profession: ");
                            String newProfession = sc.nextLine();

                            try {
                                doctorDataChange.setProfession(Profession.valueOf(newProfession));
                                System.out.println("Profession changed!");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Profession " + newProfession + " not found!");
                                return;
                            }
                            isRun = false;
                            break;

                        case CHANGE_ALL:
                            // Change Name
                            System.out.print("new name: ");
                            String cName = sc.nextLine();
                            while(isDataEmpty(cName)){
                                System.out.println("Name can't be empty");
                                System.out.print("input name: ");
                                cName = sc.nextLine();
                            }

                            // Change surname
                            System.out.print("new surname: ");
                            String cSurname = sc.nextLine();
                            while(isDataEmpty(cSurname)){
                                System.out.println("Surname can't be empty");
                                System.out.print("input surname: ");
                                cSurname = sc.nextLine();
                            }

                            // Chane email
                            System.out.print("new email: ");
                            String cEmail = sc.nextLine();
                            boolean cEc = isValidEmail(cEmail);
                            while (!cEc) {
                                System.out.println("wrong email format, try again! (ex@mail.ru, ex@gmail.com)");
                                System.out.print("email: ");
                                cEmail = sc.nextLine();
                                cEc = isValidEmail(cEmail);
                            }

                            // Change Phone number
                            System.out.print("new phone number: ");
                            String cPhoneNumber = sc.nextLine();
                            while(isDataEmpty(cPhoneNumber)){
                                System.out.println("Phone number can't be empty");
                                System.out.print("input phone number: ");
                                cPhoneNumber = sc.nextLine();
                            }

                            // Change profession
                            System.out.println("Choose profession!");
                            for (Profession value : values) {
                                System.out.println(value);
                            }
                            System.out.print("new profession: ");
                            String cProfession = sc.nextLine();
                            try {
                                doctorDataChange.setProfession(Profession.valueOf(cProfession));
                                System.out.println("Profession changed!");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Profession " + cProfession + " not found!");
                                return;
                            }

                            // Set Data
                            doctorDataChange.setName(cName);
                            doctorDataChange.setSurname(cSurname);
                            doctorDataChange.setEmail(cEmail);
                            doctorDataChange.setPhoneNumber(cPhoneNumber);

                            System.out.println("Doctor information successfully changed");
                            System.out.println(doctorDataChange);

                            isRun = false;
                            break;
                        default:
                            System.out.println("wrong command!");
                    }
                }

            } else {
                System.out.println("Doctor with id " + changeId + " not found!");
            }
        }

        private static void searchDoctorByProfession() {
            System.out.println("Choose profession!");

            for (Profession value : values) {
                System.out.println(value);
            }

            System.out.print("input profession: ");
            String searchProfession = sc.nextLine();
            Doctor dc;

            try {
                dc = storage.searchDoctorByProfession(Profession.valueOf(searchProfession));
            } catch (IllegalArgumentException iae) {
                System.out.println("Profession " + searchProfession + " not found!");
                return;
            }

            if (dc != null) {
                System.out.println(dc);
            } else {
                System.out.println("There is no doctor with this profession!");
            }
        }

        private static void addDoctor() {
            System.out.print("input id: ");
            String doctorId = sc.nextLine();
            while(isDataEmpty(doctorId) || !storage.idChecker(doctorId)) {
                if (isDataEmpty(doctorId)) {
                    System.out.println("Id can’t be empty");
                    System.out.print("input id: ");
                    doctorId = sc.nextLine();
                }
                if (!storage.idChecker(doctorId)) {
                    System.out.println("This id is already used by another user");
                    System.out.println("Please input another id");
                    System.out.print("input id: ");
                    doctorId = sc.nextLine();
                }
            }

            System.out.print("input name: ");
            String doctorName = sc.nextLine();
            while(isDataEmpty(doctorName)){
                System.out.println("Name can't be empty");
                System.out.print("input name: ");
                doctorName = sc.nextLine();
            }

            System.out.print("input surname: ");
            String doctorSurname = sc.nextLine();
            while(isDataEmpty(doctorSurname)){
                System.out.println("Surname can't be empty");
                System.out.print("input surname: ");
                doctorSurname = sc.nextLine();
            }

            System.out.print("input email: ");
            String doctorEmail = sc.nextLine();
            boolean ec = isValidEmail(doctorEmail);
            while (!ec) {
                System.out.println("wrong email format, try again! (ex@mail.ru, ex@gmail.com");
                System.out.print("input email: ");
                doctorEmail = sc.nextLine();
                ec = isValidEmail(doctorEmail);
            }

            System.out.print("input phone number: ");
            String doctorPhoneNumber = sc.nextLine();
            while(isDataEmpty(doctorPhoneNumber)){
                System.out.println("Phone number can't be empty");
                System.out.print("input phone number: ");
                doctorPhoneNumber = sc.nextLine();
            }

            System.out.println("Choose profession!");
            for (Profession value : values) {
                System.out.println(value);
            }

            System.out.print("input profession: ");
            String doctorProfession = sc.nextLine();

            Doctor newDoctor;

            try {
                newDoctor = new Doctor(doctorId, doctorName, doctorSurname, doctorEmail,
                        doctorPhoneNumber, Profession.valueOf(doctorProfession));
            } catch (IllegalArgumentException e) {
                System.out.println("No profession named " + doctorProfession + " found");
                return;
            }

            storage.addPerson(newDoctor);
            System.out.println("Doctor successfully added!");

        }

}
