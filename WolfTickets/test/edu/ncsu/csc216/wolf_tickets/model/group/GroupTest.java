package edu.ncsu.csc216.wolf_tickets.model.group;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.category.ActiveTicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.category.TicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
/**
 * Tests the Group.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class GroupTest {

	/** Group for testing */
	Group group;
	/** ActiveTicketCategory for testing */
	ActiveTicketCategory activeCategory;
	/** TicketCategory used for testing */
	TicketCategory category1;
	/** TicketCategory used for testing */
	TicketCategory category2;
	/** Ticket to be used for testing */
	Ticket ticket1;
	/** Ticket to be used for testing */
	Ticket ticket2;
	
	/**
	 * Sets up object for testing
	 */
	@BeforeEach
	public void setUp() {
		group = new Group("Group1");
		activeCategory = new ActiveTicketCategory();
		category1 = new TicketCategory("Apples", 7);
		category2 = new TicketCategory("Bananas", 7);
		ticket1 = new Ticket("Help me", "I really need help.", "My Wife", true);
		
		ticket2 = new Ticket("Wowza", "So cool.", "", false);
		
	}
	
	/**
	 * Tests the constructor for Group
	 */
	@Test
	public void testConstructor() {
		assertTrue(group.getCurrentCategory() instanceof ActiveTicketCategory);
		assertTrue(group.isChanged());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Group(null));
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Group(""));
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Group(ActiveTicketCategory.ACTIVE_TICKETS_NAME));
		assertTrue(e1 instanceof IllegalArgumentException);
		assertTrue(e2 instanceof IllegalArgumentException);
		assertTrue(e3 instanceof IllegalArgumentException);
		
	}
	
	/**
	 * Tests the getGroupName method for Group
	 */
	@Test
	public void testGetGroupName() {
		assertEquals("Group1", group.getGroupName());
		
	}
	
	/**
	 * Tests the isChanged method for Group
	 */
	@Test
	public void testIsChanged() {
		assertTrue(group.isChanged());
	}
	
	/**
	 * Tests the setChanged method for Group
	 */
	@Test
	public void testSetChanged() {
		group.setChanged(false);
		assertFalse(group.isChanged());
		group.setChanged(true);
		assertTrue(group.isChanged());
	}
	
	/**
	 * Tests the addCategory method for Group
	 */
	@Test
	public void testAddCategory() {
		group.addCategory(category1);
		assertEquals(category1, group.getCurrentCategory());
		assertTrue(group.isChanged());
		String[] array = group.getCategoryNames();
		assertEquals(ActiveTicketCategory.ACTIVE_TICKETS_NAME, array[0]);
		assertEquals(category1.getCategoryName(), array[1]);
		
		
		group.setChanged(false);
		group.addCategory(category2);
		assertEquals(category2, group.getCurrentCategory());
		assertTrue(group.isChanged());
		array = group.getCategoryNames();
		assertEquals(ActiveTicketCategory.ACTIVE_TICKETS_NAME, array[0]);
		assertEquals(category1.getCategoryName(), array[1]);
		assertEquals(category2.getCategoryName(), array[2]);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> group.addCategory(new TicketCategory(ActiveTicketCategory.ACTIVE_TICKETS_NAME, 1)));
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> group.addCategory(category1));
		assertEquals("Invalid name.", e1.getMessage());
		assertEquals("Invalid name.", e2.getMessage());
	}
	
	/**
	 * Tests the editCategory method for Group
	 */
	@Test
	public void testEditCategory() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> group.editCategory("Now"));
		
		group.addCategory(category1);
		group.setChanged(false);
		group.editCategory("Now");
		assertEquals("Now", group.getCurrentCategory().getCategoryName());
		assertTrue(group.isChanged());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> group.editCategory("Now"));
		
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> group.editCategory(ActiveTicketCategory.ACTIVE_TICKETS_NAME));
		
		assertTrue(e1 instanceof IllegalArgumentException);
		assertTrue(e2 instanceof IllegalArgumentException);
		assertTrue(e3 instanceof IllegalArgumentException);
		
	}
	
	/**
	 * Tests the removeCategory method for Group
	 */
	@Test
	public void testRemoveCategory() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> group.removeCategory());
		assertEquals("The Active Tickets list may not be deleted.", e1.getMessage());
		
		group.addCategory(category1);
		group.setChanged(false);
		
		group.removeCategory();
		assertTrue(group.getCurrentCategory() instanceof ActiveTicketCategory);
		assertTrue(group.isChanged());
	}
	
	/**
	 * Tests the getCategoryNames method for Group
	 */
	@Test
	public void testGetCategoryNames() {
		
		group.addCategory(category2);
		
		String[] array = group.getCategoryNames();
		assertEquals(ActiveTicketCategory.ACTIVE_TICKETS_NAME, array[0]);
		assertEquals(category2.getCategoryName(), array[1]);
		
		group.addCategory(category1);
		array = group.getCategoryNames();
		assertEquals(ActiveTicketCategory.ACTIVE_TICKETS_NAME, array[0]);
		assertEquals(category1.getCategoryName(), array[1]);
		assertEquals(category2.getCategoryName(), array[2]);
		
	}
	
	/**
	 * Tests the setCurrentCategory method for Group
	 */
	@Test
	public void testSetCurrentCategory() {
		group.addCategory(category1);
		group.addCategory(category2);
		
		group.setCurrentCategory(category1.getCategoryName());
		assertEquals(category1, group.getCurrentCategory());
		
		group.setCurrentCategory("Wow");
		assertTrue(group.getCurrentCategory() instanceof ActiveTicketCategory);
		
		
	}
	
	/**
	 * Tests the getCurrentCategory method for Group
	 */
	@Test
	public void testGetCurrentCategory() {
		assertTrue(group.getCurrentCategory() instanceof ActiveTicketCategory);
		group.addCategory(category1);
		assertEquals(category1, group.getCurrentCategory());
	}
	
	/**
	 * Tests the addTicket method for Group
	 */
	@Test
	public void testAddTicket() {
		group.setChanged(false);
		
		group.addTicket(ticket1);
		assertEquals(0, group.getCurrentCategory().getTickets().size());
		assertFalse(group.isChanged());
		
		group.addCategory(category1);
		group.addTicket(ticket2);
		assertEquals(1, group.getCurrentCategory().getTickets().size());
		assertEquals(ticket2, group.getCurrentCategory().getTicket(0));
		group.setCurrentCategory("");
		assertEquals(0, group.getCurrentCategory().getTickets().size());
		assertTrue(group.isChanged());
		
		group.setChanged(false);
		
		group.setCurrentCategory(category1.getCategoryName());
		group.addTicket(ticket1);
		assertEquals(2, group.getCurrentCategory().getTickets().size());
		assertEquals(ticket2, group.getCurrentCategory().getTicket(0));
		assertEquals(ticket1, group.getCurrentCategory().getTicket(1));
		group.setCurrentCategory("");
		assertEquals(1, group.getCurrentCategory().getTickets().size());
		assertEquals(ticket1, group.getCurrentCategory().getTicket(0));
		assertTrue(group.isChanged());
		
		
		
	}
	
	/**
	 * Tests the editTicket method for Group
	 */
	@Test
	public void testEditTicket() {
		group.addCategory(category1);
		group.addTicket(ticket1);
		group.setChanged(false);
		group.setCurrentCategory("");
		group.editTicket(0, "Wow", "Huh", "Me", false);
		assertEquals("Help me", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("I really need help.", group.getCurrentCategory().getTicket(0).getTicketDescription());
		assertEquals("My Wife", group.getCurrentCategory().getTicket(0).getOwner());
		assertTrue(group.getCurrentCategory().getTicket(0).isActive());
		assertFalse(group.isChanged());
		
		group.setCurrentCategory(category1.getCategoryName());
		group.editTicket(0, "Wow", "Huh", "Me", false);
		assertEquals("Wow", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("Huh", group.getCurrentCategory().getTicket(0).getTicketDescription());
		assertEquals("Me", group.getCurrentCategory().getTicket(0).getOwner());
		assertFalse(group.getCurrentCategory().getTicket(0).isActive());
		group.setCurrentCategory("");
		assertEquals(0, group.getCurrentCategory().getTickets().size());
		assertTrue(group.isChanged());
		
		group.setChanged(false);
		
		group.setCurrentCategory(category1.getCategoryName());
		group.editTicket(0, "Help me", "I really need help.", "My Wife", true);
		assertEquals("Help me", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("I really need help.", group.getCurrentCategory().getTicket(0).getTicketDescription());
		assertEquals("My Wife", group.getCurrentCategory().getTicket(0).getOwner());
		assertTrue(group.getCurrentCategory().getTicket(0).isActive());
		group.setCurrentCategory("");
		assertEquals(1, group.getCurrentCategory().getTickets().size());
		assertEquals("Help me", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals("I really need help.", group.getCurrentCategory().getTicket(0).getTicketDescription());
		assertEquals("My Wife", group.getCurrentCategory().getTicket(0).getOwner());
		assertTrue(group.getCurrentCategory().getTicket(0).isActive());
		assertTrue(group.isChanged());
		
		
		
	}
	
	
	
	
	
}
