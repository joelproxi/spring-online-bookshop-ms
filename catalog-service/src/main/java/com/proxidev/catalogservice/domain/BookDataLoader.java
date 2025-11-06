package com.proxidev.catalogservice.domain;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {

	private final BookRepository bookRepository;

	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		bookRepository.deleteAll();

		var book1 = Book.of( "Title 1", "Author 1", "Polarsophia", 9.90);
		var book2 = Book.of("Title 2", "Author 2", "Polarsophia", 9.90);

		bookRepository.saveAll(List.of(book1, book2));
	}
}
