package com.proxidev.catalogservice.domain;

import com.proxidev.catalogservice.config.DataConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
		replace = AutoConfigureTestDatabase.Replace.NONE
)
@ActiveProfiles("integration")
class BookRepositoryJdbcTest {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private JdbcAggregateTemplate aggregateTemplate;

	@Test
	void findBookByIsbnWhenExisting() {
		var bookIsbn = "1234561237";
		var book = Book.of(bookIsbn, "Title", "Author", 9.90);
		aggregateTemplate.insert(book);
		Optional<Book> actualBook = bookRepository.findByIsbn(book.isbn());

		assertTrue(actualBook.isPresent());
		assertEquals(actualBook.get().isbn(), book.isbn());
	}
}