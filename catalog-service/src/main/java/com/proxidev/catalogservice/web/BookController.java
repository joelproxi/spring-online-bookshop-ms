package com.proxidev.catalogservice.web;

import com.proxidev.catalogservice.domain.Book;
import com.proxidev.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public Iterable<Book> getBooks() {
		return bookService.getBooks();
	}

	@GetMapping("{isbn}")
	public Book getBook(@PathVariable String isbn) {
		return bookService.getBookByIsbn(isbn);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book addBook(@Valid @RequestBody Book book) {
		return bookService.addBook(book);
	}

	@DeleteMapping("{isbn}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable String isbn) {
		bookService.deleteByIsbn(isbn);
	}

	@PutMapping("{isbn}")
	public Book updateBook(@PathVariable String isbn, @Valid  @RequestBody Book book) {
		return bookService.updateBook(isbn, book);
	}
}
