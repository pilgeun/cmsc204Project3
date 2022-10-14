/**
 * will be returned by the next() method  within the DoubleLinkedListIterator class when there are no more elements in the linked list.
 * @author Philip Song
 * */

@SuppressWarnings("serial")
public class NoSuchElementException extends Exception {
	
	public NoSuchElementException() {
		this("No such element");
	}
	
	public NoSuchElementException(String message) {
		super(message); 
	}

}
