package mate.academy.onlinebookstore.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.BookDto;
import mate.academy.onlinebookstore.dto.CreateBookRequestDto;
import mate.academy.onlinebookstore.model.Book;
import mate.academy.onlinebookstore.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookDto> findAll(){
        return bookService.findAll();
    }

    @GetMapping
    public BookDto getBookById(Long id) {
        return null;
    }

    @PostMapping
    private Book save(@RequestBody CreateBookRequestDto bookDto) {
        return null;
    }
}
