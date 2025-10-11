package com.proxidev.catalogservice.web;


import com.proxidev.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JsonTest
public class BookJsonTest {

	@Autowired
	private JacksonTester<Book> json;

	@Test
	void testSerializer() throws Exception {

	}


	@Test
	void testDeserialize() throws Exception {
		var content = """
				{
					"isbn": "1234567890",
					"title": "Title",
					"author": "Author",
					"price": 9.90
				}
				""";
		assertThat(json.parse(content).getObject())
				.usingRecursiveComparison()
				.isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
	}

}
