package com.ebook.book.controller;

import java.util.List;

import javax.validation.Valid;

import com.ebook.book.dto.BookDTO;
import com.ebook.book.model.Book;
import com.ebook.book.response.CustomException;
import com.ebook.book.response.CustomResponseEntity;
import com.ebook.book.response.CustomResponseStatus;
import com.ebook.book.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ebook")
@Api(tags = "api")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    BookService bookService;

    
    @GetMapping("/all")
    public CustomResponseEntity getAllBooks() {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getAllBooks()).build();
    }
    @PostMapping("/add")
    public CustomResponseEntity addBook(@Valid @ApiParam("bookDTO") @RequestBody  List<BookDTO> bookDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.addBook(bookDTO)).build();
    }
    @GetMapping("/getByBookId")
    public CustomResponseEntity getBookById(@ApiParam("bookId") @RequestParam String bookId) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookById(bookId)).build();
    }

    @DeleteMapping("/deleteByBookId")
    public CustomResponseEntity deleteBook(@ApiParam("bookId") @RequestParam String bookId) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.deleteBook(bookId)).build();
    }
    
    @DeleteMapping("/deletebyAuthorName")
    public CustomResponseEntity deleteBookByAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.deleteBookByAuthorName(bookauthorName)).build();
    }
    @GetMapping("/getbyAuthorName")
    public CustomResponseEntity getBookByAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByAuthorName(bookauthorName)).build();
    }

    @GetMapping("/getbybookname")
    public CustomResponseEntity getBookByBookName(@ApiParam("bookname") @RequestParam String bookname) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookName(bookname)).build();
    }
    @GetMapping("/getbybookPrice")
    public CustomResponseEntity getBookByBookPrice(@ApiParam("bookprice") @RequestParam Double bookprice) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookPrice(bookprice)).build();
    }
    @GetMapping("/getbybookPriceRange")
    public CustomResponseEntity getBookByBookPriceRange(@ApiParam("bookprice") @RequestParam Double bookprice) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookPriceRange(bookprice)).build();
    }

    @GetMapping("/getbyAuthorNameAndBookName")
    public CustomResponseEntity getBookByAuthorNameAndBookName(@ApiParam("bookauthorName") @RequestParam String bookauthorName,@ApiParam("bookname") @RequestParam String bookname) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByAuthorNameAndBookName(bookauthorName,bookname)).build();
    }
    @GetMapping("/getAllBooksWithPagination")
    public CustomResponseEntity getAllBooksWithPagination(@Valid @ApiParam("offset") @RequestParam Integer offset,@ApiParam("pageSize") @RequestParam Integer pageSize) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getAllBooksWithPagination(offset,pageSize)).build();
    }
    @PutMapping("/updateBook")
    public CustomResponseEntity updateBook(@ApiParam("bookDTO") @RequestBody  BookDTO bookDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.updateBook(bookDTO)).build();
    }

    @GetMapping("/getByBookPriceRanges")
    public CustomResponseEntity getBookByBookPriceRanges(@ApiParam("bookprice") @RequestParam Double bookprice,@ApiParam("bookprice1") @RequestParam Double bookprice1) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookPriceRanges(bookprice,bookprice1)).build();
    }

    @GetMapping("/CountByBookAuthorName")
    public CustomResponseEntity countByBookAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.countByBookAuthorName(bookauthorName)).build();
    }

    //Search By Book Name
    @GetMapping("/searchByBookName")
    public CustomResponseEntity searchByBookName(@ApiParam("bookname") @RequestParam String bookname) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.searchByBookName(bookname)).build();
    }
    

   
   

}

