package org.cm.querydsl.service;

import org.cm.querydsl.domain.Author;
import org.cm.querydsl.domain.Book;

import java.util.List;

/**
 * QueryDsl
 * Created By Khan, C M Abdullah on 21/6/24 : Time: 10:08
 * Ref:
 */
public interface AuthorService {
	List<Book> getAllBooksById(int id);
}
