package ru.job4j.carLazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lazy_markcar")
public class MarkCarLazy {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "markCar")
	private List<ModelCarLazy> modelCars = new ArrayList<>();
	
	public static MarkCarLazy of(String name) {
		MarkCarLazy markCar = new MarkCarLazy();
		markCar.name = name;
		return markCar;
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

	public List<ModelCarLazy> getModelCars() {
		return modelCars;
	}

	public void setModelCars(List<ModelCarLazy> modelCars) {
		this.modelCars = modelCars;
	}
	
	public void addModelCars(ModelCarLazy modelCar) {
		this.modelCars.add(modelCar);
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
		MarkCarLazy other = (MarkCarLazy) obj;
		if (id != other.id)
			return false;
		if (modelCars == null) {
			if (other.modelCars != null)
				return false;
		} else if (!modelCars.equals(other.modelCars))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MarkCar [id=" + id + ", name=" + name + ", modelCars=" + modelCars + "]";
	}
	
	
}
