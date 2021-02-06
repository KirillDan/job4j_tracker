package ru.job4j.book;

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

            Book one = Book.of("Java");
            Book two = Book.of("CSS");
            Book three = Book.of("Spring");

            Author first = Author.of("Nikolay");
            first.addBook(one);
            first.addBook(two);
            first.addBook(three);

            Author second = Author.of("Anatoliy");
            second.addBook(two);
            second.addBook(three);
            
            Author third = Author.of("Vasya");
            third.addBook(three);

            session.persist(first);
            session.persist(second);
            session.persist(third);

            Author author = session.get(Author.class, 1);
            session.remove(author);
            
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
