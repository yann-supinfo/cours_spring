package org.example.dto;

import java.time.LocalDateTime;

public class ClashFormDto {
    private Long dayId;
    private Long homeTeamId;
    private Long awayTeamId;
    private String clashDate;


    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getClashDate() {
        return clashDate;
    }

    public void setClashDate(String clashDate) {
        this.clashDate = clashDate;
    }
}
