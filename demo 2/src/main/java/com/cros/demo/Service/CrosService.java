package com.cros.demo.Service;

import java.io.IOException;

import com.cros.demo.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CrosService {

    @Autowired
    Environment environment;

    


    public ResponseEntity<String> getAllBook() {
        // String url = "http://localhost:8080/api/ebook/all";
        String url = environment.getProperty("url");
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        // headers.setBasicAuth(username, password);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        // ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        // return response;
    }

    public ResponseEntity<String> getByBookId(String bookId) {
        // String url = "http://localhost:8080/api/ebook/getByBookId";
        String url = environment.getProperty("url1");
        String urlTemplate = url + "?bookId=" + bookId;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> addBook(Book book) {
        // String url = "http://localhost:8080/api/ebook/create";
        String url = environment.getProperty("url2");
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(book, headers), String.class);
        return response;
        
    }

    public ResponseEntity<String> updateBook(Book book) {
        // String url = "http://localhost:8080/api/ebook/updateBook";
        String url = environment.getProperty("url3");
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username,password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(book, headers), String.class);
        return response;
    }

    public ResponseEntity<String> deleteBook(String bookId) {
        // String url = "http://localhost:8080/api/ebook/deleteByBookId";
        String url = environment.getProperty("url4");
        String urlTemplate = url + "?bookId=" + bookId;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getAllAuthorNameWithCount() {
        // String url = "http://localhost:8080/api/ebook/all/AuthorNameWithCount";
        String url = environment.getProperty("url5");
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        // headers.setBasicAuth(username, password);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> deleteByAuthorName(String bookauthorName) {
        // String url = "http://localhost:8080/api/ebook/deletebyAuthorName";
        String url = environment.getProperty("url6");
        String urlTemplate = url + "?bookauthorName=" + bookauthorName;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.DELETE, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getByAuthorName(String bookauthorName) {
        // String url = "http://localhost:8080/api/ebook/getbyAuthorName";
        String url = environment.getProperty("url7");
        String urlTemplate = url + "?bookauthorName=" + bookauthorName;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getByBookName(String bookName) {
        // String url = "http://localhost:8080/api/ebook/getbybookname";
        String url = environment.getProperty("url8");
        String urlTemplate = url + "?bookName=" + bookName;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getByBookPrice(Double bookprice) {
        // String url = "http://localhost:8080/api/ebook/getbybookPrice";
        String url = environment.getProperty("url9");
        String urlTemplate = url + "?bookprice=" + bookprice;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getByAuthorNameAndBookName(String bookauthorName, String bookname) {
        // String url = "http://localhost:8080/api/ebook/getbyAuthorNameAndBookName";
        String url = environment.getProperty("url10");
        String urlTemplate = url + "?bookauthorName=" + bookauthorName + "&bookname=" + bookname;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        // headers.setBasicAuth(username, password);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getByBookPriceRanges(Double bookprice, Double bookprice1) {
        // String url = "http://localhost:8080/api/ebook/getByBookPriceRanges";
        String url = environment.getProperty("url11");
        String urlTemplate = url + "?bookprice=" + bookprice + "&bookprice1=" + bookprice1;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> getAllBooksWithPagination(Integer offset, Integer pageSize) {
        // String url = "http://localhost:8080/api/ebook/getAllBooksWithPagination";
        String url = environment.getProperty("url12");
        String urlTemplate = url + "?offset=" + offset + "&pageSize=" + pageSize;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }

    public ResponseEntity<String> searchByBookName(String bookname) {
        // String url = "http://localhost:8080/api/ebook/searchByBookName";
        String url = environment.getProperty("url13");
        String urlTemplate = url + "?bookname=" + bookname;
        HttpHeaders headers = new HttpHeaders();
        // headers.setBasicAuth(username, password);
        headers.setBasicAuth(environment.getProperty("username"), environment.getProperty("password"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return response;
    }
    

    
}
