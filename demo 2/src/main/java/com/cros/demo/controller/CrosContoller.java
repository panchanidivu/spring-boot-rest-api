package com.cros.demo.controller;

import java.time.Duration;

import javax.annotation.PostConstruct;

import com.cros.demo.Service.CrosService;
import com.cros.demo.model.Book;
import com.cros.demo.response.RestControllerExceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/cros")
public class CrosContoller {

    @Autowired
    private CrosService crosService;

    private Bucket bucket;
    private Bucket bucket1;
    private Bucket bucket2;
    private Bucket bucket3;
    private Bucket bucket4;
    private Bucket bucket5;
    private Bucket bucket6;
    private Bucket bucket7;
    private Bucket bucket8;
    private Bucket bucket9;
    private Bucket bucket10;
    private Bucket bucket11;
    private Bucket bucket12;
    private Bucket bucket13;
    @PostConstruct
    public void setupBucket() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket1() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket1 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket2() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket2 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket3() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket3 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket4() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket4 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket5() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket5 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket6() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket6 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket7() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket7 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket8() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket8 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket9() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket9 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket10() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket10 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket11() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket11 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket12() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket12 = Bucket4j.builder().addLimit(limit).build();
    }
    @PostConstruct
    public void setupBucket13() {
        Bandwidth limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofMinutes(1)));
        this.bucket13 = Bucket4j.builder().addLimit(limit).build();
    }

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
        if(bucket.tryConsume(1)){
            return crosService.getAllBook();
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
        
    }
    @GetMapping("/getByBookId")
    public ResponseEntity<String> getByBookId(@ApiParam("bookId") @RequestParam String bookId){
        if(bucket1.tryConsume(1)){
        return crosService.getByBookId(bookId);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@ApiParam("book") @RequestBody Book book){
        if(bucket2.tryConsume(1)){
        return crosService.addBook(book);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@ApiParam("book") @RequestBody Book book){
        if(bucket3.tryConsume(1)){
        return crosService.updateBook(book);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@ApiParam("bookId") @RequestParam String bookId){
        if(bucket4.tryConsume(1)){
        return crosService.deleteBook(bookId);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value = "/all/AuthorNameWithCount")
    public ResponseEntity<String> getAllAuthorNameWithCount(){
        if(bucket5.tryConsume(1)){
        return crosService.getAllAuthorNameWithCount();
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }

    @DeleteMapping(value="/deletebyAuthorName")
    public ResponseEntity<String> deleteByAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName){
        if(bucket6.tryConsume(1)){
        return crosService.deleteByAuthorName(bookauthorName);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value = "/getbyAuthorName")
    public ResponseEntity<String> getByAuthorName(@ApiParam("bookauthorName") @RequestParam String bookauthorName){
        if(bucket7.tryConsume(1)){
        return crosService.getByAuthorName(bookauthorName);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value="/getbybookname")
    public ResponseEntity<String> getByBookName(@ApiParam("bookName") @RequestParam String bookName){
        if(bucket8.tryConsume(1)){
        return crosService.getByBookName(bookName);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }

    @GetMapping(value="/getbybookPrice")
    public ResponseEntity<String> getByBookPrice(@ApiParam("bookprice") @RequestParam Double bookprice){
        if(bucket9.tryConsume(1)){
        return crosService.getByBookPrice(bookprice);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value = "/getbyAuthorNameAndBookName")
    public ResponseEntity<String> getByAuthorNameAndBookName(@RequestParam String bookauthorName,@ApiParam("bookname") @RequestParam String bookname){
        if(bucket10.tryConsume(1)){
        return crosService.getByAuthorNameAndBookName(bookauthorName,bookname);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value="/getByBookPriceRanges")
    public ResponseEntity<String> getByBookPriceRanges(@ApiParam("minPrice") @RequestParam Double bookprice,@ApiParam("maxPrice") @RequestParam Double bookprice1){
        if(bucket11.tryConsume(1)){
        return crosService.getByBookPriceRanges(bookprice,bookprice1);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value = "/getAllBooksWithPagination")
    public ResponseEntity<String> getAllBooksWithPagination( @ApiParam("offset") @RequestParam Integer offset,@ApiParam("pageSize") @RequestParam Integer pageSize){
        if(bucket12.tryConsume(1)){
        return crosService.getAllBooksWithPagination(offset,pageSize);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }
    @GetMapping(value = "/searchByBookName")
    public ResponseEntity<String> searchByBookName(@ApiParam("bookname") @RequestParam String bookname){
        if(bucket13.tryConsume(1)){
        return crosService.searchByBookName(bookname);
        }
        return RestControllerExceptionHandler.handleException(new Exception("Too many requests"));
    }

        

}
