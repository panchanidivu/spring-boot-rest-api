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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Book_id")
    private Long bookId;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only alphanumeric characters are allowed")
    @Column(name = "Book_name",nullable = false)
    @NotBlank(message = "Book name is mandatory")
    private String bookname;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Only alphanumeric characters are allowed")
    @Column(name = "Book_author_name",nullable = false)
    @NotBlank(message = "Book author name is required")
    private String bookauthorName;

    @Column(name = "Book_total_pages",nullable = false)
    private Long bookTotalPages;

    @Column(name = "Book_price",nullable = false)
    // @NotBlank(message = "Book price is required")
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
