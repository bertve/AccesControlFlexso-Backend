package service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import domain.Company;

@Repository("CompanyDao")
public class JpaCompanyDao implements CompanyDao {

	@PersistenceContext
	private EntityManager em;

	
	@Override
	@Transactional
	public void add(Company item) {
		em.persist(item);
	}

	@Override
	@Transactional
	public void remove(Company item) {
		em.remove(em.merge(item));
	}
	
	@Override
	@Transactional
	public List<Company> getAll() {
		TypedQuery<Company> q = em.createNamedQuery("Company.getAll",Company.class);
		return q.getResultList();
	}

	@Override
	@Transactional
	public Company findById(long id) {
		return em.find(Company.class, id);
	}

	@Override
	@Transactional
	public void save(List<Company> items) {
		for(Company c : items) {
			this.add(c);
		}
	}

	@Override
	public void update(Company item) {
		em.merge(item);
	}

	@Override
	public boolean exists(Long id) {
		return this.findById(id)!=null? true : false;
	}


}
