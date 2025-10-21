package edu.ncsu.csc216.wolf_tickets.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import edu.ncsu.csc216.wolf_tickets.model.category.TicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;
/**
 * Tests the GroupWriter.java class, going for a minimum of 80% coverage
 * 
 * @author John Eakes
 * @author Colin Fawcett
 */
class GroupWriterTest {

	/** Expected output file for testing */
	String expFile = "test-files/expected_writer_out.txt";
	/** Group for testing */
	Group group;
	/** Category for testing */
	TicketCategory category;
	/** Ticket for testing */
	Ticket ticket1;
	/** Ticket for testing */
	Ticket ticket2;
	
	/**
	 * Sets up files for testing
	 */
	@BeforeEach
	public void setUp() {
		group = new Group("Group 1");
		category = new TicketCategory("Apples", 7);
		ticket1 = new Ticket("Help me", "I really need help.", "My Wife", true);
		ticket2 = new Ticket("Wowza", "So cool.", "", false);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests a basic correct output
	 */
	@Test
	public void testBasic() {
		ISortedList<TicketCategory> categories = new SortedList<TicketCategory>();
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		categories.add(category);
		
		GroupWriter.writeGroupFile(new File("test-files/actual_writer_out"), "Group 1", categories);
		checkFiles(expFile, "test-files/actual_writer_out");
		
		try {
			Scanner scanner = new Scanner(new File("test-files/actual_writer_out"));
			assertEquals("!", scanner.next());
			scanner.close();
		} catch(Exception e) {
			fail();
		}
		
		
	}
	
	/**
	 * Tests the throws cases for groupWriter
	 */
	@Test
	public void testInvalid() {
		ISortedList<TicketCategory> categories = new SortedList<TicketCategory>();
		category.addTicket(ticket1);
		category.addTicket(ticket2);
		categories.add(category);
		File file = new File("groupFileInvalid.txt");
		assertThrows(IllegalArgumentException.class, () -> GroupWriter.writeGroupFile(null, "name", categories));
		assertThrows(IllegalArgumentException.class, () -> GroupWriter.writeGroupFile(file, null, categories));
		assertThrows(IllegalArgumentException.class, () -> GroupWriter.writeGroupFile(file, "name", null));
	}
	
	

}
