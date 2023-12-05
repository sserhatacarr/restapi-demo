package dev.patika.api;

import dev.patika.business.abstracts.IBookBorrowingService;
import dev.patika.core.config.modelMapper.IModelMapperService;
import dev.patika.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import dev.patika.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import dev.patika.dto.response.bookBorrowing.BookBorrowingResponse;
import dev.patika.entity.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/loan-books")
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;

    public BookBorrowingController(
            IBookBorrowingService bookBorrowingService,
            IModelMapperService modelMapper
    ) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public BookBorrowingResponse save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        BookBorrowing bookBorrowing = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);
        bookBorrowingService.save(bookBorrowing);
        return this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookBorrowingResponse get(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        return this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookBorrowingResponse update(@PathVariable("id") int id, @Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, bookBorrowing);
        bookBorrowingService.save(bookBorrowing);
        return this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        boolean isDeleted = bookBorrowingService.delete(id);
        if (isDeleted) {
            return String.format("BookBorrowing with ID %d deleted", id);
        } else {
            return String.format("No BookBorrowing with ID %d found", id);
        }
    }

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookBorrowingResponse> findAll() {
        List<BookBorrowing> bookBorrowings = this.bookBorrowingService.findAll();
        return bookBorrowings.stream()
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class))
                .collect(Collectors.toList());
    }
}