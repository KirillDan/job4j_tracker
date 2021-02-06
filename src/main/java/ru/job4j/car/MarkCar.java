package ru.job4j.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MarkCar {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ModelCar> modelCars = new ArrayList<>();
	
	public static MarkCar of(String name) {
		MarkCar markCar = new MarkCar();
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

	public List<ModelCar> getModelCars() {
		return modelCars;
	}

	public void setModelCars(List<ModelCar> modelCars) {
		this.modelCars = modelCars;
	}
	
	public void addModelCars(ModelCar modelCar) {
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
		MarkCar other = (MarkCar) obj;
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
