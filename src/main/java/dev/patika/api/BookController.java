package dev.patika.api;

import dev.patika.business.abstracts.IAuthorService;
import dev.patika.business.abstracts.IBookService;
import dev.patika.business.abstracts.IPublisherService;
import dev.patika.core.config.modelMapper.IModelMapperService;
import dev.patika.dto.request.book.BookSaveRequest;
import dev.patika.dto.request.book.BookUpdateRequest;
import dev.patika.dto.response.book.BookResponse;
import dev.patika.entity.Author;
import dev.patika.entity.Book;
import dev.patika.entity.Publisher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;

    public BookController(
            IBookService bookService,
            IModelMapperService modelMapper,
            IAuthorService authorService,
            IPublisherService publisherService
    ) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookResponse save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book book = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        book.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        book.setPublisher(publisher);

        bookService.save(book);
        return this.modelMapper.forResponse().map(book, BookResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        return this.modelMapper.forResponse().map(book, BookResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse update(@PathVariable("id") int id, @Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book book = this.bookService.get(id);
        this.modelMapper.forRequest().map(bookUpdateRequest, book);

        Author author = this.authorService.get(bookUpdateRequest.getAuthorId());
        book.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookUpdateRequest.getPublisherId());
        book.setPublisher(publisher);

        bookService.save(book);
        return this.modelMapper.forResponse().map(book, BookResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        boolean isDeleted = bookService.delete(id);
        if (isDeleted) {
            return String.format("Book with ID %d deleted", id);
        } else {
            return String.format("No Book with ID %d found", id);
        }
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookResponse> findAll() {
        List<Book> books = this.bookService.findAll();
        return books.stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
    }

    private BookResponse convertToBookResponse(Book book) {
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);

        if (book.getAuthor() != null) {
            bookResponse.setAuthorName(book.getAuthor().getName());
        }

        if (book.getPublisher() != null) {
            bookResponse.setPublisherName(book.getPublisher().getName());
        }

        return bookResponse;
    }
}
