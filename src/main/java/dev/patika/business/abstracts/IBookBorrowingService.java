package dev.patika.business.abstracts;

import dev.patika.entity.BookBorrowing;

import java.util.List;

public interface IBookBorrowingService {
    List<BookBorrowing> findAll();
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(int id);
    BookBorrowing update(BookBorrowing bookBorrowing);
    boolean delete(int id);
}
