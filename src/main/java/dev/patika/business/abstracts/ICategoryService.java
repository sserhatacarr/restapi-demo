package dev.patika.business.abstracts;

import dev.patika.entity.Category;

public interface ICategoryService {
    Category save(Category category);
    Category get(int id);
    Category update(Category category);
    boolean delete(int id);
}
