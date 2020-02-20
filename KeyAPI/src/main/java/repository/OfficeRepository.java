package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Office;

public interface OfficeRepository extends CrudRepository<Office, Long> {

}
