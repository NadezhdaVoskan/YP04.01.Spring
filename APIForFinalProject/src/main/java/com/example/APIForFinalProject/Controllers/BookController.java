package com.example.APIForFinalProject.Controllers;

import com.example.APIForFinalProject.Models.Book;
import com.example.APIForFinalProject.Repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getBook() {
        List<Book> books = bookRepository.findAll();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/book/{idBook}")
    public ResponseEntity<Book> oneBook(@PathVariable Long idBook) {
        Optional<Book> optionalBook = bookRepository.findById(idBook);

        if (optionalBook.isPresent()) {
            return new ResponseEntity<>(optionalBook.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book bookRequest) {
        Book book = bookRepository.save(bookRequest);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/book/{idBook}")
    public ResponseEntity<Book> updateBook(@PathVariable Long idBook,
                                                 @Valid @RequestBody Book bookRequest) {
        Optional<Book> bookOptional = bookRepository.findById(idBook);

        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = bookOptional.get();

        book.setIdBook(bookRequest.getIdBook());
        book.setName(bookRequest.getName());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
        book.setBookGenre(bookRequest.getBookGenre());
        book.setBookTypeLiterature(bookRequest.getBookTypeLiterature());

        Book bookUpdate = bookRepository.save(book);

        return new ResponseEntity<>(bookUpdate, HttpStatus.OK);
    }


    @DeleteMapping("/book/{idBook}")
    public ResponseEntity<?> deleteBook(@PathVariable Long idBook) {
        Optional<Book> bookOptional = bookRepository.findById(idBook);

        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Book book = bookOptional.get();

        bookRepository.delete(book);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/book/{address}/{name}")
    public ResponseEntity<List<Book>> searchBook(@PathVariable String address,@PathVariable String name) {
        List<Book> books = bookRepository.findBookByNameAndAddress(address,name);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
