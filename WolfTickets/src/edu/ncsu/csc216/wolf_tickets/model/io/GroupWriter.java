package edu.ncsu.csc216.wolf_tickets.model.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import edu.ncsu.csc216.wolf_tickets.model.category.TicketCategory;
import edu.ncsu.csc216.wolf_tickets.model.util.ISortedList;

/**
 * The GroupWriter class takes the data of the current Group and outputs it to a file
 * 
 * @author Colin Fawcett
 * @author John Eakes
 */
public class GroupWriter {

	/**
	 * Takes a filepath, name of the group, and the list of categories it has and writes the data to a file
	 * 
	 * @param groupFile the file path of the file to be created
	 * @param groupName the name of the group that is being written to a file
	 * @param categories the list of categories the group has
	 * @throws IllegalArgumentException if there are any errors or exceptions thrown during the process
	 */
	public static void writeGroupFile(File groupFile, String groupName, ISortedList<TicketCategory> categories) {
		if(groupFile == null || groupName == null || categories == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(groupFile));
			writer.println("! " + groupName);
			for(int i = 0; i < categories.size(); i++) {
				TicketCategory category = categories.get(i);
				writer.println("# " + category.getCategoryName() + "," + category.getCompletedCount());
				for(int j = 0; j < category.getTickets().size(); j++) {
					writer.println(category.getTickets().get(j).toString());
				}
			}
			writer.close();
		} catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
