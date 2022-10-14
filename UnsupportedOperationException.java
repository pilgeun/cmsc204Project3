/**
 * will be returned by the addtoFront and addToEnd implementations of the SortedDoubleLinkedList class and by the remove method of the DoubleLinkedListIterator.
 * @author Philip Song
 * */

@SuppressWarnings("serial")
public class UnsupportedOperationException extends Exception {
	
	public UnsupportedOperationException() {
		this("Unsupported Operation");
	}
	
	public UnsupportedOperationException(String message) {
		super(message);
	}
	
}
