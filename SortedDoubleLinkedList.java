
import java.util.ListIterator;
import java.util.Comparator;
import java.lang.UnsupportedOperationException;


/**
 * 
 * @author Philip Song
 * */

@SuppressWarnings("rawtypes")
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	
	private Comparator<T> comparator;
	
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param comparator
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @param comparator
	 */
	public ListIterator<T> iterator(Comparator<T> comparator) {
		return super.iterator();
	}

	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data
	 */
	@SuppressWarnings("unchecked")
	public void add(T data) {
		Node<T> currentNode = head, newNode = new Node(data);
		
		if (head == null)
			head = tail = newNode;
		
		else if (comparator.compare(head.data, data) >= 0)	// head >= data
		{
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
			head.prev = null;
		}
		
		else if (comparator.compare(data, tail.data) > 0)	// data > tail
		{
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = null;
		}
		
		else {	// if head < data < tail
			while (currentNode.next != null && comparator.compare(data,currentNode.data) > 0)
				currentNode = currentNode.next;
			
			currentNode.prev.next = newNode;
			newNode.prev = currentNode.prev;
			currentNode.prev = newNode;
			newNode.next = currentNode;	
			
		}
		
		size++;
	}
	
	@Override
	public Node remove(T targetData, Comparator<T> comparator) {
		return super.remove(targetData, comparator);
	}
	
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException { 
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public void addToFront(T data) throws UnsupportedOperationException { 
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
}
