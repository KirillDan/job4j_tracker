package ru.job4j.hql.candidate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateRepository {
	private final StandardServiceRegistry registry;
	private SessionFactory sf;

	public HibernateRepository() {
		this.registry = new StandardServiceRegistryBuilder().configure().build();
		this.sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	public void destroy() {
		StandardServiceRegistryBuilder.destroy(registry);
	}

	private <T> T tx(final Function<Session, T> command) {
		final Session session = sf.openSession();
		final Transaction tx = session.beginTransaction();
		try {
			T rsl = command.apply(session);
			tx.commit();
			return rsl;
		} catch (final Exception e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public void save(List<Candidate> candidates) {
		tx(session -> {
			for (Candidate candidate : candidates) {
				session.save(candidate);
			}
			return true;
		});
	}

	public List<Candidate> getAll() {
		return tx(session -> session.createQuery("from Candidate").getResultList());
	}

	public Candidate getById(int id) {
		return (Candidate) tx(session -> {
			Query query = session.createQuery("from Candidate s where s.id = :fId");
			query.setParameter("fId", 1);
			return query.uniqueResult();
		});
	}

	public Candidate getByName(String name) {
		return (Candidate) tx(session -> {
			Query query = session.createQuery("from Candidate s where s.name = :fname");
			query.setParameter("fname", name);
			return query.uniqueResult();
		});
	}

	public void update(int id,String name, int experience, int salary) {
		tx(session -> {
			Query query = session.createQuery(
					"update Candidate s set s.name = :newName, s.experience = :newExperience, s.salary = :newSalary where s.id = :fId");
			query.setParameter("newName", name);
			query.setParameter("newExperience", experience);
			query.setParameter("newSalary", salary);
			query.setParameter("fId", id);
			query.executeUpdate();
			return true;
		});
	}
	
	public void delete(int id) {
		tx(session -> {
			session.createQuery("delete from Candidate where id = :fId")
	        .setParameter("fId", id)
	        .executeUpdate();
			return true;
		});
	}
}
