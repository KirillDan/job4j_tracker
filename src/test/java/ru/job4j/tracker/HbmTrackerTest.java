package ru.job4j.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class HbmTrackerTest {
	HbmTracker hbmTracker;
	
	@Before
	public void setup() {
		this.hbmTracker = new HbmTracker();
	}
	
	@Test
	public void createAndFindAllAndFindByIdAndFindByNameAndDelete() {
		String name = UUID.randomUUID().toString();
		String description = UUID.randomUUID().toString();
		Item item = new Item(name, description, new Timestamp(1459510232000L));
		this.hbmTracker.add(item);
		Item findedItem = this.hbmTracker.findByName(name).get(0);
		assertEquals(findedItem.getDescription(), item.getDescription());
		List<Item> allItems = this.hbmTracker.findAll();
		boolean containsItem = false;
		Integer containsItemId = null;
		for (Item item2 : allItems) {
			if (item2.getName().equals(name)) {
				containsItem = true;
				containsItemId = item2.getId();
				System.out.println("containsItemId = " + containsItemId);
			}
		}
		assertTrue(containsItem);
		Item itemFindedById = this.hbmTracker.findById(String.valueOf(containsItemId));
		assertEquals(itemFindedById.getDescription(), item.getDescription());
		String nameNew = UUID.randomUUID().toString();
		String descriptionNew = UUID.randomUUID().toString();
		Item itemNew = new Item(nameNew, descriptionNew, new Timestamp(1459510232000L));
		this.hbmTracker.replace(String.valueOf(containsItemId), itemNew);
		Item itemFindedByIdNew = this.hbmTracker.findById(String.valueOf(containsItemId));
		assertEquals(itemFindedByIdNew.getDescription(), itemNew.getDescription());
		
		this.hbmTracker.delete(String.valueOf(findedItem.getId()));
		assertTrue(this.hbmTracker.findByName(name).isEmpty());
	}
}
