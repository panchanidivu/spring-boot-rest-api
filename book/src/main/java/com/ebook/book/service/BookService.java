package com.ebook.book.service;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Renderer;

import com.ebook.book.dto.AuthorNameDTO;
import com.ebook.book.dto.BookDTO;
import com.ebook.book.dto.BookResponseDTO;
import com.ebook.book.model.Book;
import com.ebook.book.repository.BookRepository;
import com.ebook.book.response.CustomException;
import com.ebook.book.response.ObjectMapperUtils;
import com.ebook.book.response.CustomResponseEntity.CustomResponseEntityBuilder;
import com.google.common.net.HttpHeaders;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import ch.qos.logback.core.joran.event.BodyEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    @Autowired
    private JavaMailSender mailSender;

    
    

    public List<BookResponseDTO>getAllBooks() {
        
        
            List<Book> books = bookRepository.findAll(Sort.by(Direction.DESC, "bookcreatedDate"));
            List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(books, new TypeToken<List<BookResponseDTO>>() {}.getType());
            if(bookResponseDTOs.isEmpty()) {
                throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
            }
            return bookResponseDTOs;

        
    }


    public String addBook(BookDTO bookDTO) {
       
        if(bookDTO !=null ){
            Book book = ObjectMapperUtils.map(bookDTO, Book.class);
            book.setBookcreatedDate(new Date());
            book.setUpdatedDate(new Date());
            bookRepository.save(book);
            String body = "Respected Author your book " +" "+ bookDTO.getBookName() + " is added successfully";
            String subject = "Book added successfully";
            String toEmail = bookDTO.getEmailId();
            System.out.println("toEmail"+toEmail);
            try {
                sendSimpleEmail(toEmail,body,subject);
            } catch (MessagingException e) {
                
                e.printStackTrace();
            }
            return "Book added successfully";
            

        } throw new CustomException("Book not created", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND); 
    }

    


    private void sendSimpleEmails(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("panchanidivyesh@gmail.com");
        message.setTo(toEmail);
        String html = "<html><body>"; 
        message.setText(html);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");

    }
    private void sendSimpleEmail(String toEmail, String body, String subject) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setSubject("Book added successfully");
        String html = "<!doctype html>\n" +
        "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
        "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
        "<head>\n" +
        "    <meta charset=\"UTF-8\">\n" +
        "    <meta name=\"viewport\"\n" +
        "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
        "    <title>Email</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<div>Welcome <b>" +"<h1>"+ body + "</h1>"+ "</b></div>\n" +
        "\n" +
        "<div> Your username is <b>"   +
        "</body>\n" +
        "</html>\n";
        helper.setText(html, true);
        FileSystemResource file = new FileSystemResource(new File("/Users/divyeshpanchani/Desktop/github/spring-boot-/book/src/main/resources/divu.jpeg"));
        helper.addAttachment("/Users/divyeshpanchani/Desktop/github/spring-boot-/book/src/main/resources/divu.jpeg", file);
        helper.setTo(toEmail);
        helper.setFrom("panchanidivyesh@gmail.com");
        mailSender.send(mimeMessage);
        
    }

    public BookResponseDTO getBookById(String bookId) {
        
            Optional<Book> book = bookRepository.findById(Long.valueOf(bookId));
            BookResponseDTO bookResponseDTO = new ModelMapper().map(book.get(), BookResponseDTO.class);
            if(bookResponseDTO == null) {
                throw new CustomException("Book not found", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
            }
            return bookResponseDTO;
        
    }

    public boolean deleteBook(String BookId) {
        try {
            bookRepository.deleteByBookId(Long.valueOf(BookId));
            return true;
        } catch (Exception e) {
            throw new CustomException("Book not deleted", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
        }
    }

	public boolean deleteBookByAuthorName(String bookauthorName) {
		bookRepository.deleteByBookauthorName(bookauthorName);
        return true;
	}

    public List<BookResponseDTO> getBookByAuthorName(String bookauthorName) {
        List<Book> book =bookRepository.findByBookauthorName(bookauthorName);
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        

    }

    public List<BookResponseDTO> getBookByBookName(String bookName) {
        List<Book> book =bookRepository.findByBookName(bookName);
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        
    }

    public List<BookResponseDTO> getBookByBookPrice(Double bookprice) {
        List<Book> book =bookRepository.findByBookPriceGreaterThan(Double.valueOf(bookprice));
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        
    }

    

    public List<BookResponseDTO> getBookByAuthorNameAndBookName(String bookauthorName, String bookName) {
        List<Book> book =bookRepository.findByBookauthorNameAndBookName(bookauthorName, bookName);
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        
    }

    public Page<Book> getAllBooksWithPagination(Integer offset, Integer pageSize) {
        Page<Book> books = bookRepository.findAll(PageRequest.of(offset, pageSize));
        return books;
         
    }

    public BookResponseDTO updateBook(BookDTO bookDTO) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        Optional<Book> book = bookRepository.findById(bookDTO.getBookId());
        if(!book.isPresent()) {
            throw new CustomException("Book not found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        Book book1=book.get();
        book1.setBookName(bookDTO.getBookName());
        book1.setBookauthorName(bookDTO.getBookauthorName());
        book1.setBookTotalPages(bookDTO.getBookTotalPages());
        book1.setBookPrice(bookDTO.getBookPrice());
        book1.setUpdatedDate(new Date());
        book1.setBookcreatedDate(book.get().getBookcreatedDate());
        book1.setEmailId(bookDTO.getEmailId());
        Book book2 = bookRepository.save(book1);

        bookResponseDTO.setBookName(book2.getBookName());
        bookResponseDTO.setBookauthorName(book2.getBookauthorName());
        bookResponseDTO.setBookTotalPages(book2.getBookTotalPages());
        bookResponseDTO.setBookPrice(book2.getBookPrice());
        bookResponseDTO.setBookId(book2.getBookId());
        String body = "Respected Author your book " +" "+ bookDTO.getBookName() + " is update successfully";
        String subject = "Book update successfully";
        String toEmail = bookDTO.getEmailId();
        System.out.println("toEmail"+toEmail);
        try {
            sendSimpleEmail(toEmail,body,subject);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bookResponseDTO;
       


        
    }

    public List<BookResponseDTO> getBookByBookPriceRanges(Double bookprice, Double bookprice1) {
        List<Book> book =bookRepository.findByBookPriceBetween(Double.valueOf(bookprice), Double.valueOf(bookprice1));
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        
    }

    public Object countByBookAuthorName(String bookauthorName) {
        
        if(bookauthorName.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookRepository.countByBookauthorName(bookauthorName);

        
        
    }

    public List<BookResponseDTO> searchByBookName(String bookName) {
        List<Book> book =bookRepository.findByBookNameContaining(bookName);
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        
    }


    public List<AuthorNameDTO> getAllAuthorName() {
        List<AuthorNameDTO> authorNameDTOs = new ArrayList<>();
        List<Book> book =bookRepository.findAll();
        HashSet<String> authorNames = new HashSet<>();
        for(Book book1:book) {
            authorNames.add(book1.getBookauthorName());
        }
        for(String authorName:authorNames) {
            AuthorNameDTO authorNameDTO = new AuthorNameDTO();
            authorNameDTO.setBookauthorName(authorName);
            authorNameDTO.setBookCount(bookRepository.findByBookauthorNameContaining(authorName).size());
            authorNameDTOs.add(authorNameDTO);
        }
        return authorNameDTOs;
        // book.stream().filter(distinctByKey(Book::getBookauthorName)).forEach(book1 -> {
        //     AuthorNameDTO authorNameDTO = new AuthorNameDTO();
        //     authorNameDTO.setBookauthorName(book1.getBookauthorName());
        //     authorNameDTO.setBookCount(bookRepository.findByBookauthorNameContaining(book1.getBookauthorName()).size());
        //     authorNameDTOs.add(authorNameDTO);
        // });
        // return authorNameDTOs;
    }


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void writeTOCsv(Writer writer){
        try {
            writer.write("BookID,Book Name,Book Author Name,Book Total Pages,Book Price,Book Created Date,Book Updated Date,Email Id");
            writer.write("\n");
            List<Book> book =bookRepository.findAll();
            for(Book book1:book) {
                writer.write(book1.getBookId()+","+book1.getBookName()+","+book1.getBookauthorName()+","+book1.getBookTotalPages()+","+book1.getBookPrice()+","+book1.getBookcreatedDate()+","+book1.getUpdatedDate()+","+book1.getEmailId());
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Object downloadBookCsv(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/csv");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"books.csv\"");
        writeTOCsv(httpServletResponse.getWriter());
        return httpServletResponse;
    }
        



}
