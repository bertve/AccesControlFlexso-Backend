package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import domain.Office;

@Repository("officeDao")
public class JpaOfficeDao implements OfficeDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void add(Office item) {
		em.persist(item);
	}

	@Override
	@Transactional
	public void remove(Office item) {
		em.remove(em.merge(item));
	}

	@Override
	@Transactional
	public List<Office> getAll() {
		TypedQuery<Office> q = em.createNamedQuery("Office.getAll",Office.class);
		return q.getResultList();
	}

	@Override
	@Transactional
	public Office findById(long id) {
		return em.find(Office.class, id);
	}

	@Override
	@Transactional
	public void save(List<Office> items) {
		for(Office o : items) {
			this.add(o);
		}
	}

	@Override
	public void update(Office item) {
		em.merge(item);
	}

	@Override
	public boolean exists(Long id) {
		return this.findById(id)!=null? true : false;
	}

}
