package com.ebook.book.service;

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

import com.ebook.book.dto.AuthorNameDTO;
import com.ebook.book.dto.BookDTO;
import com.ebook.book.dto.BookResponseDTO;
import com.ebook.book.model.Book;
import com.ebook.book.repository.BookRepository;
import com.ebook.book.response.CustomException;
import com.ebook.book.response.ObjectMapperUtils;
import com.ebook.book.response.CustomResponseEntity.CustomResponseEntityBuilder;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
            sendSimpleEmail(toEmail,body,subject);
            return "Book added successfully";
            

        } throw new CustomException("Book not created", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND); 
    }

    


    private void sendSimpleEmail(String toEmail, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("panchanidivyesh@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");

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
        sendSimpleEmail(toEmail,body,subject);

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



}
