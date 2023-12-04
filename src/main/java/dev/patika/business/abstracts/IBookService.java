package dev.patika.business.abstracts;

import dev.patika.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book save(Book book);
    Book get(long id);
    Book update(Book book);
    boolean delete(long id);
}
