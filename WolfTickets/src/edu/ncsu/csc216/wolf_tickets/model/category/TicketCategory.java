package edu.ncsu.csc216.wolf_tickets.model.category;

import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * The TicketCategory class holds a list of tickets that fall under the name of the TicketCategory and allows for operations upon that list
 * 
 * @author Colin Fawcett
 * @author John Eakes
 */
public class TicketCategory extends AbstractCategory implements Comparable<TicketCategory> {
	
	/**
	 * Constructor for the TicketCategory class
	 * 
	 * @param categoryName the desired name of the category
	 * @param completedCount the number of completed tickets in the category
	 */
	public TicketCategory(String categoryName, int completedCount) {
		super(categoryName, completedCount);
	}

	/**
	 * Creates a string array of the current category and its list of tickets
	 * 
	 * @return categoryArray the string array of the category data and the data of its tickets
	 */
	public String[][] getTicketsAsArray() {
		ISwapList<Ticket> tickets = this.getTickets();
		String[][] categoryArray = new String[tickets.size()][3];
		for(int i = 0; i < tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			String owner = ticket.getOwner() == null ? "" : ticket.getOwner();
			categoryArray[i][0] = String.valueOf(i);
			categoryArray[i][1] = owner;
			categoryArray[i][2] = ticket.getTicketName();
		}
		return categoryArray;
	}
	/**
	 * Case insensitive comparison of the names of (ideally) TicketCategory objects, as compared to the original compareTo method
	 * 
	 * @param o the object being compared
	 */
	@Override
	public int compareTo(TicketCategory o) {
		int fin = this.getCategoryName().compareToIgnoreCase(o.getCategoryName());
		
		if(fin < 0) {
			fin = -1;
		}
		if(fin > 0) {
			fin = 1;
		}
		return fin;
		
		
	}

}
