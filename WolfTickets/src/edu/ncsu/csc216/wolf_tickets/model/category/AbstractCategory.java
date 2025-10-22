package edu.ncsu.csc216.wolf_tickets.model.category;



import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tickets.model.util.SwapList;

/**
 * The AbstractCategory class defines base behavior for both the ActiveTicketCategory and the TicketCategory classes
 * 
 * @author Colin Fawcett
 * @author John Eakes
 */
public abstract class AbstractCategory {
	
	/** Name of the category */
	private String categoryName;
	/** Number of completed tickets */
	private int completedCount;
	/** List of the tickets of the category */
	private ISwapList<Ticket> tickets;
	
	/**
	 * Constructor for the AbstractCategory class
	 * 
	 * @param categoryName the desired name of the category
	 * @param completedCount the number of completed tickets of the category
	 * @throws IllegalArgumentException if the categoryName is null or empty
	 * @throws IllegalArgumentException if the completedCount is less than zero
	 */
	public AbstractCategory(String categoryName, int completedCount) {
		setCategoryName(categoryName);
		if(completedCount < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		this.categoryName = categoryName;
		this.completedCount = completedCount;
		this.tickets = new SwapList<>();
	}
	
	/**
	 * Getter for the name of the category
	 * 
	 * @return categoryName the name of the category
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Setter for the name of the category
	 * 
	 * @param categoryName the desired name of the category
	 * @throws IllegalArgumentException if the categoryName is null or empty
	 */
	public void setCategoryName(String categoryName) {
		if(categoryName == null || "".equals(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.categoryName = categoryName;
	}
	
	/**
	 * Getter for the list of tickets in the category
	 * 
	 * @return tickets the list of tickets in the category
	 */
	public ISwapList<Ticket> getTickets() {
		return this.tickets;
	}
	
	/**
	 * Getter for the number of completed tickets in the category
	 * 
	 * @return completedCount the number of completed tickets in the category
	 */
	public int getCompletedCount() {
		return this.completedCount;
	}
	
	/**
	 * Adds a ticket to the list of tickets in the category
	 * 
	 * @param t the ticket to be added to the list
	 */
	public void addTicket(Ticket t) {
		tickets.add(t);
		t.addCategory(this);
	}
	
	/**
	 * Removes a ticket from the list of tickets in the category
	 *
	 * @param idx the index of the ticket in the list of tickets to be removed
	 * @return ticket the removed ticket
	 */
	public Ticket removeTicket(int idx) {
		Ticket removed = tickets.remove(idx);
		return removed;
	}
	
	/**
	 * Gets a specific ticket from the list of tickets in the category
	 * 
	 * @param idx the index of the ticket in the list of tickets to be returned
	 * @return ticket the returned ticket
	 */
	public Ticket getTicket(int idx) {
		return tickets.get(idx);
	}
	
	/**
	 * Marks a ticket as completed and updates the completedCount accordingly
	 * 
	 * @param t the ticket to be completed
	 */
	public void completeTicket(Ticket t) {
		int idx = -1;
		for(int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i) == t){
				idx = i;
				break;
			}
		}
		if(idx >= 0) {
			tickets.remove(idx);
			completedCount++;
		} else {
			throw new IllegalArgumentException("Ticket not found in category");
		}
	}
	
	/**
	 * Creates a string array of the current category and its list of tickets
	 * 
	 * @return categoryArray the string array of the category data and the data of its tickets
	 */
	public abstract String[][] getTicketsAsArray();
	
}
