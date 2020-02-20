package repository;

import org.springframework.data.repository.CrudRepository;

import domain.AuthorizedPerson;

public interface AuthorizedPersonRepository extends CrudRepository<AuthorizedPerson, Long> {

}
