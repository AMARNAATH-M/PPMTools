package io.agileintellligence.fullstack.repositories;

import io.agileintellligence.fullstack.domain.Backlog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog,Long> {
    Backlog findByProjectIdentifier(String Identifier);
}
