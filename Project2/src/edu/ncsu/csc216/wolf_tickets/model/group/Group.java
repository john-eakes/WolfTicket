package edu.ncsu.csc216.wolf_tickets.model.group;

import java.io.File;

import edu.ncsu.csc216.wolf_tickets.model.category.AbstractCategory;
import edu.ncsu.csc216.wolf_tickets.model.category.ActiveTicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.category.TicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.io.GroupWriter;
import edu.ncsu.csc216.wolf_tickets.model.ticket.Ticket;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tickets.model.util.SortedList;

/**
 * The Group class has information regarding the current group of Tickets and all of the Category's those tickets belong to
 * 
 * @author Colin Fawcett
 * @author John Eakes
 */
public class Group {

	/** The name of the group */
	private String groupName;
	/** Tracks if the group was changed since last saved */
	private boolean isChanged;
	/** The current category */
	private AbstractCategory currentCategory;
	/** The active tickets category */
	private ActiveTicketCategory activeTicketCategory;
	/** The list of categories */
	private ISortedList<TicketCategory> categories;
	
	/**
	 * Constructs a new Group
	 * 
	 * @param groupName the name of the group
	 * @throws IllegalArgumentException if groupName is null, empty, or matches ACTIVE_TASKS_NAME from activeTicketCategory
	 */
	public Group(String groupName) {
		if(groupName == null || "".equals(groupName) || ActiveTicketCategory.ACTIVE_TICKETS_NAME.equals(groupName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		setGroupName(groupName);
		this.categories = new SortedList<>();
		this.activeTicketCategory = new ActiveTicketCategory();
		this.currentCategory = this.activeTicketCategory;
		this.isChanged = true;
	}
	
	/**
	 * Saves the current group to the specified file location
	 * 
	 * @param groupFile the location of the file to save the data to
	 */
	public void saveGroup(File groupFile) {
		GroupWriter.writeGroupFile(groupFile, groupName, categories);
		this.isChanged = false;
	}
	
	/**
	 * Getter for the name of the Group
	 * 
	 * @return groupName the name of the group
	 */
	public String getGroupName() {
		return this.groupName;
	}
	
	/**
	 * Setter for the name of the Group
	 * 
	 * @param groupName the desired name of the Group
	 */
	private void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * Getter for the status of if the Group has been changed
	 * 
	 * @return isChanged the value of if the Group was changed
	 */
	public boolean isChanged() {
		return this.isChanged;
	}
	
	/**
	 * Setter for the status of if the Group has been changed
	 * 
	 * @param changed the desired status of the isChanged field
	 */
	public void setChanged(boolean changed) {
		this.isChanged = changed;
	}
	
	/**
	 * Adds the specified category to the list of categories
	 * 
	 * @param category the category to be added
	 * @throws IllegalArgumentException if the categoryName is a duplicate name or matches ACTIVE_TASKS_NAME from activeTicketCategory
	 */
	public void addCategory(TicketCategory category) {
		if(category.getCategoryName().equalsIgnoreCase("Active Tickets")) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < categories.size(); i++) {
			if(category.getCategoryName().equalsIgnoreCase(categories.get(i).getCategoryName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		categories.add(category);
		getActiveTicketCategory();
		this.currentCategory = category;
		this.isChanged = true;
	}
	
	/**
	 * Edits the currentCategory to have the new specified name
	 * 
	 * @param categoryName the new name of the currentCategory
	 * @throws IllegalArgumentException if the currentCategory is activeTicketCategory
	 * @throws IllegalArgumentException if the categoryName matches ACTIVE_TASKS_NAME from activeTicketCategory or is a duplicate name
	 */
	public void editCategory(String categoryName) {
		if(currentCategory instanceof ActiveTicketCategory) {
			throw new IllegalArgumentException("The Active Tickets list may not be edited.");
		}
		if("Active Tickets".equalsIgnoreCase(categoryName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < categories.size(); i++) {
			if(categoryName.equalsIgnoreCase(categories.get(i).getCategoryName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		int idx = -1;
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).equals(currentCategory)) {
				idx = i;
				break;
			}
		}
		
		if(idx >= 0) {
			categories.remove(idx);
			currentCategory.setCategoryName(categoryName);
			categories.add((TicketCategory) currentCategory);
			this.isChanged = true;
		}
		
	}
	
	/**
	 * Removes the currentCategory from the list of categories
	 * 
	 * @throws IllegalArgumentException if the currentCategory is the activeTicketCategory
	 */
	public void removeCategory() {
		if(currentCategory instanceof ActiveTicketCategory) {
			throw new IllegalArgumentException("The Active Tickets list may not be deleted.");
		}
		int idx = -1;
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).equals(currentCategory)) {
				idx = i;
				break;
			}
		}
		
		if(idx >= 0) {
			categories.remove(idx);
			currentCategory = activeTicketCategory;
			this.isChanged = true;
		}
		getActiveTicketCategory();
	}
	
	/**
	 * Returns a string array of the names of the Group and the categories it owns
	 * 
	 * @return categoryArray the names of the categories
	 */
	public String[] getCategoryNames() {
		String[] categoryArray = new String[categories.size() + 1];
		categoryArray[0] = activeTicketCategory.getCategoryName();
		for(int i = 0; i < categories.size(); i++) {
			categoryArray[i + 1] = categories.get(i).getCategoryName();
		}
		return categoryArray;
	}
	
	/**
	 * Sets the currentCategory to the specified name, or else the activeTicketCategory if a category is not found
	 * 
	 * @param categoryName the name of the category to be set to the currentCategory
	 */
	public void setCurrentCategory(String categoryName) {
		if("Active Tickets".equalsIgnoreCase(categoryName)) {
			currentCategory = activeTicketCategory;
			return;
		}
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getCategoryName().equalsIgnoreCase(categoryName)) {
				currentCategory = categories.get(i);
				return;
			}
		}
		currentCategory = activeTicketCategory;
	}
	
