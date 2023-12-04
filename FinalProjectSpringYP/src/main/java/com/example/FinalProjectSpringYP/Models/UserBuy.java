package com.example.FinalProjectSpringYP.Models;

public class UserBuy {

    private Long idUserBuy;
    private User user;

    private Book book;

    public UserBuy() {
    }

    public UserBuy(Long idUserBuy, User user, Book book) {
        this.idUserBuy = idUserBuy;
        this.user = user;
        this.book = book;
    }

    public Long getIdUserBuy() {
        return idUserBuy;
    }

    public void setIdUserBuy(Long idUserBuy) {
        this.idUserBuy = idUserBuy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
