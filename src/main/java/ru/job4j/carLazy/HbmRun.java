package ru.job4j.carLazy;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ru.job4j.lazy.Category;
import ru.job4j.lazy.Task;

public class HbmRun {

	public static void save() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();

			MarkCarLazy markCar = MarkCarLazy.of("koenigsegg");
			ModelCarLazy[] modelCar = new ModelCarLazy[5];
			modelCar[0] = ModelCarLazy.of("ccx", markCar);
			modelCar[1] = ModelCarLazy.of("agera", markCar);
			modelCar[2] = ModelCarLazy.of("one", markCar);
			modelCar[3] = ModelCarLazy.of("jesko", markCar);
			modelCar[4] = ModelCarLazy.of("gemera", markCar);

			for (int i = 0; i < modelCar.length; i++) {
				session.save(modelCar[i]);
			}

			for (int j = 0; j < modelCar.length; j++) {
				markCar.addModelCars(session.load(ModelCarLazy.class, j + 1));
			}
			session.save(markCar);

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public static void getMarkCarLazy() {
		List<MarkCarLazy> list = new ArrayList<>();
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			list = session.createQuery("select distinct m from MarkCarLazy m join fetch m.modelCars").list();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		for (ModelCarLazy modelCarLazy : list.get(0).getModelCars()) {
			System.out.println(modelCarLazy);
		}

	}

	public static void main(String[] args) {
		getMarkCarLazy();
	}
}
