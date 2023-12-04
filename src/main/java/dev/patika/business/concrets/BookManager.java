package dev.patika.business.concrets;

import dev.patika.business.abstracts.IBookService;
import dev.patika.dal.IBookRepo;
import dev.patika.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements IBookService {
    private final IBookRepo bookRepo;

    public BookManager(IBookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findAll() {
       return this.bookRepo.findAll();
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book get(long id) {
        return this.bookRepo.findById(id).orElseThrow();
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(long id) {
        if (this.bookRepo.findById(id).isPresent()) {
            this.bookRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
