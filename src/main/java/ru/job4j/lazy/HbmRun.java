package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
	public static void save() {
		List<Category> list = new ArrayList<>();
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			Category c1 = Category.of("Consulting");
			Task t1 = Task.of("Consultation on Hibernate", c1);
			Task t2 = Task.of("Consultation on Spring", c1);
			Task t3 = Task.of("Consultation on Servlet", c1);
			c1.addTasks(t1);
			c1.addTasks(t2);
			c1.addTasks(t3);

			session.persist(c1);
			session.persist(t1);
			session.persist(t2);
			session.persist(t3);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StandardServiceRegistryBuilder.destroy(registry);
		}

	}

	public static void getCategoryNotWorkVariant() {
		List<Category> list = new ArrayList<>();
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			list = session.createQuery("from Category").list();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		for (Category category : list) {
			for (Task task : category.getTasks()) {
				System.out.println(task);
			}
		}
	}

	public static void getCategorySessionVariant() {
		List<Category> list = new ArrayList<>();
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			list = session.createQuery("from Category").list();
			for (Category category : list) {
				for (Task task : category.getTasks()) {
					System.out.println(task);
				}
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	public static void getCategoryJoinFetchVariant() {
		List<Category> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            list = session.createQuery(
                    "select distinct c from Category c join fetch c.tasks"
            ).list();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Task task : list.get(0).getTasks()) {
            System.out.println(task);
        }

	}

	public static void main(String[] args) {
		getCategoryJoinFetchVariant();
	}
}
