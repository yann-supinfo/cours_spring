package org.example.dto;

public class TeamSaison {

    private Long id;
    private Long saisonId;
    private Long teamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaisonId() {
        return saisonId;
    }

    public void setSaisonId(Long saisonId) {
        this.saisonId = saisonId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
