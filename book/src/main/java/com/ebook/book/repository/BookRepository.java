package com.ebook.book.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.ebook.book.model.Book;
import com.google.common.base.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>  {

    List<Book> findByBookId(Long bookId);
    @Transactional
    void deleteByBookId(Long bookId);
    @Transactional
    void deleteByBookauthorName(String bookauthorName);

    List<Book> findByBookauthorName(String bookauthorName);

    List<Book> findByBookName(String bookName);
   
    @Query(value = "SELECT * FROM book WHERE book_price = ?1", nativeQuery = true)
    List<Book> findByBookPriceGreaterThan(Double bookPrice);

    // @Query(value = "SELECT * FROM book WHERE  book_price BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Book> findByBookPriceBetween(Double bookPrice1, Double bookPrice2);
    
    

    @Query(value = "SELECT * FROM book WHERE book_price > ?1", nativeQuery = true)
    List<Book> findByBookPriceGreaterThanEqual(Double bookPrice);

    // List<Book> findByBookauthorNameAndBookname(String bookauthorName, String bookname);

    // @Query(value = "SELECT * FROM book WHERE book_author_name = ?1 and book_name = ?2", nativeQuery = true)
    List<Book> findByBookauthorNameAndBookName(String bookauthorName, String bookName);

    Object countByBookauthorName(String bookauthorName);

    public List<Book> findByBookNameContaining(String bookName);


    public List<Book> findByBookauthorNameContaining(String bookauthorName);


   
    
    
    
}
    

