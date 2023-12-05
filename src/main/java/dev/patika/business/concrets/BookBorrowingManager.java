package dev.patika.business.concrets;

import dev.patika.business.abstracts.IBookBorrowingService;
import dev.patika.dal.IBookBorrowingRepo;
import dev.patika.dal.IBookRepo;
import dev.patika.entity.Book;
import dev.patika.entity.BookBorrowing;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final IBookBorrowingRepo bookBorrowingRepo;
    private final IBookRepo bookRepo;

    public BookBorrowingManager(IBookBorrowingRepo bookBorrowingRepo, IBookRepo bookRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        Book book = bookRepo.findById(bookBorrowing.getBook().getId()).orElse(null);
        if (book != null) {
            if (book.getStockAmount() > 0) {
                book.setStockAmount(book.getStockAmount() - 1);
                bookRepo.save(book);
                return this.bookBorrowingRepo.save(bookBorrowing);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is out of stock");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        Book book = bookRepo.findById(bookBorrowing.getBook().getId()).orElse(null);
        if (book != null) {
            book.setStockAmount(book.getStockAmount() + 1);
            bookRepo.save(book);
            return this.bookBorrowingRepo.save(bookBorrowing);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        if (this.bookBorrowingRepo.findById(id).isPresent()) {
            this.bookBorrowingRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BookBorrowing> findAll() {
        return this.bookBorrowingRepo.findAll();
    }
}