package com.ebook.book.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ebook.book.dto.BookDTO;
import com.ebook.book.model.Book;
import com.ebook.book.response.CustomException;
import com.ebook.book.response.CustomResponseEntity;
import com.ebook.book.response.CustomResponseStatus;
import com.ebook.book.service.BookService;
import com.ebook.book.validation.LevelOneValidation;
import com.ebook.book.validation.LevelTwoValidation;
import com.ebook.book.validation.MainLevelValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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
@Validated(MainLevelValidation.class)
public class BookController {

    @Autowired
    BookService bookService;

    
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getAllBooks() {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getAllBooks()).build();
    }
    @GetMapping(value = "/all/AuthorNameWithCount", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getAllAuthorName() {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getAllAuthorName()).build();
    }
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity addBook(@Validated(MainLevelValidation.class) @ApiParam("bookDTO") @RequestBody  BookDTO bookDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.addBook(bookDTO)).build();
    }
    @GetMapping(value = "/getByBookId", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getBookById(@NotBlank(message="please enter the only BookId", groups = LevelOneValidation.class)@Pattern(regexp = "^[0-9]*$", message = "Only numeric characters are allowed") @ApiParam("bookId") @RequestParam String bookId) throws ParseException {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookById(bookId)).build();
    }

    @DeleteMapping("/deleteByBookId")
    public CustomResponseEntity deleteBook(@NotBlank(message="please enter the only BookId", groups = LevelOneValidation.class)@Pattern(regexp = "^[0-9]*$", message = "Only numeric characters are allowed" ,groups = LevelTwoValidation.class) @ApiParam("bookId") @RequestParam String bookId) throws ParseException {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.deleteBook(bookId)).build();
    }
    
    @DeleteMapping(value="/deletebyAuthorName", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity deleteBookByAuthorName(@NotBlank(message="please enter valid AuthoreName", groups = LevelOneValidation.class) @ApiParam("bookauthorName") @RequestParam String bookauthorName)throws ParseException {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.deleteBookByAuthorName(bookauthorName)).build();
    }
    @GetMapping(value = "/getbyAuthorName", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getBookByAuthorName(@NotBlank(message="please enter valid AuthoreName", groups = LevelOneValidation.class) @ApiParam("bookauthorName") @RequestParam String bookauthorName)throws ParseException {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByAuthorName(bookauthorName)).build();
    }

    @GetMapping(value="/getbybookname", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getBookByBookName(@NotBlank(message="please enter valid BookName", groups = LevelOneValidation.class)@ApiParam("bookName") @RequestParam String bookName) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookName(bookName)).build();
    }
    @GetMapping(value="/getbybookPrice", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getBookByBookPrice(@NotNull(message="please enter valid bookPrice", groups = LevelOneValidation.class)@ApiParam("bookprice") @RequestParam Double bookprice) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookPrice(bookprice)).build();
    }
    

    @GetMapping("/getbyAuthorNameAndBookName")
    public CustomResponseEntity getBookByAuthorNameAndBookName(@ApiParam("bookauthorName") @RequestParam String bookauthorName,@ApiParam("bookname") @RequestParam String bookname) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByAuthorNameAndBookName(bookauthorName,bookname)).build();
    }
    @GetMapping("/getAllBooksWithPagination")
    public CustomResponseEntity getAllBooksWithPagination( @ApiParam("offset") @RequestParam Integer offset,@ApiParam("pageSize") @RequestParam Integer pageSize) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getAllBooksWithPagination(offset,pageSize)).build();
    }
    @PutMapping("/updateBook")
    public CustomResponseEntity updateBook(@Validated(MainLevelValidation.class) @ApiParam("bookDTO") @RequestBody  BookDTO bookDTO) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.updateBook(bookDTO)).build();
    }

    @GetMapping(value="/getByBookPriceRanges", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity getBookByBookPriceRanges(@ApiParam("minPrice") @RequestParam Double bookprice,@ApiParam("maxPrice") @RequestParam Double bookprice1) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.getBookByBookPriceRanges(bookprice,bookprice1)).build();
    }

    @GetMapping(value="/CountByBookAuthorName", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity countByBookAuthorName(@NotBlank(message="please enter valid AuthoreName", groups = LevelOneValidation.class)@ApiParam("bookauthorName") @RequestParam String bookauthorName) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.countByBookAuthorName(bookauthorName)).build();
    }

    //Search By Book Name
    @GetMapping(value = "/searchByBookName", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomResponseEntity searchByBookName(@ApiParam("bookname") @RequestParam String bookname) {
        return CustomResponseEntity.builder().code(HttpStatus.OK.value())
        .status(CustomResponseStatus.SUCCESS.getStatus()).message(CustomResponseStatus.SUCCESS.getMessage())
        .data(bookService.searchByBookName(bookname)).build();
    }
    

   
   

}

