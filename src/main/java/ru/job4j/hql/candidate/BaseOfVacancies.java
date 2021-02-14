package ru.job4j.hql.candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BaseOfVacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToMany
    private List<Vacancy> vacancies = new ArrayList<>();
    
	public static BaseOfVacancies of(String name) {
		BaseOfVacancies b = new BaseOfVacancies();
		b.name = name;
        return b;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(List<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}
	
	public void addVacancies(Vacancy vacancy) {
		this.vacancies.add(vacancy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseOfVacancies other = (BaseOfVacancies) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vacancies == null) {
			if (other.vacancies != null)
				return false;
		} else if (!vacancies.equals(other.vacancies))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseOfVacancies [id=" + id + ", name=" + name + ", vacancies=" + vacancies + "]";
	}

}
