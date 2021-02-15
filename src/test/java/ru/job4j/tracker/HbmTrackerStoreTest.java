package ru.job4j.tracker;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class HbmTrackerStoreTest {

	@Test
	public void whenAddAndFindByName() {
		Store store = new HbmTracker();
		Item item = new Item("name1", "description1", new Timestamp(System.currentTimeMillis()));
		store.add(item);
		assertEquals(item, store.findByName("name1").get(0));
	}
	
	@Test
	public void whenAddAndFindById() {
		Store store = new HbmTracker();
		Item item = new Item("name2", "description2", new Timestamp(System.currentTimeMillis()));
		item = store.add(item);
		assertEquals(item, store.findById("1"));
	}
	
	@Test
	public void whenAddAndFindAll() {
		Store store = new HbmTracker();
		Item item1 = new Item("name3", "description3", new Timestamp(System.currentTimeMillis()));
		Item item2 = new Item("name4", "description4", new Timestamp(System.currentTimeMillis()));
		store.add(item1);
		store.add(item2);
		List<Item> list = store.findAll();
		assertEquals(item1, list.get(0));
		assertEquals(item2, list.get(1));
	}
	
	@Test
	public void whenAddAndReplaceAndFindById() {
		Store store = new HbmTracker();
		Item item = new Item("name5", "description5", new Timestamp(System.currentTimeMillis()));
		item = store.add(item);
		item.setName("nameNew");;
		store.replace(item.getId().toString(), item);
		assertEquals(item, store.findById("1"));
	}
	
	@Test
	public void whenAddAndDelete() {
		Store store = new HbmTracker();
		Item item = new Item("name2", "description2", new Timestamp(System.currentTimeMillis()));
		item = store.add(item);
		store.delete(item.getId().toString());
		assertEquals(null, store.findById("1"));
	}
}
