package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tickets.model.category.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
/**
 * Tests the GroupReader.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class GroupReaderTest {

	/** Pathing for a valid test file */
	private static final String VALID_FILE_STRING_1 = "test-files/valid_file.txt";
	/** Pathing for an invalid test file */
	private static final String INVALID_FILE_STRING_1 = "test-files/invalid_start.txt";
	/** Pathing for a valid test file */
	private static final String VALID_FILE_STRING_2 = "test-files/group1.txt";
	/** Pathing for an invalid test file */
	private static final String INVALID_FILE_STRING_2 = "test-files/group7.txt";
	
	/** File for a valid test file */
	File validFile1;
	/** File for an invalid test file */
	File invalidFile1;
	/** File for a valid test file */
	File validFile2;
	/** File for an invalid test file */
	File invalidFile2;
	
	/**
	 * Sets up files for testing
	 */
	@BeforeEach
	public void setUp() {
		validFile1 = new File(VALID_FILE_STRING_1);
		invalidFile1 = new File(INVALID_FILE_STRING_1);
		validFile2 = new File(VALID_FILE_STRING_2);
		invalidFile2 = new File(INVALID_FILE_STRING_2);
		
	}
	
	/**
	 * Tests the valid file for the GroupReader method
	 */
	@Test
	public void testValidFile() {
		assertTrue(validFile1.exists());
		Group group = GroupReader.readGroupFile(validFile1);
		
		assertEquals("CSC IT", group.getGroupName());
		
		String[] categoryArray = group.getCategoryNames();
		
		assertEquals(4, categoryArray.length);
		assertEquals("Active Tickets", categoryArray[0]);
		assertEquals("Classroom Tech", categoryArray[1]);
		assertEquals("Desktop", categoryArray[2]);
		assertEquals("Web", categoryArray[3]);
		
		group.setCurrentCategory("Web");
		AbstractCategory category = group.getCurrentCategory();
		
		assertEquals(5, category.getCompletedCount());
		assertEquals(1, category.getTickets().size());
		assertEquals("Dr. McLeod website pages won't update.", category.getTicket(0).getTicketName());
		assertEquals("I recently uploaded new versions of pages on my website, but the changes don't show up when I go to the URL.", category.getTicket(0).getTicketDescription());
		assertEquals("cmaldon", category.getTicket(0).getOwner());
		assertTrue(category.getTicket(0).isActive());
		
		
		
	}
	/**
	 * Tests the file group1.txt from staff
	 */
	@Test
	public void testGroup1() {
		Group group = GroupReader.readGroupFile(validFile2);
		assertEquals(4, group.getCategoryNames().length);
		
		group.setCurrentCategory("Web");
		ISwapList<Ticket> webTickets = group.getCurrentCategory().getTickets();
		assertEquals(1, webTickets.size());
		assertEquals("Dr. McLeod website pages won't update.", webTickets.get(0).getTicketName());
		
		group.setCurrentCategory("Classroom Tech");
		ISwapList<Ticket> classroomTickets = group.getCurrentCategory().getTickets();
		assertEquals(4, classroomTickets.size());
		assertEquals("EBII 1025 Laptop display won't work", classroomTickets.get(0).getTicketName());
		assertEquals("EBII 1010 Podium monitor won't turn on.", classroomTickets.get(1).getTicketName());
		assertEquals("EBII 1025 Replace lights", classroomTickets.get(2).getTicketName());
		assertEquals("LMP 200 update Firefox", classroomTickets.get(3).getTicketName());
		
		group.setCurrentCategory("Desktop");
		ISwapList<Ticket> desktopTickets = group.getCurrentCategory().getTickets();
		assertEquals(2, desktopTickets.size());
		assertEquals("Dr. McLeod's computer won't charge.", desktopTickets.get(0).getTicketName());
		assertEquals("Microphone not detected through docking station.", desktopTickets.get(1).getTicketName());
		
		group.setCurrentCategory("");
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());
		assertEquals(5, group.getCurrentCategory().getTickets().size());
	}
	
	/**
	 * Tests the file group7.txt from staff
	 */
	@Test
	public void testGroup7() {
		Group group = GroupReader.readGroupFile(invalidFile2);
		
		assertEquals(2, group.getCategoryNames().length);
		
		group.setCurrentCategory("");
		assertEquals("Active Tickets", group.getCurrentCategory().getCategoryName());
		assertEquals(1, group.getCurrentCategory().getTickets().size());
		assertEquals("Adobe Creative", group.getCurrentCategory().getTicket(0).getTicketName());
		assertEquals(0, group.getCurrentCategory().getCompletedCount());
		
	}
	
	/**
	 * Tests file that doesn't exist
	 */
	@Test
	public void testNonexistentFile() {
		File file = new File("this_file_doesnt_exist.txt");
		assertThrows(IllegalArgumentException.class, () -> GroupReader.readGroupFile(file));
		assertThrows(IllegalArgumentException.class, () -> GroupReader.readGroupFile(null));
	}
	
	/**
	 * Tests empty file
	 */
	@Test
	public void testEmptyFile() {
		File file = new File("test-files/empty_file.txt");
		assertThrows(IllegalArgumentException.class, () -> GroupReader.readGroupFile(file));
	}
	

}
