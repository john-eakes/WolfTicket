package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


import edu.ncsu.csc216.wolf_tickets.model.category.TicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.group.Group;
import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;

/**
 * The GroupReader class reads in file input related to a Group object
 * 
 * @author Colin Fawcett
 * @author John Eakes
 */
public class GroupReader {

	/**
	 * Reads the data of a Group from the specified file path
	 * 
	 * @param groupFile the file path of the file containing the Group information
	 * @return group the newly made group from the file data
	 * @throws IllegalArgumentException if the file doesn't exist
	 * @throws IllegalArgumentException if the file does not begin with "!"
	 */
	public static Group readGroupFile(File groupFile) {
		if(groupFile == null || !groupFile.exists()) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		try {
			Scanner scanner = new Scanner(groupFile);
			if(!scanner.hasNext()) {
				scanner.close();
				throw new IllegalArgumentException("Unable to load file.");
			}
			String firstLine = scanner.nextLine().trim();
			if (!firstLine.startsWith("!")) {
				scanner.close();
                throw new IllegalArgumentException("Unable to load file.");
            }
			String groupName = firstLine.substring(1).trim();
			Group group = new Group(groupName);
			
			String fileContents = firstLine + "\n";
			while(scanner.hasNextLine()) {
				fileContents += scanner.nextLine() + "\n";
			}
			scanner.close();
			
			Scanner scanner2 = new Scanner(fileContents);
			scanner2.useDelimiter("\\r?\\n?[#]");
			scanner2.next();
			
			while(scanner2.hasNext()) {
				String categoryText = scanner2.next().trim();
				TicketCategory category = processCategory(categoryText);
				if(category != null) {
					group.addCategory(category);
				}
			}
			scanner2.close();
			
			group.setCurrentCategory("");
			
			return group;
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	/**
	 * Helper method for creating the AbstractCategorys of the Group
	 * 
	 * @param categoryText the line of text for the AbstractCategory
	 * @return category the newly made category from the categoryText
	 */
	private static TicketCategory processCategory(String categoryText) {
		Scanner scanner = new Scanner(categoryText);
		
		
		if(!scanner.hasNext()) {
			scanner.close();
			return null;
		}
		
		String categoryHeader = scanner.nextLine();
		Scanner headerScanner = new Scanner(categoryHeader);
		headerScanner.useDelimiter(",");
		
		if(!headerScanner.hasNext()) {
			headerScanner.close();
			scanner.close();
			return null;
		}
		
		String categoryName = headerScanner.next().trim();
		int completedTickets = headerScanner.hasNextInt() ? headerScanner.nextInt() : -1;
		headerScanner.close();
		
		if (completedTickets < 0) {
			scanner.close();
			return null;
		}
		
		TicketCategory category = new TicketCategory(categoryName, completedTickets);
		
		
		
		
		
		scanner.useDelimiter("\\r?\\n?[*]");
		
		while(scanner.hasNext()) {
			String ticketData = scanner.next();
			
			Ticket ticket = processTicket(ticketData);
			if(ticket != null) {
				category.addTicket(ticket);
			}
		}
		
		
		scanner.close();
		
		
		
		
		
		
		
		
		
		return category;
	}
	
	/**
	 * Helper method for creating the Tickets of the Group
	 * 
	 * @param ticketText the line of text for the Ticket
	 * @return ticket the newly made ticket from the ticketText
	 */
	private static Ticket processTicket(String ticketText) {
		String newTicketText = ticketText.trim();
		
		Scanner scanner = new Scanner(newTicketText);
		
		try {
			String ticketInfo = scanner.nextLine();
			String ticketDesc = scanner.nextLine();
			Scanner scanner2 = new Scanner(ticketInfo);
			
			scanner2.useDelimiter(",");
			
			String ticketName = scanner2.next();
			if(!scanner2.hasNext()) {
				Ticket ticket = new Ticket(ticketName, ticketDesc, "", false);
				scanner2.close();
				return ticket;
			}
			String ticketOwner = scanner2.next();
			if(!scanner2.hasNext()) {
				Ticket ticket = new Ticket(ticketName, ticketDesc, ticketOwner, false);
				scanner2.close();
				return ticket;
			}
			String ticketStatus = scanner2.next().trim();
			if(!"active".equals(ticketStatus)) {
				scanner2.close();
				return null;
			}
			if(scanner2.hasNext()) {
				scanner2.close();
				return null;
			}
			scanner2.close();
			Ticket ticket = new Ticket(ticketName, ticketDesc, ticketOwner, true);
			return ticket;
		} catch (Exception e) {
			//Do nothing
		}
		
		
		scanner.close();
		
		
		
		return null;
		
		
	}
	
}
