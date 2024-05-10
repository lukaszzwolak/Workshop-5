package pl.coderslab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;

import java.util.SequencedCollection;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public SequencedCollection<pl.coderslab.model.Book> getList() {
        return bookService.getBooks();
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.get(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ));
    }

    /*
    request body for postman:
    {
    "isbn":"34321","title":"Thinking in Java",
    "publisher":"Helion","type":"programming", "author":"Bruce Eckel"
    }
    */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Book book) {
        bookService.update(book);
    }
}
