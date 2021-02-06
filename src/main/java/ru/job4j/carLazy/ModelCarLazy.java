package ru.job4j.carLazy;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lazy_modelcar")
public class ModelCarLazy {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "markCar_id")
	private MarkCarLazy markCar;
	
	public static ModelCarLazy of(String name, MarkCarLazy markCar) {
		ModelCarLazy modelCar = new ModelCarLazy();
		modelCar.name = name;
		modelCar.markCar = markCar;
		return modelCar;
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

	public MarkCarLazy getMarkCar() {
		return markCar;
	}

	public void setMarkCar(MarkCarLazy markCar) {
		this.markCar = markCar;
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
		ModelCarLazy other = (ModelCarLazy) obj;
		if (id != other.id)
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
		return "ModelCar [id=" + id + ", name=" + name + "]";
	}
	
	
}
