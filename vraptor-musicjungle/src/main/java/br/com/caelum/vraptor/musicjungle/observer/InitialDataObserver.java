package br.com.caelum.vraptor.musicjungle.observer;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.vraptor.events.VRaptorInitialized;
import br.com.caelum.vraptor.musicjungle.model.User;

/**
 * <p> Insert some data when VRaptor is initialized.</p>
 * 
 * This is an example that you can observe any event triggered by VRaptor.
 * 
 * See more at {@link http://www.vraptor.org/en/docs/events/}.
 *
 * @author Rodrigo Turini
 */
@Dependent
public class InitialDataObserver {

	public void insert(@Observes VRaptorInitialized event) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		User defaultUser = new User();
		defaultUser.setLogin("vraptor");
		defaultUser.setPassword("vraptor");
		defaultUser.setName("VRaptor's Default User");

		manager.persist(defaultUser);
		manager.getTransaction().commit();
		manager.close(); 
		factory.close();
	}
}