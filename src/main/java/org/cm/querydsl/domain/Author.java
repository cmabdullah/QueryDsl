package org.cm.querydsl.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * QueryDsl
 * Created By Khan, C M Abdullah on 20/6/24 : Time: 17:03
 * Ref:
 */
@Data
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "author_name", nullable = false, unique = true)
	private String authorName;

	private Long publisherId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
	List<Book> books;
}
