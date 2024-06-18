package com.firstspring.library.controllers;

import com.firstspring.library.models.Book;
import com.firstspring.library.repo.AuthorRepository;
import com.firstspring.library.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Book> books = bookRepository.findLastBooks(10);
        model.addAttribute("books", books);
        model.addAttribute("title", "Главная страница");
        return "home";
    }
}