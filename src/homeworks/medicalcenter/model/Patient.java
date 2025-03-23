package homeworks.medicalcenter.model;


import homeworks.medicalcenter.util.DateUtil;

import java.util.Date;
import java.util.Objects;

public class Patient extends Person {

    private Doctor doctor;
    private Date dateOfReg;

    public Patient(String id, String name, String surname, String email, String phoneNumber, Doctor doctor, Date dateOfReg) {
        super(id, name, surname, email, phoneNumber);
        this.doctor = doctor;
        this.dateOfReg = dateOfReg;
    }
    public Patient() {
    }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Date getDateOfReg() { return dateOfReg; }
    public void setDateOfReg(Date dateOfReg) { this.dateOfReg = dateOfReg; }


    @Override
    public String toString() {
        return "Patient{" +
                "id='" + super.id + '\'' +
                ", name='" + super.name + '\'' +
                ", surname='" + super.surname + '\'' +
                ", email='" + super.email + '\'' +
                ", phoneNumber='" + super.phoneNumber + '\'' +
                ", doctor=" + doctor +
                ", dateOfReg=" + DateUtil.dateMinute.format(dateOfReg) +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(super.id, patient.id)
                && Objects.equals(super.name, patient.name)
                && Objects.equals(super.surname, patient.surname)
                && Objects.equals(super.email, patient.email)
                && Objects.equals(super.phoneNumber, patient.phoneNumber)
                && Objects.equals(doctor, patient.doctor)
                && Objects.equals(dateOfReg, patient.dateOfReg);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.id, super.name, super.surname,
                super.email, super.phoneNumber, doctor, dateOfReg);
    }

}