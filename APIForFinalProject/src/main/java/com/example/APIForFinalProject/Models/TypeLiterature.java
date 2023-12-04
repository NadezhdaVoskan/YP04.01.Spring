package com.example.APIForFinalProject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
@Entity
public class TypeLiterature {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idBookTypeLiterature;
    @Size(min = 3, message = "The nameTypeLiteratur cannot be less than 3 characters")
    @NotBlank(message = "NameTypeLiterature is required")
    private String nameTypeLiterature;

    public TypeLiterature() {
    }
    public TypeLiterature(Long idBookGenre, String nameTypeLiterature) {
        this.idBookTypeLiterature = idBookGenre;
        this.nameTypeLiterature = nameTypeLiterature;
    }
    public Long getIdBookTypeLiterature() {
        return idBookTypeLiterature;
    }
    public void setIdBookTypeLiterature(Long idBookTypeLiterature) {
        this.idBookTypeLiterature = idBookTypeLiterature;
    }

    public String getNameTypeLiterature() {
        return nameTypeLiterature;
    }
    public void setNameTypeLiterature(String nameTypeLiterature) {
        this.nameTypeLiterature = nameTypeLiterature;
    }

}
