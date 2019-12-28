package com.ledinhtuyenbkdn.masterpersonindex.service.dto;

import com.ledinhtuyenbkdn.masterpersonindex.model.Province;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Gender;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.PersonStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class PersonDTO {

    private Long id;

    @NotBlank
    private String patientCode;

    @NotBlank
    private String fullName;

    private String healthInsuranceNumber;

    private String identificationNumber;

    private String address;

    @Past
    private LocalDate dateOfBirth;

    private Gender gender;

    @PositiveOrZero
    private Double score;

    private PersonStatus personStatus;

    private HealthCenterDTO healthCenter;

    private Province province;

    private Long masterPersonId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public PersonStatus getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(PersonStatus personStatus) {
        this.personStatus = personStatus;
    }

    public HealthCenterDTO getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenterDTO healthCenter) {
        this.healthCenter = healthCenter;
    }

    public Long getMasterPersonId() {
        return masterPersonId;
    }

    public void setMasterPersonId(Long masterPersonId) {
        this.masterPersonId = masterPersonId;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
