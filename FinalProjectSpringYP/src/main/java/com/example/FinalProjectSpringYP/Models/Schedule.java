package com.example.FinalProjectSpringYP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Schedule {
    private Long idSchedule;
    @NotNull
    @NotBlank(message = "Schedule is required")
    private String schedule;

    public Schedule() {
    }

    public Schedule(Long idTimetable, String schedule) {
        this.idSchedule = idTimetable;
        this.schedule = schedule;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
