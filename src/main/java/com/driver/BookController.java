package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {
    @Autowired
    BookService bookService;

    // One example controller, make the rest by yourself
    @PostMapping("/create-book")
    public ResponseEntity createBook(@RequestBody Book book){
        Book newbook = bookService.createBook(book);
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }

    @GetMapping("get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        int bookId;
        try {
            bookId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book retrievedBook = bookService.findBookById(bookId);
        if (retrievedBook == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(retrievedBook, HttpStatus.OK);
    }

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = bookService.findAllBooks();
        if(bookList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>( bookList, HttpStatus.OK);
    }

    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getAllBookByTheAuthor(@RequestParam String author){
        List<Book> bookList = bookService.findBooksByAuthor(author);
        if(bookList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>( bookList, HttpStatus.OK);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getAllBookByGenre(@RequestParam String genre){
        List<Book> bookList = bookService.findBooksByGenre(genre);
        if(bookList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>( bookList, HttpStatus.OK);
    }

    @DeleteMapping("delete-book-by-id/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable String id) {
        int bookId;
        try {
            bookId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-books")
    public ResponseEntity<Book> deleteAllBooks() {
        bookService.deleteAllBooks();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
