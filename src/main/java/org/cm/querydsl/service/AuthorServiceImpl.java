package org.cm.querydsl.service;

//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;

import com.querydsl.core.Tuple;
import com.querydsl.sql.*;
import org.cm.querydsl.domain.Book;
//import org.cm.querydsl.domain.QAuthor;
//import org.cm.querydsl.domain.QBook;
import org.cm.querydsl.domain.QAuthor;
import org.cm.querydsl.domain.QBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * QueryDsl
 * Created By Khan, C M Abdullah on 21/6/24 : Time: 10:08
 * Ref:
 */
@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ApplicationContext context;

	private final String GET_BOOK_BY_ID_QUERY = "SELECT * FROM book where id = ?";

//	private final EntityManager entityManager;
//
//	public AuthorServiceImpl(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}

	/**
	 * select * from author
	 * inner join book
	 * on author.publisher_id = book.publisher_id
	 * <p>
	 * where author.id=1
	 *
	 * @return
	 */

	@Override
	public List<Book> getAllBooksById(int id) {

		return queryDSL(id);


//		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//		QAuthor author = QAuthor.author;
//		QBook book = QBook.book;
//		JPAQuery<Book> query = queryFactory
//				.selectFrom(book)
//				.innerJoin(author)
//				.on(author.publisherId.eq(book.publisherId))
//				.where(author.id.eq(id));
//		return query.fetch();

//		return jdbcExample(id);
	}

	private List<Book> jdbcExample(int id) {
		Book book = jdbcTemplate.queryForObject(GET_BOOK_BY_ID_QUERY, new Object[]{id}, new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setBook_name(rs.getString("book_name"));
				book.setPublisher_id(rs.getLong("publisher_id"));
				return book;
			}
		});
		return List.of(book);
	}

	private List<Book> queryDSL(int id) {
		SQLTemplates templates = new MySQLTemplates();
		Configuration configuration = new Configuration(templates);
		DataSource dataSource = context.getBean(DataSource.class);
		SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, dataSource);
		QBook qbook = QBook.book;
		QAuthor qauthor = QAuthor.author;

		/**
		 * select book.bookName from book where book.bookName = ?
		 */
		List<Integer> ids = queryFactory.select(qbook.id).from(qbook)
				//.where(qbook.id.eq(1))
				.fetch();
		List<String> lastNames = queryFactory.select(qbook.book_name).from(qbook)
				.where(qbook.book_name.eq("HP"))
				.fetch();
		SQLQuery<Tuple> query = queryFactory
				.select(qbook.id, qbook.book_name, qbook.publisher_id)
				.from(qbook)
				.innerJoin(qauthor)
				.on(qauthor.publisher_id.eq(qbook.publisher_id))
				.where(qauthor.id.eq(id));

		List<Tuple> result = query.fetch();

		return result.stream().filter(Objects::nonNull).map((Tuple tuple) -> {
			Integer index = tuple.get(0, Integer.class);
			String name = tuple.get(1, String.class);
			Long publisherId = tuple.get(2, Long.class);
			return new Book(index, name, publisherId);
		}).collect(Collectors.toList());
	}
}
