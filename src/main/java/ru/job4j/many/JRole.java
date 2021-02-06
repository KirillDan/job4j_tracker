package ru.job4j.many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "j2_role")
public class JRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<JUser> users = new ArrayList<>();

	public static JRole of(String name) {
		JRole role = new JRole();
		role.name = name;
		return role;
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

	public List<JUser> getUsers() {
		return users;
	}

	public void setUsers(List<JUser> users) {
		this.users = users;
	}

	public void addUser(JUser u) {
		this.users.add(u);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JRole role = (JRole) o;
		return id == role.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
