package ru.job4j.mapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "driver")
public class Driver {
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	@JoinColumn(name = "history_owner_id", nullable = true, foreignKey = @ForeignKey(name = "HISTORY_OWNER_ID_FK")) 
	private HistoryOwner historyOwner;
}
