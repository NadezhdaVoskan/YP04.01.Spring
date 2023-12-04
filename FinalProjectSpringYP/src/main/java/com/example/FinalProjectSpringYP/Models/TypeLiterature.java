package com.example.FinalProjectSpringYP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class TypeLiterature {
    private Long idBookTypeLiterature;
    @Size(min = 3, message = "The nameTypeLiteratur cannot be less than 3 characters")
    @NotBlank(message = "NameTypeLiterature is required")
    private String nameTypeLiterature;

    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
