package edu.ncsu.csc216.wolf_tickets.model.ticket;

import edu.ncsu.csc216.wolf_tickets.model.category.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The ticket class holds information about each individual ticket, such as its name, owner, description, and whether
 * or not it's active
 * @author John Eakes
 * @author Colin Fawcett
 * 
 */
public class Ticket {
	/** The ticket's name */
	private String ticketName;
	/** The ticket's owner */
	private String owner;
	/** The ticket's description */
	private String ticketDescription;
	/** The ticket's active state */
	private boolean active;
	/** The categories that the tickets could be a part of */
	private ISwapList<AbstractCategory> categories;
	
	/**
	 * Constructs a ticket with the given parameters
	 * @param ticketName the ticket's name
	 * @param ticketDescription the ticket's description
	 * @param owner the ticket's owner
	 * @param active whether or not the ticket is active
	 */
	public Ticket(String ticketName, String ticketDescription, String owner, boolean active) {
		categories = new SwapList<>();
		setTicketName(ticketName);
		setTicketDescription(ticketDescription);
		setOwner(owner);
		setActive(active);
	}
	/**
	 * Returns the ticket's name
	 * @return the name of the ticket
	 */
	public String getTicketName() {
		return ticketName;
	}
	/**
	 * Sets the ticket's name to a specified name
	 * @param ticketName the name that the ticket is being set to
	 * @throws IllegalArgumentException if ticketName is null or empty string
	 */
	public void setTicketName(String ticketName) {
		if(ticketName == null || "".equals(ticketName)) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketName = ticketName;
	}
	/**
	 * Returns the tickets owner
	 * @return the owner of the ticket
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * Sets the ticket's owner to a specified owner. If owner variable is not a string with at least
	 * one character, set owner to null
	 * @param owner the person who the ticket belongs to
	 */
	public void setOwner(String owner) {
		if(owner == null || "".equals(owner)) {
			this.owner = null;
		}
		else {
			this.owner = owner;
		}
	}
	/**
	 * Returns the ticket's description
	 * @return the description of a ticket
	 */
	public String getTicketDescription() {
		return ticketDescription;
	}
	/**
	 * Sets the ticket's description to the parameter description
	 * @param ticketDescription the new description
	 * @throws IllegalArgumentException if description is null
	 */
	public void setTicketDescription(String ticketDescription) {
		if(ticketDescription == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.ticketDescription = ticketDescription;
	}
	/**
	 * returns true if ticket is active and false if not
	 * @return the boolean representing the ticket's active status
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * changes active to true if the parameter active is true and the ticket has an owner
	 * @param active the ticket's new active status
	 * @throws IllegalArgumentException if active is true and ticket has no owner
	 */
	public void setActive(boolean active) {
		if(active && (owner == null || "".equals(owner))) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		this.active = active;
	}
	/**
	 * Returns the category name
	 * @return the name of the current category
	 */
	public String getCategoryName() {
		if(categories == null || "".equals(categories.get(0).getCategoryName())) {
			return "";
		}
		return categories.get(0).getCategoryName();
	}
	/**
	 * Adds a category to the end of the categories list, if category is not already registered with AbstractCategory
	 * @param category the category that's being added
	 * @throws IllegalArgumentException if category parameter is null
	 */
	public void addCategory(AbstractCategory category) {
		if(category == null) {
			throw new IllegalArgumentException("Incomplete ticket information.");
		}
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).equals(category)) {
				return;
			}
		}
		categories.add(category);
	}
	/**
	 * Completes the ticket and notifies the categories by sharing the ticket via the 
	 * Category.completeTicket(ticket) method
	 */
	public void completeTicket() {
		for(int i = 0; i < categories.size(); i++) {
			categories.get(i).completeTicket(this);
		}
	}
	/**
	 * Returns the string format of a ticket
	 * @return the string format of a ticket
	 */
	public String toString() {
		if(owner != null && active) {
			return "* " + ticketName + "," + owner + "," + "active\n" + ticketDescription;
		}
		if(owner != null && !active) {
			return "* " + ticketName + "," + owner + "\n" + ticketDescription;
		}
		return "* " + ticketName + "\n" + ticketDescription;
		
		
	}
}
