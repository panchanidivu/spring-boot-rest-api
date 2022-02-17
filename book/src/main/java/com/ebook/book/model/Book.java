package com.ebook.book.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "book")
public class Book {
    //Entity class for Book table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;


    @Column(name = "book_name",nullable = false)
    private String bookName;

   
    @Column(name = "book_author_name",nullable = false)
    private String bookauthorName;

    @Column(name = "book_total_pages",nullable = false)
    private Long bookTotalPages;

    @Column(name = "book_price",nullable = false)
    private Double bookPrice;

    @CreationTimestamp
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookcreatedDate;


    @UpdateTimestamp
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
}
