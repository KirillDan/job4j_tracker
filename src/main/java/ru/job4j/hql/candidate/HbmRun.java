package ru.job4j.hql.candidate;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
    	HibernateRepository repository = new HibernateRepository();
        try {
            Candidate one = Candidate.of("Alex", 1, 70000);
            Candidate two = Candidate.of("Nikolay", 2, 10000);
            Candidate three = Candidate.of("Nikita", 3, 12000);
            List<Candidate> candidates = new ArrayList<Candidate>();
            candidates.add(one);
            candidates.add(two);
            candidates.add(three);
//            repository.save(candidates);

            System.out.println(repository.getById(1));
            System.out.println(repository.getByName("Alex"));
            repository.update(1, "AlexNew", 2, 85000);
            System.out.println(repository.getAll());
            repository.delete(1);
            System.out.println(repository.getAll());
        } finally {
        	repository.destroy();
		}
    }
}
