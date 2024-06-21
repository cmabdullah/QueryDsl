package org.cm.querydsl.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * QueryDsl
 * Created By Khan, C M Abdullah on 20/6/24 : Time: 17:12
 * Ref:
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

//	@Column(name = "book_name", nullable = false, unique = true)
	private String book_name;

//	private Long authorId;
	private Long publisher_id;

//	@JsonIgnore
//	@ToString.Exclude
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "author_id")
//	private Author author;
}
