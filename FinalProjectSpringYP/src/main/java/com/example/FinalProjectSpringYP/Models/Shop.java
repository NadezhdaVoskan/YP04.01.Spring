package com.example.FinalProjectSpringYP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Shop {

    private Long idShop;
    @Size(min = 5, message = "The address cannot be less than 5 characters")
    @NotBlank(message = "Address is required")
    private String address;

    private Book book;

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
