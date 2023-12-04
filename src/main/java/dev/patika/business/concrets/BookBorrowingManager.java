package dev.patika.business.concrets;

import dev.patika.business.abstracts.IBookBorrowingService;
import dev.patika.dal.IBookBorrowingRepo;
import dev.patika.entity.BookBorrowing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final IBookBorrowingRepo bookBorrowingRepo;

    public BookBorrowingManager(IBookBorrowingRepo bookBorrowingRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElse(null);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
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