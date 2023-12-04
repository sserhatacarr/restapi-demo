package dev.patika.business.concrets;

import dev.patika.business.abstracts.IPublisherService;
import dev.patika.dal.IPublisherRepo;
import dev.patika.entity.Publisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {
    private final IPublisherRepo publisherRepo;

    public PublisherManager(IPublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElse(null);
    }

    @Override
    public Publisher update(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public boolean delete(int id) {
        if (this.publisherRepo.findById(id).isPresent()) {
            this.publisherRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
