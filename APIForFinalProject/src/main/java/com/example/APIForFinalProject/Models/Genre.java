package com.example.APIForFinalProject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idBookGenre;
    @Size(min = 3, message = "The nameGenre cannot be less than 3 characters")
    @NotBlank(message = "NameGenre is required")
    private String nameGenre;


    public Genre() {
    }

    public Genre(Long idBookGenre, String nameGenre) {
        this.idBookGenre = idBookGenre;
        this.nameGenre = nameGenre;
    }

    public Long getIdBookGenre() {
        return idBookGenre;
    }

    public void setIdBookGenre(Long idBookGenre) {
        this.idBookGenre = idBookGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

}
