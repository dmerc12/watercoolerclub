package com.watercooler.entities;

import java.util.Objects;

public class Company {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String aboutUs;
    private String relatedJobs;
    private String location;

    public Company() {

    }

    public Company(int id, String name, String phoneNumber, String email, String aboutUs, String relatedJobs, String location) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.aboutUs = aboutUs;
        this.relatedJobs = relatedJobs;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getRelatedJobs() {
        return relatedJobs;
    }

    public void setRelatedJobs(String relatedJobs) {
        this.relatedJobs = relatedJobs;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && phoneNumber == company.phoneNumber && Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(aboutUs, company.aboutUs) && Objects.equals(relatedJobs, company.relatedJobs) && Objects.equals(location, company.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, aboutUs, relatedJobs, location);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", aboutUs='" + aboutUs + '\'' +
                ", relatedJobs='" + relatedJobs + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
