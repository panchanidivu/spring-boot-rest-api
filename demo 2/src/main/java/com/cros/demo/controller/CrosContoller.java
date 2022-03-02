package com.cros.demo.controller;

import com.cros.demo.Service.CrosService;
import com.cros.demo.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/cros")
public class CrosContoller {

    @Autowired
    private CrosService crosService;

    // @GetMapping("/all")
    // public String getHello() {
    //     String url = "http://localhost:8080/api/ebook/all";
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setBasicAuth("divyesh", "divu@2108");

    //     RestTemplate restTemplate = new RestTemplate();
    //     String response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    //     return response;
    // }
    @GetMapping("/all")
    public ResponseEntity<String> getAllBook(){
        return crosService.getAllBook();
    }
    @GetMapping("/getByBookId")
    public ResponseEntity<String> getByBookId(@ApiParam("bookId") @RequestParam String bookId){
        return crosService.getByBookId(bookId);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@ApiParam("book") @RequestBody Book book){
        return crosService.addBook(book);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@ApiParam("book") @RequestBody Book book){
        return crosService.updateBook(book);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@ApiParam("bookId") @RequestParam String bookId){
        return crosService.deleteBook(bookId);
    }
    @GetMapping(value = "/all/AuthorNameWithCount")
    public ResponseEntity<String> getAllAuthorNameWithCount(){
        return crosService.getAllAuthorNameWithCount();
    }

    @DeleteMapping(value="/deletebyAuthorName")
    public ResponseEntity<String> deleteByAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName){
        return crosService.deleteByAuthorName(bookauthorName);
    }
    @GetMapping(value = "/getbyAuthorName")
    public ResponseEntity<String> getByAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName){
        return crosService.getByAuthorName(bookauthorName);
    }
    @GetMapping(value="/getbybookname")
    public ResponseEntity<String> getByBookName(@ApiParam("bookName") @RequestParam String bookName){
        return crosService.getByBookName(bookName);
    }

    @GetMapping(value="/getbybookPrice")
    public ResponseEntity<String> getByBookPrice(@ApiParam("bookprice") @RequestParam Double bookprice){
        return crosService.getByBookPrice(bookprice);
    }
    @GetMapping(value = "/getbyAuthorNameAndBookName")
    public ResponseEntity<String> getByAuthorNameAndBookName(@RequestParam String bookauthorName,@ApiParam("bookname") @RequestParam String bookname){
        return crosService.getByAuthorNameAndBookName(bookauthorName,bookname);
    }
    @GetMapping(value="/getByBookPriceRanges")
    public ResponseEntity<String> getByBookPriceRanges(@ApiParam("minPrice") @RequestParam Double bookprice,@ApiParam("maxPrice") @RequestParam Double bookprice1){
        return crosService.getByBookPriceRanges(bookprice,bookprice1);
    }
    @GetMapping(value = "/getAllBooksWithPagination")
    public ResponseEntity<String> getAllBooksWithPagination( @ApiParam("offset") @RequestParam Integer offset,@ApiParam("pageSize") @RequestParam Integer pageSize){
        return crosService.getAllBooksWithPagination(offset,pageSize);
    }
    @GetMapping(value = "/searchByBookName")
    public ResponseEntity<String> searchByBookName(@ApiParam("bookname") @RequestParam String bookname){
        return crosService.searchByBookName(bookname);
    }

        

}
