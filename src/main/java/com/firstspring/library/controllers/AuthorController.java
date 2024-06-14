package com.firstspring.library.controllers;

import com.firstspring.library.models.Author;
import com.firstspring.library.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        model.addAttribute("title", "Авторы");
        model.addAttribute("search", "authors_search");
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @PostMapping("/authors")
    public String getAuthorsPost(@RequestParam String name_author, Model model) {
        model.addAttribute("title", "Авторы");
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/authors{id}")
    public String getAuthor(Model model) {
        model.addAttribute("title", "Авторы");
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }
//
//    @GetMapping("/authors/add")
//    public String addAuthor(Model model) {
//        model.addAttribute("title", "Авторы");
//        return "authors";
//    }
//    @PostMapping("/authors/add")
//    public String addAuthorPost(Model model) {
//        model.addAttribute("title", "Авторы");
//        return "authors";
//    }
}
