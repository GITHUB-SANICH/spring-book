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
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @GetMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("title", "Книги");
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/books")
    public String getBooks(@RequestParam String title, Model model){
//        Book book = new Book(title);
        model.addAttribute("title", "Книги");
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

//    @GetMapping("/books/add")
//    public String addBook(Model model) {
//        model.addAttribute("title", "Добавление книги");
//        return "book-add";
//    }

//    @PostMapping("/books/add")
//    public String addBookPost(@RequestParam String title, @RequestParam String annotation, RequestParam String description, @RequestParam Author author, Model model) {
//        Book book = new Book(title, annotation, description, author);
//        bookRepository.save(book);
//        return "redirect:/";
//    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable(value = "id") long id, Model model) {
        if(!bookRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("title", "Книга");
        model.addAttribute("book", res);
        return "book-details";
    }

//    @GetMapping("/books/{id}/edit")
//    public String bookEdit(@PathVariable(value = "id") long id, Model model) {
//        if(!bookRepository.existsById(id)){
//            return "redirect:/";
//        }
//        Optional<Book> book = bookRepository.findById(id);
//        ArrayList<Book> res = new ArrayList<>();
//        book.ifPresent(res::add);
//        model.addAttribute("book", res);
//        return "book-edit";
//    }
//
//    @PostMapping("/books/{id}/edit")
//    public String bookPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String description, @RequestParam Author author, Model model) {
//        Book book = bookRepository.findById(id).orElseThrow();
//        book.setTitle(title);
//        book.setDescription(description);
//        book.setAuthor(author);
//        bookRepository.save(book);
//        return "redirect:/";
//    }

//    @PostMapping("/books/{id}/remove")
//    public String bookPostDelete(@PathVariable(value = "id") long id, Model model) {
//        Book book = bookRepository.findById(id).orElseThrow();
//        bookRepository.delete(book);
//        return "redirect:/";
//    }
}
