package edu.ncsu.csc216.wolf_tickets.model.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests the SortedList.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class SortedListTest {

	/** Swap List to use for testing */
	SortedList<String> list;
	
	/*
	 * Sets up items before testing
	 */
	@BeforeEach
	public void setUp() {
		list = new SortedList<String>();
	}
	
	/**
	 * Tests the constructor of SwapList
	 */
	@Test
	public void testConstructor() {
		assertEquals(0, list.size());
		
	}
	
	/**
	 * Tests the size method of SwapList
	 */
	@Test
	public void testSize() {
		list.add("Wow");
		assertEquals(1, list.size());
		list.add("Yippee");
		list.add("Wahoo");
		assertEquals(3, list.size());
	}
	
	/**
	 * Tests the get method of SwapList
	 */
	@Test
	public void testGet() {
		list.add("Apple");
		list.add("Banana");
		list.add("Crab");
		
		assertEquals("Apple", list.get(0));
		assertEquals("Banana", list.get(1));
		assertEquals("Crab", list.get(2));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.get(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the add method of SwapList
	 */
	@Test
	public void testAdd() {
		list.add("Apple");
		list.add("Banana");
		list.add("Apple2");
		
		assertEquals(3, list.size());
		assertEquals("Apple", list.get(0));
		assertEquals("Apple2", list.get(1));
		assertEquals("Banana", list.get(2));
		
		list.add("Apple1");
		assertEquals(4, list.size());
		assertEquals("Apple", list.get(0));
		assertEquals("Apple2", list.get(2));
		assertEquals("Banana", list.get(3));
		assertEquals("Apple1", list.get(1));
		
		
		Exception e1 = assertThrows(NullPointerException.class, 
				() -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> list.add("Apple"));
		assertEquals("Cannot add duplicate element.", e2.getMessage());
	}
	
	/**
	 * Tests the remove method of SwapList
	 */
	@Test
	public void testRemove() {
		
		list.add("Apple");
		list.add("Banana");
		list.add("Crab");
		
		assertEquals("Banana", list.remove(1));
		assertEquals(2, list.size());
		assertEquals("Apple", list.get(0));
		assertEquals("Crab", list.get(1));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the contains method of SwapList
	 */
	@Test
	public void testContains() {
		list.add("Apple");
		assertTrue(list.contains("Apple"));
		
		assertFalse(list.contains("banana"));
	}

}
