package fr.epita.services.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

public abstract class GenericDAO<Z, I> { // Z ro gozashtim yani in dao az yechizi e ye type i ke malom nis alan baadan
	// jaygozin mishe(I bejash Long gozashtim hala bayad 2ta type o provide koni
	// barash)
	// abstract class, classi hast ke gharar nist mostaghim instantiate she mitoni
	// prototype tarif koni behesh migan api design mesle protocol xcode amal
	// mikone hala harja bekhay in class o use koni
	// har methodi ke abstract dade shode behesh inja, unja majboret mikone biari va
	// beheshun @override mide

	@PersistenceContext
	private EntityManager em;

	/*
	 * @Inject
	 * 
	 * protected SeasionFactory sf;
	 */

	// in tamam karhae ke ba sf mikardimo
	// alan mikone
	// ba ye khat code in
	// requried ina baraye nested transcation hast vaghty transaction vojod
	// nadare misaze age bashe hamuno use mikone bekhatere required
	@Transactional(value = TxType.REQUIRED)
	public void create(Z entity) {

		em.persist(entity);

		/*
		 * Session session = em.openSession(); Transaction tx =
		 * session.getTransaction(); tx.begin(); session.save(question);// toye memory
		 * barname zakhire mikone na toye database chun nagoftim be // hibernate ke
		 * flush kone session o tx.commit();// hala transaction o sakhtimo begin kardimo
		 * commit kardim hale
		 */

	}

	@Transactional(value = TxType.REQUIRED)
	public void update(Z entity) {

		em.merge(entity);
	}

//	public void update(Z question) {
//		Session session = em.openSession();
//		Transaction tx = session.getTransaction();
//		tx.begin();
//		session.update(question);
//		tx.commit();
//
//	}
//
//	public void delete(Z question) {
//		Session session = em.openSession();
//		Transaction tx = session.getTransaction();
//		tx.begin();
//		session.delete(question);
//
//	}
	// (1)
	public abstract String getQuery();

	public abstract void setParam(Map<String, Object> parameters, Z criteria);

	public List<Z> search(Z criteria) {
		Query searchQuery = em.createQuery(getQuery());
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();// linkedhashmap to keep the order of
																				// insertion
		setParam(parameters, criteria);
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			searchQuery.setParameter(entry.getKey(), entry.getValue());
		}
		return searchQuery.getResultList();

//		searchQuery.setParameter("pTitle", titleP);
//		List<Question> resultList = searchQuery.getResultList();

	}

	public abstract Class<Z> getEntityClass();

	public Z getById(I id) {
		return em.find(getEntityClass(), id);

	}

	public List<Z> getAll(String db) {

		return em.createQuery("from " + db, getEntityClass()).getResultList();

	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(Z entity) {
		// em.refresh(entity);
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		// em.remove(entity);
	}

}
