package dev.patika.dto.response.bookBorrowing;

import java.time.LocalDate;

public class BookBorrowingResponse {
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private int bookId;
}
