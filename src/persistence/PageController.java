package persistence;

import java.util.ArrayList;
import java.util.List;

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

	@SuppressWarnings("unchecked")
	public static List<Page> getNextPagesToCrawl(int limit) {
		EntityManager em = JpaUtil.createEntityManager();
		List<Page> list = null;
		try {
			String jpql = "select p from Page p order by p.id, p.generationNumber";
			Query q = em.createQuery(jpql);
			q.setMaxResults(limit);
			list = q.getResultList();

		} catch (NoResultException e) {
			System.out.println("There are no pages left to crawl.");
			list = new ArrayList<Page>();
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		em.close();
		return list;
	}

	public static Integer getLastGenerationNumber() {
		EntityManager em = JpaUtil.createEntityManager();
		Integer lastGenerationNumber = 0;
		try {
			String jpql = "select p.generationNumber from Page p order by p.generationNumber desc";
			Query q = em.createQuery(jpql);
			q.setMaxResults(1);
			lastGenerationNumber = (int) q.getSingleResult();

		} catch (NoResultException e) {
			System.out.println("There are no pages left to crawl.");
			lastGenerationNumber = 0;
		} catch (Exception e) {
			e.printStackTrace();
			lastGenerationNumber = null;
		}
		em.close();
		return lastGenerationNumber;
	}

	public static Page getPageFromURL(String pageUrl) {
		EntityManager em = JpaUtil.createEntityManager();
		Page page = null;
		try {
			String jpql = "select p from Page p where p.pageUrl=:url";
			Query q = em.createQuery(jpql);
			q.setParameter("url", pageUrl);
			q.setMaxResults(1);
			page = (Page) q.getSingleResult();

		} catch (NoResultException e) {
			page = null;
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
		try {
			em.getTransaction().begin();
			em.persist(page);
			em.getTransaction().commit();
			em.refresh(page);
		} catch (Exception e) {
			page = null;
		}
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
