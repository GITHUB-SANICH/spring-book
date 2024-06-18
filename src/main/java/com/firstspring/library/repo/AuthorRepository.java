package com.firstspring.library.repo;

import com.firstspring.library.models.Author;
import com.firstspring.library.models.Book;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByNameContainingIgnoreCase(String author_name);

    List<Author> findAllByOrderByNameAsc();
}
