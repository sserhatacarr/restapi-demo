package dev.patika.business.abstracts;

import dev.patika.entity.Author;

public interface IAuthorService {
    Author save(Author author);
    Author get(int id);
    Author update(Author author);
    boolean delete(int id);
}
