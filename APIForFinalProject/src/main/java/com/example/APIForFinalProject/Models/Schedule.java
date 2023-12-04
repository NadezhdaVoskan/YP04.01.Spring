package com.example.APIForFinalProject.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
