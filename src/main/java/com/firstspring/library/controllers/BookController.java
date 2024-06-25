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
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("title", "Книги");
        Iterable<Book> books = bookRepository.findAllByOrderByTitleAsc();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/books")
    public String getBooks(@RequestParam String book_title, Model model) {
        Iterable<Book> books;
        if (book_title == null || book_title.isEmpty()) {
            books = bookRepository.findAllByOrderByTitleAsc();
        } else {
            books = bookRepository.findByTitleContainingIgnoreCase(book_title);
        }
        model.addAttribute("books", books);
        model.addAttribute("title", "Книги");
        return "books";
    }

    @GetMapping("/books/add")
    public String addBook(Model model) {
        Iterable<Author> authors = authorRepository.findAllByOrderByNameAsc();
        model.addAttribute("authors", authors);
        model.addAttribute("title", "Добавление книги");
        return "book-add";
    }

    @PostMapping("/books/add")
    public String addBookPost(@RequestParam String title, @RequestParam String annotation, @RequestParam String description, @RequestParam Long author_book, Model model) {
        model.addAttribute("title", "Добавление книги");
        Iterable<Author> authors = authorRepository.findAllByOrderByNameAsc();
        model.addAttribute("authors", authors);
        if (author_book == null){
            model.addAttribute("error_class", "p-3 mb-2 bg-danger text-white");
            model.addAttribute("error_mess", "Выберите автора");
            return "book-add";
        } else if (title.length() < 2) {
            model.addAttribute("error_class", "p-3 mb-2 bg-danger text-white");
            model.addAttribute("error_mess", "Поле заголовка должно иметь хотя бы 2 символа");
            return "book-add";
        }else if (annotation.length() < 2) {
            model.addAttribute("error_class", "p-3 mb-2 bg-danger text-white");
            model.addAttribute("error_mess", "Поле аннотации должно иметь хотя бы 2 символа");
            return "book-add";
        }else if (description.length() < 2) {
            model.addAttribute("error_class", "p-3 mb-2 bg-danger text-white");
            model.addAttribute("error_mess", "Поле описания должно иметь хотя бы 2 символа");
            return "book-add";
        }
        Book book = new Book(title, annotation, description, new Author(author_book));
        bookRepository.save(book);
        model.addAttribute("success_class", "p-3 mb-2 bg-success text-white");
        model.addAttribute("success_mess", "Книга успешно добавлена");
        return "book-add";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)) {
            return "redirect:/";
        }
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("title", "Книга");
        model.addAttribute("book", res);
        return "book";
    }
}
