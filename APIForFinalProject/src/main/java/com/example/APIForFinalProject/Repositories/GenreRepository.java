package com.example.APIForFinalProject.Repositories;

import com.example.APIForFinalProject.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
