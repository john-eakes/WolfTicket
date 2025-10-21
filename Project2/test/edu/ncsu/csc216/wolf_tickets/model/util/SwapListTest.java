package edu.ncsu.csc216.wolf_tickets.model.util;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests the SwapList.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class SwapListTest {

	/** Swap List to use for testing */
	SwapList<String> list;
	
	/*
	 * Sets up items before testing
	 */
	@BeforeEach
	public void setUp() {
		list = new SwapList<String>();
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
		list.add("Wow");
		assertEquals("Wow", list.get(0));
		list.add("Yippee");
		list.add("Wahoo");
		assertEquals("Wow", list.get(0));
		assertEquals("Yippee", list.get(1));
		assertEquals("Wahoo", list.get(2));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.get(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the add method of SwapList
	 */
	@Test
	public void testAdd() {
		list.add("Wow");
		assertEquals("Wow", list.get(0));
		assertEquals(1, list.size());
		list.add("Yippee");
		list.add("Wahoo");
		assertEquals(3, list.size());
		assertEquals("Wow", list.get(0));
		assertEquals("Yippee", list.get(1));
		assertEquals("Wahoo", list.get(2));
		
		Exception e1 = assertThrows(NullPointerException.class, 
				() -> list.add(null));
		assertEquals("Cannot add null element.", e1.getMessage());
	}
	
	/**
	 * Tests the remove method of SwapList
	 */
	@Test
	public void testRemove() {
		list.add("Wow");
		assertEquals("Wow", list.get(0));
		assertEquals(1, list.size());
		assertEquals("Wow", list.remove(0));
		assertEquals(0, list.size());
		list.add("Yippee");
		list.add("Wahoo");
		
		assertEquals(2, list.size());
		assertEquals("Yippee", list.get(0));
		assertEquals("Wahoo", list.get(1));
		
		assertEquals("Yippee", list.remove(0));
		assertEquals(1, list.size());
		assertEquals("Wahoo", list.get(0));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the moveUp method of SwapList
	 */
	@Test
	public void testMoveUp() {
		list.add("Wow");
		list.add("Yippee");
		list.add("Wahoo");
		
		list.moveUp(1);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(0));
		assertEquals("Wow", list.get(1));
		assertEquals("Wahoo", list.get(2));
		
		list.moveUp(0);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(0));
		assertEquals("Wow", list.get(1));
		assertEquals("Wahoo", list.get(2));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveUp(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the moveDown method of SwapList
	 */
	@Test
	public void testMoveDown() {
		list.add("Wow");
		list.add("Yippee");
		list.add("Wahoo");
		
		list.moveDown(1);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(2));
		assertEquals("Wow", list.get(0));
		assertEquals("Wahoo", list.get(1));
		
		list.moveDown(2);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(2));
		assertEquals("Wow", list.get(0));
		assertEquals("Wahoo", list.get(1));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveDown(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the moveToFront method of SwapList
	 */
	@Test
	public void testMoveToFront() {
		list.add("Wow");
		list.add("Yippee");
		list.add("Wahoo");
		
		list.moveToFront(2);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(2));
		assertEquals("Wow", list.get(1));
		assertEquals("Wahoo", list.get(0));
		
		list.moveToFront(1);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(2));
		assertEquals("Wow", list.get(0));
		assertEquals("Wahoo", list.get(1));
		
		list.moveToFront(0);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(2));
		assertEquals("Wow", list.get(0));
		assertEquals("Wahoo", list.get(1));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveToFront(3));
		assertEquals("Invalid index.", e1.getMessage());
	}
	
	/**
	 * Tests the moveToBack method of SwapList
	 */
	@Test
	public void testMoveToBack() {
		list.add("Wow");
		list.add("Yippee");
		list.add("Wahoo");
		
		list.moveToBack(0);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(0));
		assertEquals("Wow", list.get(2));
		assertEquals("Wahoo", list.get(1));
		
		list.moveToBack(1);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(0));
		assertEquals("Wow", list.get(1));
		assertEquals("Wahoo", list.get(2));
		
		list.moveToBack(2);
		assertEquals(3, list.size());
		assertEquals("Yippee", list.get(0));
		assertEquals("Wow", list.get(1));
		assertEquals("Wahoo", list.get(2));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> list.moveToBack(3));
		assertEquals("Invalid index.", e1.getMessage());
	}

}
