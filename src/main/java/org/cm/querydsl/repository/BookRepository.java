package org.cm.querydsl.repository;

import org.cm.querydsl.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * QueryDsl
 * Created By Khan, C M Abdullah on 20/6/24 : Time: 17:04
 * Ref:
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
}
