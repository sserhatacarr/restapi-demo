package dev.patika.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private String name;
    private int publishYear;
    private int authorId;
    private int publisherId;
}
