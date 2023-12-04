package com.example.APIForFinalProject.Repositories;

import com.example.APIForFinalProject.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
