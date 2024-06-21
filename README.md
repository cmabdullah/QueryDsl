expected

```
select * from book
 inner join author
 on author.publisher_id = book.publisher_id
 where author.id=1
```

generated sql query
```
from Book book
  inner join Author author with author.publisherId = book.publisherId
where author.id = ?1
```