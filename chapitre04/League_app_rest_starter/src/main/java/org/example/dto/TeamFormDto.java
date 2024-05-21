package org.example.dto;

public class TeamFormDto {
    private String name;

    public TeamFormDto() {

    }

    public TeamFormDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
