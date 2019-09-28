package com.ledinhtuyenbkdn.masterpersonindex.domain;

import com.ledinhtuyenbkdn.masterpersonindex.domain.enumeration.Gender;
import com.ledinhtuyenbkdn.masterpersonindex.domain.enumeration.PersonStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    private String healthInsuranceNumber;

    private String identificationNumber;

    private String address;

    private LocalDate dateOfBirth;

    private Gender gender;

    @Column(precision = 5, scale = 2)
    private double score;

    private PersonStatus personStatus;

    @ManyToOne
    private HealthCenter healthCenter;

    @ManyToOne
    private MasterPerson masterPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PersonStatus getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(PersonStatus personStatus) {
        this.personStatus = personStatus;
    }

    public HealthCenter getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenter healthCenter) {
        this.healthCenter = healthCenter;
    }

    public MasterPerson getMasterPerson() {
        return masterPerson;
    }

    public void setMasterPerson(MasterPerson masterPerson) {
        this.masterPerson = masterPerson;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", personStatus=" + personStatus +
                ", healthCenter=" + healthCenter +
                ", masterPerson=" + masterPerson +
                '}';
    }
}
