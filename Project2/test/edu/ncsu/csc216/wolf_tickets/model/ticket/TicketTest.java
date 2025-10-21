package edu.ncsu.csc216.wolf_tickets.model.ticket;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.category.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.category.TicketCategory;
/**
 * Tests the Ticket.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class TicketTest {

	/** Ticket to be used for testing */
	Ticket ticket1;
	/** Ticket to be used for testing */
	Ticket ticket2;
	/** Category to be used for testing */
	AbstractCategory category1;
	/** Category to be used for testing */
	AbstractCategory category2;
	
	/**
	 * Sets up objects for testing
	 */
	@BeforeEach
	public void setUp() {
		ticket1 = new Ticket("Help me", "I really need help.", "My Wife", true);
		category1 = new TicketCategory("John Tickets", 7);
		ticket2 = new Ticket("Wowza", "So cool.", "", false);
		category2 = new TicketCategory("Wow", 8);
	}
	
	/**
	 * Tests constructor methods for Ticket
	 */
	@Test
	public void testConstructor() {
		assertEquals("Help me", ticket1.getTicketName());
		assertEquals("I really need help.", ticket1.getTicketDescription());
		assertEquals("My Wife", ticket1.getOwner());
		assertTrue(ticket1.isActive());
		
		assertEquals("Wowza", ticket2.getTicketName());
		assertEquals("So cool.", ticket2.getTicketDescription());
		assertEquals(null, ticket2.getOwner());
		assertFalse(ticket2.isActive());
		
		
	}
	
	/**
	 * Tests the getTicketName method for Ticket
	 */
	@Test
	public void testGetTicketName() {
		assertEquals("Help me", ticket1.getTicketName());
	}
	
	/**
	 * Tests the setTicketName method for Ticket
	 */
	@Test
	public void testSetTicketName() {
		ticket1.setTicketName("Wow");
		assertEquals("Wow", ticket1.getTicketName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> ticket1.setTicketName(""));
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> ticket1.setTicketName(null));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		assertEquals("Incomplete ticket information.", e2.getMessage());
	}
	
	/**
	 * Tests the getOwner method for Ticket
	 */
	@Test
	public void testGetOwner() {
		assertEquals("My Wife", ticket1.getOwner());
	}
	
	/**
	 * Tests the setOwner method for Ticket
	 */
	@Test
	public void testSetOwner() {
		ticket1.setOwner("Tom");
		assertEquals("Tom", ticket1.getOwner());
		
		ticket1.setOwner("");
		assertEquals(null, ticket1.getOwner());
		ticket1.setOwner(null);
		assertEquals(null, ticket1.getOwner());
	}
	
	/**
	 * Tests the getTicketDescription for Ticket
	 */
	@Test
	public void testGetTicketDescription() {
		assertEquals("I really need help.", ticket1.getTicketDescription());
		
	}
	
	/**
	 * Tests the setTicketDescription method for Ticket
	 */
	@Test
	public void testSetTicketDescription() {
		ticket1.setTicketDescription("Yahoo");
		assertEquals("Yahoo", ticket1.getTicketDescription());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> ticket1.setTicketDescription(null));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		
	}
	
	/**
	 * Tests the isActive method for Ticket
	 */
	@Test
	public void testIsActive() {
		assertTrue(ticket1.isActive());
		assertFalse(ticket2.isActive());
	}
	
	/**
	 * Tests the setActive method for Ticket
	 */
	@Test
	public void testSetActive() {
		ticket1.setActive(false);
		assertFalse(ticket1.isActive());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> ticket2.setActive(true));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		
		ticket2.setOwner("Me");
		ticket2.setActive(true);
		assertTrue(ticket2.isActive());
	}
	
	/**
	 * Tests the getCategoryName method for Ticket
	 */
	@Test
	public void testGetCategoryName() {
		ticket1.addCategory(category1);
		assertEquals(category1.getCategoryName(), ticket1.getCategoryName());
		ticket1.addCategory(category2);
		assertEquals(category1.getCategoryName(), ticket1.getCategoryName());
	}
	
	/**
	 * Tests the addCategory method for Ticket
	 */
	@Test
	public void testAddCategory() {
		ticket1.addCategory(category1);
		assertEquals(category1.getCategoryName(), ticket1.getCategoryName());
		
		ticket1.addCategory(category1);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> ticket1.addCategory(null));
		assertEquals("Incomplete ticket information.", e1.getMessage());
		
	}
	
	/**
	 * Tests the completeTicket method for Ticket
	 */
	@Test
	public void testCompleteTicket() {
		category1.addTicket(ticket1);
		ticket1.completeTicket();
		assertEquals(0, category1.getTickets().size());
		assertEquals(8, category1.getCompletedCount());
	}
	
	/**
	 * Tests the toString method for Ticket
	 */
	@Test
	public void testToString() {
		String ticketString2 = "* Wowza\n" + "So cool.";
		assertEquals(ticketString2, ticket2.toString());
		String ticketString1 = "* Help me,My Wife,active\n" + "I really need help.";
		assertEquals(ticketString1, ticket1.toString());
	}
	
}
