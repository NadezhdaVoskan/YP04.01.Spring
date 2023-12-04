package com.example.FinalProjectSpringYP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
public class Genre {

    private Long idBookGenre;
    @Size(min = 3, message = "The nameGenre cannot be less than 3 characters")
    @NotBlank(message = "NameGenre is required")
    private String nameGenre;

    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
