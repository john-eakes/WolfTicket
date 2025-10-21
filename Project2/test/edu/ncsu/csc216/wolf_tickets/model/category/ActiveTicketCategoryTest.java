package edu.ncsu.csc216.wolf_tickets.model.category;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
/**
 * Tests the ActiveTicketCategory.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class ActiveTicketCategoryTest {

	/** ActiveTicketCategory for testing */
	ActiveTicketCategory category;
	/** Ticket for testing */
	Ticket ticket1;
	/** Ticket for testing */
	Ticket ticket2;
	/** Ticket for testing */
	Ticket ticket3;
	/** TicketCategory for testing */
	TicketCategory category2;
	
	/**
	 * Sets up objects for testing
	 */
	@BeforeEach
	public void setUp() {
		category = new ActiveTicketCategory();
		ticket1 = new Ticket("Help me", "I really need help.", "My Wife", true);
		ticket2 = new Ticket("Wowza", "So cool.", "", false);
		ticket3 = new Ticket("What", "Please", "John", true);
		category2 = new TicketCategory("Apples", 7);
	}
	
	/**
	 * Tests the constructor for ActiveTicketCategory
	 */
	@Test
	public void testConstructor() {
		assertEquals("Active Tickets", category.getCategoryName());
		assertEquals(0, category.getCompletedCount());
	}
	
	/**
	 * Tests the addTicket method for ActiveTicketCategory
	 */
	@Test
	public void testAddTicket() {
		category.addTicket(ticket1);
		assertEquals(1, category.getTickets().size());
		assertEquals(ticket1, category.getTickets().get(0));
		
		category.addTicket(ticket3);
		assertEquals(2, category.getTickets().size());
		assertEquals(ticket1, category.getTickets().get(0));
		assertEquals(ticket3, category.getTickets().get(1));
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> category.addTicket(ticket2));
		assertEquals("Cannot add ticket to Active Tickets.", e1.getMessage());
		
	}
	
	/**
	 * Tests the setCategoryName method for ActiveTicketCategory
	 */
	@Test
	public void testSetCategoryName() {
		category.setCategoryName("Active Tickets");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> category.setCategoryName("Wow"));
		assertEquals("The Active Tickets list may not be edited.", e1.getMessage());
	}
	
	/**
	 * Tests the getTicketsAsArray method for ActiveTicketCategory
	 */
	@Test
	public void testGetTicketsAsArray() {
		category2.addTicket(ticket1);
		category2.addTicket(ticket3);
		
		category.addTicket(ticket1);
		category.addTicket(ticket3);
		
		String[][] array = category.getTicketsAsArray();
		assertEquals(2, array.length);
		assertEquals(3, array[0].length);
		assertEquals(category2.getCategoryName(), array[0][0]);
		assertEquals(ticket1.getOwner(), array[0][1]);
		assertEquals(ticket1.getTicketName(), array[0][2]);
		assertEquals(category2.getCategoryName(), array[1][0]);
		assertEquals(ticket3.getOwner(), array[1][1]);
		assertEquals(ticket3.getTicketName(), array[1][2]);
		
		
	}
	
	/**
	 * Tests the clearTickets method for ActiveTicketCategory
	 */
	@Test
	public void testClearTickets() {
		category.addTicket(ticket1);
		category.addTicket(ticket3);
		category.clearTickets();
		assertEquals(0, category.getTickets().size());
	}
}
