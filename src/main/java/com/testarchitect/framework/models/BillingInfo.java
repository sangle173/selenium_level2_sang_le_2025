package com.testarchitect.framework.models;

import com.github.javafaker.Faker;

/**
 * Billing information model for checkout forms
 */
public class BillingInfo {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String address2;
    private final String city;
    private final String state;
    private final String zip;
    private final String phone;
    private final String country;

    public BillingInfo(String firstName, String lastName, String email, String address, 
                      String address2, String city, String state, String zip, String phone, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.country = country;
    }

    // Static factory methods
    public static BillingInfo createDefault() {
        return new BillingInfo(
                "John",
                "Doe", 
                "john.doe@test.com",
                "123 Test Street",
                "Apt 456",
                "Test City",
                "CA",
                "12345",
                "123-456-7890",
                "US"
        );
    }

    public static BillingInfo createRandom() {
        Faker faker = new Faker();
        return new BillingInfo(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.address().streetAddress(),
                faker.address().secondaryAddress(),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().zipCode(),
                faker.phoneNumber().phoneNumber(),
                "US"
        );
    }

    public static BillingInfo createCustom(String firstName, String lastName, String email) {
        Faker faker = new Faker();
        return new BillingInfo(
                firstName,
                lastName,
                email,
                faker.address().streetAddress(),
                faker.address().secondaryAddress(),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().zipCode(),
                faker.phoneNumber().phoneNumber(),
                "US"
        );
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "BillingInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
