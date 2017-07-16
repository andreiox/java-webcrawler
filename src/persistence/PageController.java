package persistence;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import util.JpaUtil;

public class PageController {

	public static Page save(Page page) {
		if (page.getId() == 0)
			return persist(page);
		else
			return merge(page);
	}

	public static Page getById(int id) {
		EntityManager em = JpaUtil.createEntityManager();
		Page page = null;
		try {
			String jpql = "select p from Page p where p.id=:id";
			Query q = em.createQuery(jpql);
			q.setParameter("id", id);
			page = (Page) q.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("No result for id = " + id);
		} catch (Exception e) {
			e.printStackTrace();
			page = null;
		}
		em.close();
		return page;
	}

	public static boolean remove(Page page) {
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Page.class, page.getId()));
		em.getTransaction().commit();
		em.close();

		return true;
	}

	private static Page persist(Page page) {
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();
		em.persist(page);
		em.getTransaction().commit();
		em.refresh(page);
		em.close();
		return page;
	}

	private static Page merge(Page page) {
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();
		em.merge(page);
		em.getTransaction().commit();
		em.close();
		return page;
	}

}