	/**
	 * Getter for the currentCategory field of the Group
	 * 
	 * @return currentCategory the currentCategory of the Group
	 */
	public AbstractCategory getCurrentCategory() {
		return this.currentCategory;
	}
	
	/**
	 * Adds the specified Ticket to the currentCategory
	 * 
	 * @param t the ticket to be added
	 */
	public void addTicket(Ticket t) {
		if(currentCategory instanceof TicketCategory) {
			currentCategory.addTicket(t);
            if(t.isActive()) {
            	getActiveTicketCategory();
            	
            }
            this.isChanged = true;
		}
	}
	
	/**
	 * Edits the specified index to contain the new desired information
	 * 
	 * @param idx the index of the ticket to be changed
	 * @param ticketName the desired name of the ticket
	 * @param ticketDescription the desired description of the ticket
	 * @param owner the owner of the ticket
	 * @param active the desired status of the ticket
	 */
	public void editTicket(int idx, String ticketName, String ticketDescription, String owner, boolean active) {
		if(currentCategory instanceof TicketCategory) {
			TicketCategory category = (TicketCategory) currentCategory;
			Ticket ticket = category.getTicket(idx);
			if(ticket.isActive() == active) {
				ticket.setTicketName(ticketName);
				ticket.setTicketDescription(ticketDescription);
				ticket.setOwner(owner);
			}
			else  {
				ticket.setTicketName(ticketName);
				ticket.setTicketDescription(ticketDescription);
				ticket.setOwner(owner);
				ticket.setActive(active);
				
				getActiveTicketCategory();
				
			}
			
			
			isChanged = true;
		}
	}
	
	/**
	 * Resets the activeTicketCategory
	 */
	private void getActiveTicketCategory() {
		
		activeTicketCategory.clearTickets();
		
		for(int i = 0; i < categories.size(); i++) {
			for(int j = 0; j < categories.get(i).getTickets().size(); j++) {
				if(categories.get(i).getTickets().get(j).isActive()) {
					activeTicketCategory.addTicket(categories.get(i).getTicket(j));
				}
			}
		}
		
	}
}
