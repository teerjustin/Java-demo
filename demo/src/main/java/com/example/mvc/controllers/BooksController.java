package com.example.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mvc.models.Book;
import com.example.mvc.services.BookService;


@Controller
public class BooksController {
    private final BookService bookService;
    
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @RequestMapping("/books")
    public String index(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        System.out.println("I am here");
        return "/books/index.jsp";
    }
    
    @RequestMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new.jsp";
    }
    
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/books/new.jsp";
        } else {
            bookService.createBook(book);
            return "redirect:/books";
        }
    }
    
    @RequestMapping(value="/books/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "/books/show.jsp";
    }
    
    @RequestMapping(value="/books/{id}/delete", method=RequestMethod.POST)
    public String delete(@PathVariable("id") Long id) {
    	System.out.println("I TRIED TO DELETE");
    	bookService.deleteBook(id);
    	System.out.println("I TRIED TO DELETE X 2");
    	return "redirect:/books";
    }
    
    @RequestMapping(value="/books/{id}/edit")
    public String editPage(@PathVariable("id") Long id, Model model) {
    	Book book = bookService.findBook(id);
    	model.addAttribute("book", book);
    	return "/books/edit.jsp";
    }
    
    
    @RequestMapping(value="/books/{id}/edit", method=RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, String title, String description, String language, Integer numberOfPages) {
    	System.out.println("I TRIED TO EDIT");
    	System.out.println(id + title + description + language + numberOfPages);
    	bookService.updateBook(id, title, description, language, numberOfPages);
    	System.out.println("I TRIED TO EDIT X 2");
    	return "redirect:/books";
    }
    
}