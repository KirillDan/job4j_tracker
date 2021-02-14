package ru.job4j.hql.candidate;

import java.util.ArrayList;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
    	HibernateRepository repository = new HibernateRepository();
        try {
//        	Vacancy v1 = Vacancy.of("Java developer");
//        	Vacancy v2 = Vacancy.of("JS developer");
//        	List<Vacancy> vacancies = new ArrayList<>();
//        	vacancies.add(v1);
//        	vacancies.add(v2);
//        	BaseOfVacancies base = BaseOfVacancies.of("Base1");
//        	base.addVacancies(v1);
//        	base.addVacancies(v2);       	
//            Candidate one = Candidate.of("Alex", 1, 70000);
//            Candidate two = Candidate.of("Nikolay", 2, 10000);
//            Candidate three = Candidate.of("Nikita", 3, 12000);
//            one.setBase(base);
//            two.setBase(base);
//            three.setBase(base);
//            List<Candidate> candidates = new ArrayList<Candidate>();
//            candidates.add(one);
//            candidates.add(two);
//            candidates.add(three);
//            repository.save(vacancies);
//            repository.save(base);
//            repository.save(candidates);

            System.out.println(repository.getById(Candidate.class, 4));
            System.out.println(repository.getByName(Candidate.class, "Alex"));
            repository.update(4, "AlexNew", 2, 85000);
            System.out.println(repository.getAll(Candidate.class));
            repository.delete(Candidate.class, 4);
            System.out.println(repository.getAll(Candidate.class));
        } finally {
        	repository.destroy();
		}
    }
}
