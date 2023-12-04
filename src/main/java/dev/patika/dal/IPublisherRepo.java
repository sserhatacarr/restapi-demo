package dev.patika.dal;

import dev.patika.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepo extends JpaRepository<Publisher, Integer> {
}
