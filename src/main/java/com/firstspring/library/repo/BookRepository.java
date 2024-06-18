package com.firstspring.library.repo;

import com.firstspring.library.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String book_title);
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findLastBooks(@Param("count") Integer count);
    @Query("SELECT b FROM Book b JOIN Author a ON b.author.id = :id")
    List<Book> findBooksFromAuthor(@Param("id") Long id);
}
