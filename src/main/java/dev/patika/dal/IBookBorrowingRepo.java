package dev.patika.dal;

import dev.patika.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookBorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
