package com.firstspring.library.repo;

import com.firstspring.library.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
