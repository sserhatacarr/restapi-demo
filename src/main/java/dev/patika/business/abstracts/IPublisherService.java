package dev.patika.business.abstracts;

import dev.patika.entity.Publisher;

public interface IPublisherService {
    Publisher save(Publisher publisher);
    Publisher get(int id);
    Publisher update(Publisher publisher);
    boolean delete(int id);
}
