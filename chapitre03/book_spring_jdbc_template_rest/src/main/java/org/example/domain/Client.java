package org.example.domain;

public class Client {

    private Integer id;
    private String firstname;
    private String lastname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Client(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public void displayClientInformation() {
        System.out.println("index " + id +
                " | firstname " + firstname +
                " | lastname " + lastname);
    }
}
