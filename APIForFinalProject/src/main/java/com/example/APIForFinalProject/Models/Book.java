package com.example.APIForFinalProject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idBook;
    @Size(min = 3, message = "The name cannot be less than 2 characters")
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 2, message = "The author cannot be less than 2 characters")
    @NotBlank(message = "Author is required")
    private String author;
    @Size(min = 10, message = "The description cannot be less than 10 characters and more than 255 characters")
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="typeLiteratureId", nullable=false)
    private TypeLiterature bookTypeLiterature;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="genreId", nullable=false)
    private Genre bookGenre;


    public Book() {
    }

    public Book(Long idBook, String name, String author, String description, Double price, Genre bookGenre, TypeLiterature bookTypeLiterature) {
        this.idBook = idBook;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
        this.bookTypeLiterature = bookTypeLiterature;
        this.bookGenre = bookGenre;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TypeLiterature getBookTypeLiterature() {
        return bookTypeLiterature;
    }

    public void setBookTypeLiterature(TypeLiterature bookTypeLiterature) {
        this.bookTypeLiterature = bookTypeLiterature;
    }

    public Genre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(Genre bookGenre) {
        this.bookGenre = bookGenre;
    }



}
