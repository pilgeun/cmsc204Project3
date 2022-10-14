/**
 * 
 * @author Philip Song
 * */

import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected Node<T> head, tail;
	protected int size = 0;
	
	public BasicDoubleLinkedList() {
		head = tail = null;
		size = 0;
	}
	
	
	public class Node<T>
	{
		protected T data;
		protected Node prev, next;
		
		/**
		 * @param dataNode - data of the 
		 */
		public Node(T dataNode) {
			this(dataNode, null, null);
			//data = dataNode;
		}

		/**
		 * @param dataNode 
		 * @param prevNode
		 * @param nextNode
		 */
		
		public Node(T dataNode, Node prevNode, Node nextNode) {
			data = dataNode;
			prev = prevNode;
			next = nextNode;
		}	
	}
	
	@SuppressWarnings("hiding")
	public class DoubleLinkedListIterator<T> implements ListIterator<T> 
	{	
		protected Node pointer;
		
		/**
		 * Constructor to initialize the current pointer to BEFORE the head of the BasicDoubleLinkedList.
		 * */
		public DoubleLinkedListIterator() {	
			pointer = head.prev;
		}

		@Override
		public boolean hasNext() {
			
			if (pointer == head.prev)
				return true;
			
			return pointer.next != null;
		}

		@Override
		public T next() throws NoSuchElementException {	
			if (pointer == head.prev) 
				pointer = head;
				
			else if ( hasNext() ) 
				pointer = pointer.next;

			else
				throw new NoSuchElementException();
				
			return (T) pointer.data;
		}
		
		@Override
		public boolean hasPrevious() {	
			if (pointer == head.prev)
				return false;
			
			return pointer.prev != null;
		}

		@Override
		public T previous() throws NoSuchElementException {	// probably not the most efficient to have two return statements?
			Node<T> returnNode = pointer;
			
			if ( hasPrevious() || pointer == head) 
				pointer = pointer.prev;

			else
				throw new NoSuchElementException();
			
			return returnNode.data;	
		}
		
		@Override
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}
	
		
	/**
	 * Adds an element to the end of the list and updates the size of the list
	 * @param data
	 */
	public void addToEnd(T data) {
		Node newNode = new Node(data);
		
		if (head == null) {
			head = tail = newNode;
			head.prev = null;
			tail.next = null;
		}
			
		else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = null;
		}
		size++;

	}
	
	/**
	 * Adds element to the front of the list and updates the size of the list
	 * @param data
	 */
	public void addToFront(T data) {
		Node newNode = new Node(data);
		
		if (tail == null) {
			head = tail = newNode;
			head.prev = null;
			tail.next = null;
		}
		
		else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			head.prev = null;
		}
		size++;
	}
	
	/**
	 * Returns but does not remove the first element from the list. 
	 * @return first element, or null if there are no elements
	 */
	public T getFirst() {
		if (size > 0)
			return head.data;
		else 
			return null;
	}
	
	/**
	 * Returns but does not remove the last element from the list. 
	 * @return last element, or null if there are no elements
	 */
	public T getLast() {
		if (size > 0)		
			return tail.data;
		else 
			return null;
	}
	
	/**
	 * @return number of nodes in the list.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * returns an object of the DoubleLinkedListIterator.
	 */
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	

	
	/**
	 * Removes and returns the first element from the list. 
	 * If there are no elements the method returns null. 
	 * @return
	 */
	public T retrieveFirstElement() {
		if (size > 0) {
			T removed = head.data;
			
			head = head.next;
			head.prev = null;
			size--;
			
			return removed;
		}
		else 
			return null;
	}
	
	/**
	 * Removes and returns the last element from the list. 
	 * If there are no elements the method returns null. 
	 * @return
	 */
	public T retrieveLastElement() {
		if (size > 0) {
			T removed = tail.data;
			
			tail = tail.prev;
			tail.next = null;
			size--;
			
			return removed;
		}
		else
			return null;
	}

	/**
	 * Returns an arraylist of all the items in the Double Linked list
	 * @return
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();

		Node<T> node = head;
		
		while(node != null) {
			list.add(node.data);
			node = node.next;
		}
		
		return list;
	}

	/**
	 * Removes the first instance of the targetData from the list. 
	 * @param targetData
	 * @param comparator
	 * @return Node matching targetData, or null if not found
	 */
	public Node remove(T targetData, Comparator<T> comparator) {
		Node<T> currentN = head, previousN = null;
			
		if(comparator.compare(head.data, targetData)==0) {
			head = head.next;
			head.prev = null;
		}
		else if (comparator.compare(tail.data, targetData)==0) 
		{
			currentN = tail;
			tail = tail.prev;
			tail.next = null;
		}
		else {
			while (currentN != null && comparator.compare(currentN.data, targetData)!=0) 
			{
				previousN = currentN;
				currentN = currentN.next;
			}
			
			previousN.next = currentN.next;
			
		}
		
		if (comparator.compare(currentN.data, targetData)==0)
			size--;
		
		return currentN;
	}

	
}
