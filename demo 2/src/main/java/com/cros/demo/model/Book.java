package com.cros.demo.model;

import java.util.Date;

public class Book {
    
    private Long bookId;
    private String bookName;
    private String bookauthorName;
    private Long bookTotalPages;
    private Double bookPrice;
    private String emailId;
    public Book(Long bookId, String bookName, String bookauthorName, Long bookTotalPages, Double bookPrice, String emailId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookauthorName = bookauthorName;
        this.bookTotalPages = bookTotalPages;
        this.bookPrice = bookPrice;
        this.emailId = emailId;
    }
    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", bookTotalPages="
                + bookTotalPages + ", bookauthorName=" + bookauthorName + "]";
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookauthorName() {
        return bookauthorName;
    }
    public void setBookauthorName(String bookauthorName) {
        this.bookauthorName = bookauthorName;
    }
    public Long getBookTotalPages() {
        return bookTotalPages;
    }
    public void setBookTotalPages(Long bookTotalPages) {
        this.bookTotalPages = bookTotalPages;
    }
    public Double getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    
    
}
