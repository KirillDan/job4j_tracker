package ru.job4j.hql.candidate;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Candidate {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int experience;
	private int salary;
	
	@OneToOne(fetch = FetchType.LAZY)
	private BaseOfVacancies base;
	
	public static Candidate of(String name, int experience, int salary) {
		Candidate c = new Candidate();
		c.name = name;
		c.experience = experience;
		c.salary = salary;
		return c;
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

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	

	public BaseOfVacancies getBase() {
		return base;
	}

	public void setBase(BaseOfVacancies base) {
		this.base = base;
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
		Candidate other = (Candidate) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", experience=" + experience + ", salary=" + salary
				+ ", base=" + base + "]";
	}
}
