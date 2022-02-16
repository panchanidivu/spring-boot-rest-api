package com.ebook.book.service;

import java.util.ArrayList;
import java.util.Date;
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

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    

    public List<BookResponseDTO>getAllBooks() {
        
        
            List<Book> books = bookRepository.findAll(Sort.by(Direction.DESC, "bookcreatedDate"));
            List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(books, new TypeToken<List<BookResponseDTO>>() {}.getType());
            if(bookResponseDTOs.isEmpty()) {
                throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
            }
            return bookResponseDTOs;

        
    }


    public List<BookResponseDTO> addBook(BookDTO bookDTO) {
        List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
        if(bookDTO !=null ){
            Book book = ObjectMapperUtils.map(bookDTO, Book.class);
            book.setBookcreatedDate(new Date());
            book.setUpdatedDate(new Date());
            bookRepository.save(book);
            bookResponseDTOs.add(ObjectMapperUtils.map(book, BookResponseDTO.class));
            return bookResponseDTOs;

        } throw new CustomException("Book not created", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND); 
    }

    public List<BookResponseDTO> getBookById(String bookId) {
        
            List<Book> book =bookRepository.findByBookId(Long.valueOf(bookId));
            List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(book, new TypeToken<List<BookResponseDTO>>() {}.getType());
            if(bookResponseDTOs.isEmpty()) {
                throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
            }
            return bookResponseDTOs;
            
            

        
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

    public List<BookResponseDTO> getBookByBookPriceRange(Double bookprice) {
        List<Book> book =bookRepository.findByBookPriceGreaterThanEqual(Double.valueOf(bookprice));
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

    public List<BookResponseDTO> getAllBooksWithPagination(Integer offset, Integer pageSize) {
        List<Book> books = bookRepository.findAll(PageRequest.of(offset, pageSize)).getContent();
        List<BookResponseDTO> bookResponseDTOs = new ModelMapper().map(books, new TypeToken<List<BookResponseDTO>>() {}.getType());
        if(bookResponseDTOs.isEmpty()) {
            throw new CustomException("No books found", HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
        }
        return bookResponseDTOs;
        
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
        Book book2 = bookRepository.save(book1);

        bookResponseDTO.setBookName(book2.getBookName());
        bookResponseDTO.setBookauthorName(book2.getBookauthorName());
        bookResponseDTO.setBookTotalPages(book2.getBookTotalPages());
        bookResponseDTO.setBookPrice(book2.getBookPrice());
        bookResponseDTO.setBookId(book2.getBookId());

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
        book.stream().filter(distinctByKey(Book::getBookauthorName)).forEach(book1 -> {
            AuthorNameDTO authorNameDTO = new AuthorNameDTO();
            authorNameDTO.setBookauthorName(book1.getBookauthorName());
            authorNameDTO.setBookCount(bookRepository.findByBookauthorNameContaining(book1.getBookauthorName()).size());
            authorNameDTOs.add(authorNameDTO);
        });
        return authorNameDTOs;
    }


    private Predicate<? super Book> distinctByKey(Function<? super Book, ?> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    


    


    
}
