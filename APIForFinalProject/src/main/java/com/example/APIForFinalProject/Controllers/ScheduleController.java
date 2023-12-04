package com.example.APIForFinalProject.Controllers;

import com.example.APIForFinalProject.Models.Schedule;
import com.example.APIForFinalProject.Repositories.ScheduleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @GetMapping("/schedule")
    public ResponseEntity<List<Schedule>> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/schedule/{idSchedule}")
    public ResponseEntity<Schedule> oneSchedule(@PathVariable Long idSchedule) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(idSchedule);

        if (optionalSchedule.isPresent()) {
            return new ResponseEntity<>(optionalSchedule.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/schedule")
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody Schedule scheduleRequest) {
        Schedule schedule = scheduleRepository.save(scheduleRequest);

        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @PutMapping("/schedule/{idSchedule}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long idSchedule,
                                                   @Valid @RequestBody Schedule scheduleRequest) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(idSchedule);

        if (optionalSchedule.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Schedule schedule = optionalSchedule.get();

        schedule.setIdSchedule(scheduleRequest.getIdSchedule());
        schedule.setSchedule(scheduleRequest.getSchedule());

        Schedule scheduleUpdate = scheduleRepository.save(schedule);

        return new ResponseEntity<>(scheduleUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/schedule/{idSchedule}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long idSchedule) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(idSchedule);

        if (optionalSchedule.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Schedule schedule = optionalSchedule.get();

        scheduleRepository.delete(schedule);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
