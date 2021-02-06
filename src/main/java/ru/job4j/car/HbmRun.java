package ru.job4j.car;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

	public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            ModelCar[] modelCar = new ModelCar[5];
            modelCar[0] = ModelCar.of("ccx");
            modelCar[1] = ModelCar.of("agera");
            modelCar[2] = ModelCar.of("one");
            modelCar[3] = ModelCar.of("jesko");
            modelCar[4] = ModelCar.of("gemera");
            
            for (int i = 0; i < modelCar.length; i++) {
            	session.save(modelCar[i]);
            }
            
            MarkCar markCar = MarkCar.of("koenigsegg");
            for (int j = 0; j < modelCar.length; j++) {
            	markCar.addModelCars(session.load(ModelCar.class, j + 1));
			}          
            session.save(markCar);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
