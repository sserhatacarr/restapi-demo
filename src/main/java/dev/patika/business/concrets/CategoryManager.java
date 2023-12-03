package dev.patika.business.concrets;

import dev.patika.business.abstracts.ICategoryService;
import dev.patika.dal.ICategoryRepo;
import dev.patika.entity.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final ICategoryRepo categoryRepo;

    public CategoryManager(ICategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElse(null);
    }

    @Override
    public Category update(Category category) {
        return this.categoryRepo.save(category);
    }


    @Override
    public boolean delete(int id) {
        if (this.categoryRepo.findById(id).isPresent()) {
            this.categoryRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
