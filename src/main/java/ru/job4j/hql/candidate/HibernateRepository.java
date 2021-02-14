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

	public <T> void save(T t) {
		tx(session -> session.save(t));
	}

	public <T> void save(List<T> ts) {
		tx(session -> {
			for (T t : ts) {
				session.save(t);
			}
			return true;
		});
	}

	public <T> List<T> getAll(Class cl) {
		return tx(session -> session.createQuery("select distinct s from " + cl.getName() + " s " + "join fetch s.base b "
				+ "join fetch b.vacancies v ").getResultList());
	}

	public <T> Candidate getById(Class cl, int id) {
		return (Candidate) tx(session -> {
			Query query = session.createQuery("select distinct s from " + cl.getName() + " s " + "join fetch s.base b "
					+ "join fetch b.vacancies v " + " where s.id = :fId");
			query.setParameter("fId", 1);
			return query.uniqueResult();
		});
	}

	public <T> Candidate getByName(Class cl, String name) {
		return (Candidate) tx(session -> {
			Query query = session.createQuery("select distinct s from " + cl.getName() + " s " + "join fetch s.base b "
					+ "join fetch b.vacancies v " + " where s.name = :fname");
			query.setParameter("fname", name);
			return query.uniqueResult();
		});
	}

	public void update(int id, String name, int experience, int salary) {
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

	public <T> void delete(Class cl, int id) {
		tx(session -> {
			session.createQuery("delete from " + cl.getName() + " where id = :fId").setParameter("fId", id)
					.executeUpdate();
			return true;
		});
	}
}
