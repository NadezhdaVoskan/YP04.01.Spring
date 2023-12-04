package com.example.APIForFinalProject.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idShop;
    @Size(min = 5, message = "The address cannot be less than 5 characters")
    @NotBlank(message = "Address is required")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bookId", nullable=false)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="scheduleId", nullable=false)
    private Schedule schedule;

    public Shop() {
    }

    public Shop(Long idShop, String address, Book book, Schedule schedule) {
        this.idShop = idShop;
        this.address = address;
        this.book = book;
        this.schedule = schedule;
    }

    public Long getIdShop() {
        return idShop;
    }

    public void setIdShop(Long idShop) {
        this.idShop = idShop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
