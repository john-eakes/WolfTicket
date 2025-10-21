package edu.ncsu.csc216.wolf_tickets.model.category;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
/**
 * Tests the TicketCategory.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class TicketCategoryTest {

	/** TicketCategory used for testing */
	TicketCategory category1;
	/** TicketCategory used for testing */
	TicketCategory category2;
	/** TicketCategory used for testing */
	TicketCategory category3;
	/** TicketCategory used for testing */
	TicketCategory category4;
	/** Ticket to be used for testing */
	Ticket ticket1;
	/** Ticket to be used for testing */
	Ticket ticket2;
	
	/**
	 * Sets up all the testing objects
	 */
	@BeforeEach
	public void setUp() {
		category1 = new TicketCategory("Apples", 7);
		category2 = new TicketCategory("Bananas", 7);
		category3 = new TicketCategory("Crabs", 7);
		category4 = new TicketCategory("Apples", 1);
		ticket1 = new Ticket("Help me", "I really need help.", "My Wife", true);
		
		ticket2 = new Ticket("Wowza", "So cool.", "", false);
	}
	
	/**
	 * Tests the compareTo method of TicketCategory
	 */
	@Test
	public void testCompareTo() {
		assertEquals(-1, category1.compareTo(category2));
		assertEquals(-1, category1.compareTo(category3));
		assertEquals(1, category2.compareTo(category1));
		assertEquals(-1, category2.compareTo(category3));
		assertEquals(1, category3.compareTo(category1));
		assertEquals(1, category3.compareTo(category2));
		assertEquals(0, category1.compareTo(category4));
	}
	
	/**
	 * Tests the getTicketsAsArray method of TicketCategory
	 */
	@Test
	public void testGetTicketsAsArray() {
		category1.addTicket(ticket1);
		category1.addTicket(ticket2);
		String[][] array = category1.getTicketsAsArray();
		assertEquals(2, array.length);
		assertEquals(3, array[0].length);
		assertEquals("0", array[0][0]);
		assertEquals(ticket1.getOwner(), array[0][1]);
		assertEquals(ticket1.getTicketName(), array[0][2]);
		
		assertEquals("1", array[1][0]);
		assertEquals("", array[1][1]);
		assertEquals(ticket2.getTicketName(), array[1][2]);
		
	}

}
