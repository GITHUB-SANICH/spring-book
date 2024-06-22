package com.firstspring.library.controllers;

import com.firstspring.library.models.Author;
import com.firstspring.library.models.Book;
import com.firstspring.library.repo.AuthorRepository;
import com.firstspring.library.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("title", "Авторы");
        Iterable<Author> authors = authorRepository.findAllByOrderByNameAsc();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @PostMapping("/authors")
    public String getAuthorsPost(@RequestParam String author_name, Model model) {
        Iterable<Author> authors;
        if (author_name == null || author_name.isEmpty()) {
            authors = authorRepository.findAllByOrderByNameAsc();
        } else {
            authors = authorRepository.findByNameContainingIgnoreCase(author_name);
        }
        model.addAttribute("authors", authors);
        model.addAttribute("title", "Авторы");
        return "authors";
    }

    @GetMapping("/authors/{id}")
    public String getAuthor(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Author> author = authorRepository.findById(id);
        ArrayList<Author> res = new ArrayList<>();
        author.ifPresent(res::add);
        model.addAttribute("title", "Автор");
        model.addAttribute("author", res);
        return "author";
    }

    @GetMapping("/authors/{id}/books")
    public String getAuthorsBooks(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)) {
            return "redirect:/";
        }
        List<Book> books = bookRepository.findAllByAuthor(new Author(id));
        model.addAttribute("title", "Книги автора");
        model.addAttribute("books", books);
        return "author-books";
    }
}
