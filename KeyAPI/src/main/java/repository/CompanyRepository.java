package repository;
import org.springframework.data.repository.CrudRepository; 
import domain.Company;

public interface CompanyRepository extends CrudRepository<Company,Long> {

}
