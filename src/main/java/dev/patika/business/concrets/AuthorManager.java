package dev.patika.business.concrets;

import dev.patika.business.abstracts.IAuthorService;
import dev.patika.dal.IAuthorRepo;
import dev.patika.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {
    private final IAuthorRepo authorRepo;

    public AuthorManager(IAuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElse(null);
    }

    @Override
    public Author update(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(int id) {
        if (this.authorRepo.findById(id).isPresent()) {
            this.authorRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
