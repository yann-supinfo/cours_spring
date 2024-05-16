package org.example.domain;

public class Client {

    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Client(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void displayClientInformation(int index) {
        System.out.println("index " + index +
                " | firstname " + firstname +
                " | lastname " + lastname);
    }
}
