package com.proxidev.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookValidationTest {
	private static Validator validator;

	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book = new Book(
				"1234567890",
				"Title",
				"Author",9.99
		);

		Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

		assertThat(constraintViolations.isEmpty());
	}

	@Test
	void whenISBNDefinedThenValidationFails() {
		var book = new Book(
				"a1234567890",
				"Title",
				"Author",9.99
		);
		Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);

		assertThat(constraintViolations.size()).isEqualTo(1);
		assertThat(constraintViolations.iterator().next().getMessage())
		.isEqualTo("The ISBN format must be valid.");
	}

	@Test
	void author() {
	}

	@Test
	void price() {
	}
}