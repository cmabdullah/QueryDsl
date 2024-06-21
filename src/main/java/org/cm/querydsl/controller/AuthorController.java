package org.cm.querydsl.controller;

import org.cm.querydsl.domain.Author;
import org.cm.querydsl.domain.Book;
import org.cm.querydsl.repository.AuthorRepository;
import org.cm.querydsl.repository.BookRepository;
import org.cm.querydsl.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * QueryDsl
 * Created By Khan, C M Abdullah on 20/6/24 : Time: 17:05
 * Ref:
 */
@RestController
public class AuthorController {

	public final AuthorRepository authorRepository;
	public final BookRepository bookRepository;
	public final AuthorService authorService;

	public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository, AuthorService authorService) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.authorService = authorService;
	}

	@GetMapping("/author")
	public Author find() {
		return authorRepository.findById(1).orElse(null);
	}


	@GetMapping("/booksbyid")
	public List<Book> findById(@RequestParam Integer id) {
		return authorService.getAllBooksById(id);
	}
}
