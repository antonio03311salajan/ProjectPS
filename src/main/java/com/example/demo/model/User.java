package com.example.demo.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String cnp;
    private String phoneNumber;
    private int shift;
    private String specialty;

    public User(String username, String email, String password, String role,
                String firstName, String lastName, String cnp, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.phoneNumber = phoneNumber;
    }


    public User(int id, String username, String email, String password, String role,
                String firstName, String lastName, String cnp, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String username, String email, String password, String role,
                String firstName, String lastName, String cnp, String phoneNumber, String specialty, int workSchedule) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.shift = workSchedule;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getShift() { return shift; }
    public void setShift(int shift) { this.shift = shift; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnp='" + cnp + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
