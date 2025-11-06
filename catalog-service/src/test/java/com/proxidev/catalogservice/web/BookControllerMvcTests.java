package com.proxidev.catalogservice.web;

import com.proxidev.catalogservice.domain.BookService;
import com.proxidev.catalogservice.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerMvcTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private BookService bookService;


	void testWhenGetBookNotExistingThenShouldReturn404() throws Exception {
		String isbn = "73737313940";
		given(bookService.getBookByIsbn(isbn))
				.willThrow(BookNotFoundException.class);

		mockMvc.perform(get("/books/" + isbn))
				.andExpect(status().isNotFound());
	}

}