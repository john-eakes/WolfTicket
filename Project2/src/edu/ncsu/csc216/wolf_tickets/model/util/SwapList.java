package edu.ncsu.csc216.wolf_tickets.model.util;

import java.util.Arrays;

/**
 * A list that changes the position of elements through
 * swap operations. 
 * 
 * @author John Eakes
 * @author Colin Fawcett
 * 
 * @param <E> type for SwapList
 */
public class SwapList<E> implements ISwapList<E> {
	/** The static constant for the initial list capacity of 10 */
	private static final int INITIAL_CAPACITY = 10;
	/** The list variable of type E[] */
	private E[] list;
	/** The current size of the list */
	private int size;
	/**
	 * Constructs the list
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		checkCapacity(size + 1);
		list[size] = element;
		size++;
	}
	/**
	 * Checks the capacity of the list for the add method
	 * @param capacity the capacity that is being checked
	 */
	private void checkCapacity(int capacity) {
		if(capacity > list.length) {
			list = Arrays.copyOf(list, list.length * 2);
		}
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
		E removed = list[idx];
		for(int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return removed;
	}
	/**
	 * Checks the index of the element that's meant to be removed
	 * @param idx the index that's being checked for removal
	 */
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx);
		if(idx > 0) {
			E temp = list[idx];
			list[idx] = list[idx - 1];
			list[idx - 1] = temp;
		}
	}
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		if(idx < size - 1) {
			E temp = list[idx];
			list[idx] = list[idx + 1];
			list[idx + 1] = temp;
		}
	}
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		E temp = list[idx];
		for(int i = idx; i > 0; i--) {
			list[i] = list[i - 1];
		}
		list[0] = temp;
	}
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		E temp = list[idx];
		for(int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = temp;
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
		return list[idx];
	}
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
}
