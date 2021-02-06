package ru.job4j.toone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class HbmRun {
	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Role role = create(Role.of("ADMIN"), sf);
            create(User.of("Petr Arsentev", role), sf);
            for (User user : findAll(User.class, sf)) {
                System.out.println(user.getName() + " " + user.getRole().getName());
            }
            
            replace("1", Role.of("Vasya"), sf);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
	}

	public static <T> T create(T model, SessionFactory sf) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		session.close();
		return model;
	}

	public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
		Session session = sf.openSession();
		session.beginTransaction();
		List<T> list = session.createQuery("from " + cl.getName(), cl).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static <T> boolean replace(String id, T model, SessionFactory sf)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Session session = sf.openSession();
		session.beginTransaction();
		T res = (T) session.find(model.getClass(), Integer.valueOf(id));

		Method[] fields = model.getClass().getMethods();
		for (Method method : fields) {
			if (method.getName().indexOf("get") == 0 && !method.getName().equals("getId")
					&& !method.getName().equals("getClass")) {
				System.out.println("method.getName() = " + method.getName());
				Method resMethod = res.getClass().getMethod("set" + method.getName().substring(3), String.class);
				System.out.println(resMethod);
				resMethod.invoke(res, method.invoke(model, null));
			}
		}
		session.getTransaction().commit();
		session.close();
		return true;
	}

}
