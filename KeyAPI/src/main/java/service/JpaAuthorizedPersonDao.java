package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import domain.AuthorizedPerson;

@Repository("authorizedPersonDao")
public class JpaAuthorizedPersonDao implements AuthorizedPersonDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(AuthorizedPerson item) {
		em.persist(item);
	}
	
	@Override
	public void remove(AuthorizedPerson item) {
		em.remove(em.merge(item));
	}

	@Override
	public List<AuthorizedPerson> getAll() {
		TypedQuery<AuthorizedPerson> q = em.createNamedQuery("AuthorizedPerson.getAll",AuthorizedPerson.class);
		return q.getResultList();
	}

	@Override
	public AuthorizedPerson findById(long id) {
		return em.find(AuthorizedPerson.class, id);
	}

	@Override
	public void save(List<AuthorizedPerson> items) {
		for(AuthorizedPerson a : items) {
			this.add(a);
		}
	}

	@Override
	public void update(AuthorizedPerson item) {
		em.merge(item);
	}

	@Override
	public boolean exists(Long id) {
		return this.findById(id)!=null? true : false;
	}

}
