package edu.ncsu.csc216.wolf_tickets.model.category;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
/**
 * Tests the AbstractCategory.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class AbstractCategoryTest {

	/** AbstractCategory used for testing */
	AbstractCategory category;
	/** Ticket to be used for testing */
	Ticket ticket1;
	/** Ticket to be used for testing */
	Ticket ticket2;
	
	/**
	 * Setup of objects for testing
	 */
	@BeforeEach
	public void setUp() {
		category = new TicketCategory("John Tickets", 7);
		ticket1 = new Ticket("Help me", "I really need help.", "My Wife", true);
		
		ticket2 = new Ticket("Wowza", "So cool.", "", false);
	}
	
	/**
	 * Tests the constructor for AbstractCategory
	 */
	@Test
	public void testConstructor() {
		assertEquals("John Tickets", category.getCategoryName());
		assertEquals(7, category.getCompletedCount());
	}
	
	/**
	 * Tests the getCategoryName method for AbstractCategory
	 */
	@Test
	public void testGetCategoryName() {
		assertEquals("John Tickets", category.getCategoryName());
		
	}
	
	/**
	 * Tests the setCategoryName method for AbstractCategory
	 */
	@Test
	public void testSetCategoryName() {
		category.setCategoryName("Amazing");
		assertEquals("Amazing", category.getCategoryName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> category.setCategoryName(""));
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> category.setCategoryName(null));
		assertEquals("Invalid name.", e1.getMessage());
		assertEquals("Invalid name.", e2.getMessage());
	}
	
	/**
	 * Tests the getTickets method for AbstractCategory
	 */
	@Test
	public void testGetTickets() {
		assertTrue(category.getTickets() instanceof ISwapList<Ticket>);
		assertEquals(0, category.getTickets().size());
	}
	
	/**
	 * Tests the getCompletedCount method for AbstractCategory
	 */
	@Test
	public void testGetCompletedCount() {
		assertEquals(7, category.getCompletedCount());
	}
	
	/**
	 * Tests the addTicket method for AbstractCategory
	 */
	@Test
	public void testAddTicket() {
		category.addTicket(ticket1);
		assertEquals(1, category.getTickets().size());
		assertEquals(ticket1, category.getTickets().get(0));
		assertEquals("John Tickets", ticket1.getCategoryName());
		
		category.addTicket(ticket2);
		assertEquals(2, category.getTickets().size());
		assertEquals(ticket1, category.getTickets().get(0));
		assertEquals(ticket2, category.getTickets().get(1));
		assertEquals("John Tickets", ticket1.getCategoryName());
		assertEquals("John Tickets", ticket2.getCategoryName());
	}
	
	/**
	 * Tests the removeTicket method for AbstractCategory
	 */
	@Test
	public void testRemoveTicket() {
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		
		assertEquals(ticket2, category.removeTicket(1));
		assertEquals(1, category.getTickets().size());
		assertEquals(ticket1, category.getTickets().get(0));
	}
	
	/**
	 * Tests the getTicket method for AbstractCategory
	 */
	@Test
	public void testGetTicket() {
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		
		assertEquals(ticket1, category.getTicket(0));
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class, 
				() -> category.getTicket(3));
		assertEquals("Invalid index.", e1.getMessage());
				
	}
	
	/**
	 * Tests the completeTicket method for AbstractCategory
	 */
	@Test
	public void testCompleteTicket() {
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		
		category.completeTicket(ticket1);
		assertEquals(1, category.getTickets().size());
		assertEquals(ticket2, category.getTicket(0));
		assertEquals(8, category.getCompletedCount());
	}
	
	
}
