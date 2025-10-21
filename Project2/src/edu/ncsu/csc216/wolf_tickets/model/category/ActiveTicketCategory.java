package edu.ncsu.csc216.wolf_tickets.model.category;

import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;

/**
 * The ActiveTicketCategory class holds a list of the active tickets and allows for operations upon that list
 * 
 * @author Colin Fawcett
 * @author John Eakes
 */
public class ActiveTicketCategory extends AbstractCategory {

	/** The name of the ActiveTicketCategory */
	public static final String ACTIVE_TICKETS_NAME = "Active Tickets";
	
	/**
	 * Constructor for the ActiveticketCategory class
	 */
	public ActiveTicketCategory() {
		super(ACTIVE_TICKETS_NAME, 0);
	}
	
	/**
	 * Adds the specified ticket to the list of tickets the ActiveTicketCategory holds
	 * 
	 * @param t the Ticket to be added to the list of Tickets
	 * @throws IllegalArgumentException if the Ticket is not active
	 */
	@Override
	public void addTicket(Ticket t) {
		if(!t.isActive()) {
			throw new IllegalArgumentException("Cannot add ticket to Active Tickets.");
		}
		super.addTicket(t);
	}
	
	/**
	 * Sets the ActiveTicketCategory name to "Active Tickets", overridden because the name should only be "Active Tickets"
	 * 
	 * @param categoryName the desired name of the category
	 * @throws IllegalArgumentException if the name does not match ACTIVE_TICKETS_NAME
	 */
	@Override
	public void setCategoryName(String categoryName) {
		if(!ACTIVE_TICKETS_NAME.equals(categoryName)) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		super.setCategoryName(categoryName);
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
			
			categoryArray[i][0] = ticket.getCategoryName();
			categoryArray[i][1] = owner;
			categoryArray[i][2] = ticket.getTicketName();
		}
		return categoryArray;
	}
	
	/**
	 * Clears all of the tickets from the AbstractTicketCategory's list of tickets
	 */
	public void clearTickets() {
		ISwapList<Ticket> tickets = this.getTickets();
		for(int i = tickets.size() - 1; i >= 0; i--) {
			tickets.remove(i);
		}
	}
}
