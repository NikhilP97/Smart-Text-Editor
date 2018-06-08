package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int varSize;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		this.varSize = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		varSize+=1;
		LLNode<E> newObject = new LLNode<E>(element);
		if(head.next == null) {
			head.next = newObject;
			newObject.prev = head;
		}
		if(tail.prev == null) {
			tail.prev = newObject;
			return true;
		}
		newObject.next = null;
		newObject.prev = tail.prev;
		tail.prev.next = newObject;
		tail.prev = newObject;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index > this.size()-1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp = new LLNode<E>();
		temp = head;
		int counterIndex = 0;
		while(temp.next != null) {
			temp = temp.next;
			if(counterIndex == index) {
				return temp.data;
			}
			counterIndex++;
//			temp = temp.next;
//			if(counterIndex == index) {
//				return temp.data;
//			}
			
		}
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		this.varSize+=1;
		LLNode<E> temp = new LLNode<E>();
		LLNode<E> newObject = new LLNode<E>(element);
		if(head.next == null) {
			head.next = newObject;
			newObject.prev = head;
		}
		if(tail.prev == null) {
			tail.prev = newObject;
		}
		if(index == 0) {
			newObject.prev = head;
			newObject.next = head.next;
			head.next.prev = newObject;
			head.next = newObject;
			return;
		}
		temp = head;
		int counterIndex = 0;
		while(temp.next != null) {
			temp = temp.next;
			if(counterIndex == index) {
				newObject.next = temp;
				newObject.prev = temp.prev;
				temp.prev.next = newObject;
				temp.prev = newObject;
				return;
			}
			counterIndex++;
		}
		if(temp.next == null ) {
			newObject.next = null;
			newObject.prev = temp;
			tail.prev = newObject;
			temp.next = newObject;
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
//		LLNode<E> temp = new LLNode<E>();
//		temp = head;
//		int counterIndex = 1;
//		if(head == null) {
//			return 0;
//		}
//		while(temp.next != null) {
//			counterIndex++;
//			temp = temp.next;
//		}
//		return counterIndex;
		return this.varSize;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		if(index < 0 || index > this.size()-1) {
			throw new IndexOutOfBoundsException();
		}
		varSize-=1;
		LLNode<E> temp = new LLNode<E>();
		temp = head.next;
		if(index == 0) {
			head.next = temp.next;
			temp.next.prev = head;
			return temp.data;
		}
		temp = head;
		int counterIndex = 0;
		while(temp.next != null) {
			temp = temp.next;
			if(counterIndex == index) {
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				return temp.data;
			}
			counterIndex++;
		}
		
		temp.prev.next = null;
		tail.prev = temp.prev;
		return temp.data;
		
		
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > this.size()-1) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> temp = new LLNode<E>();
		if(index == 0) {
			temp.data = head.next.data;
			head.next.data = element;
			return temp.data;
		}
		LLNode<E> storeValue = new LLNode<E>();
		temp = head;
		int counterIndex = 0;
		while(temp.next != null) {
			temp = temp.next;
			if(counterIndex == index) {
				storeValue.data = temp.data;
				temp.data = element;
				return storeValue.data;
			}
			counterIndex++;
		}
		
		storeValue.data = temp.data;
		temp.data = element;
		return storeValue.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode() {
		// TODO Auto-generated constructor stub
		this.prev = null;
		this.next = null;	
		this.data = null;
	}

}
