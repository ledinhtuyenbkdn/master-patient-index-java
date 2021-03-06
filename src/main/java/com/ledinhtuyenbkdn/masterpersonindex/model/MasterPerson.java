package com.ledinhtuyenbkdn.masterpersonindex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
public class MasterPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String globalId;

    @NotBlank
    private String fullName;

    @Column(unique = true)
    private String healthInsuranceNumber;

    @Column(unique = true)
    private String identificationNumber;

    private String address;

    @Past
    private LocalDate dateOfBirth;

    private Gender gender;

    @OneToMany(mappedBy = "masterPerson")
    @JsonIgnore
    private List<Person> people;

    @ManyToOne
    private Province province;

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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    @Override
    public String toString() {
        return "MasterPerson{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", people=" + people +
                '}';
    }
}
