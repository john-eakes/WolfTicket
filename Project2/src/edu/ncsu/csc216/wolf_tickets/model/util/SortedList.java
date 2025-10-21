package edu.ncsu.csc216.wolf_tickets.model.util;
/**
 * a list that keeps objects in sorted order as defined by the Comparable interface.
 * 
 * @author John Eakes
 * @author Colin Fawcett
 * 
 * @param <E> the type of SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** The size of the sortedList */
	private int size;
	/** The front of the LinkedList */
	private ListNode front;
	/**
	 * Constructs a sortedList
	 */
	public SortedList() {
		front = null;
		size = 0;
	}
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if(contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		if(front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			while(current.next != null && element.compareTo(current.next.data) >= 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}
	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E removed;
		if(idx == 0) {
			removed = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			removed = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return removed;
	}
	/**
	 * Checks the index of the element that is meant to be removed
	 * @param idx the index being checked for removal
	 */
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		ListNode current = front;
		while(current != null) {
			if(current.data.equals(element)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
		for(int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	

	private class ListNode {
		/** The data value in a specific linkedList position */
		public E data;
		/** The next value in the linkedList */
		public ListNode next;
		/**
		 * Constructs a ListNode with data and next values
		 * @param data the data held by the list node
		 * @param next the list node after this one
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
