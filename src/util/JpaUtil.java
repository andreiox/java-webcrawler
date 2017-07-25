package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static final String PERSISTENT_UNIT = "java-webcrawler";
	private static EntityManagerFactory factory = null;

	static {
		factory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT);
	}

	public static EntityManager createEntityManager() {
		return factory.createEntityManager();
	}

}
