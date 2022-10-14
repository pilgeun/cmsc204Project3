

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.UnsupportedOperationException;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> basicListString;
	BasicDoubleLinkedList<Integer> basicListInt;
	
	StringComparator comparatorS;
	IntegerComparator comparatorI;
	
	private class StringComparator implements Comparator<String>
	{
		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
	}
	
	private class IntegerComparator implements Comparator<Integer>
	{
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0.compareTo(arg1);
		}
	}
	

	@Before
	public void setUp() throws Exception {
		basicListString = new BasicDoubleLinkedList<String>();
		basicListString.addToFront("Tom");
		basicListString.addToFront("Jerry");
		comparatorS = new StringComparator();
		
		basicListInt = new BasicDoubleLinkedList<Integer>();
		basicListInt.addToEnd(13);
		basicListInt.addToEnd(37);
		basicListInt.addToEnd(80085);
		comparatorI = new IntegerComparator();
		
	}

	@After
	public void tearDown() throws Exception {
		basicListString = null;
		basicListInt = null;
		comparatorS = null;
		comparatorI = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,basicListString.getSize());
		assertEquals(3,basicListInt.getSize());

	}
	
	@Test
	public void testAddToEnd() {
		basicListInt.addToEnd(1010);
		basicListInt.addToEnd(123);
		
		assertEquals(123, basicListInt.getLast(), .01);
		assertEquals(5, basicListInt.getSize());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Jerry", basicListString.getFirst());
		basicListString.addToFront("Ben");
		basicListString.addToFront("Next");
		assertEquals("Next", basicListString.getFirst());
		assertEquals("Tom", basicListString.getLast());
	}
	
	@Test
	public void testNext() {
		basicListString.addToFront("Front");
		basicListString.addToEnd("End");
		ListIterator<String> iterator = basicListString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Front", iterator.next());
		assertEquals("Jerry", iterator.next());
		assertEquals("Tom", iterator.next());
		assertEquals("End", iterator.next());
		assertEquals(false, iterator.hasNext());
	}
	
	@Test
	public void testPrevious() {
		basicListInt.addToEnd(1010);
		basicListInt.addToEnd(123);
		ListIterator<Integer> iterator = basicListInt.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(13, iterator.next(), .01);
		assertEquals(37, iterator.next(), .01);
		assertEquals(80085, iterator.next(), .01);
		assertEquals(1010, iterator.next(), .01);
		assertEquals(true, iterator.hasNext());
		
		assertEquals(true, iterator.hasPrevious());
		assertEquals(1010, iterator.previous(), .01);
		assertEquals(80085, iterator.previous(), .01);
		assertEquals(37, iterator.previous(), .01);
	}
	
	
	@Test
	public void UnsupportedOperationException() {	// ListInterator remove not supported
		ListIterator<String> iterator = basicListString.iterator();
		try { 
			iterator.remove();
		} catch (UnsupportedOperationException e){
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
	}

	@Test
	public void testRetrieveFirstElement() {
		
		basicListString.addToFront("New");
		assertEquals("New", basicListString.retrieveFirstElement());
		assertEquals("Jerry", basicListString.retrieveFirstElement());
		assertEquals("Tom",basicListString.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		basicListInt.addToEnd(1111);
		assertEquals(1111, basicListInt.getLast(), .01);
		assertEquals(1111, basicListInt.retrieveLastElement(), .01);
		assertEquals(80085, basicListInt.retrieveLastElement(), .01);
		assertEquals(37, basicListInt.getLast(), .01);
		assertEquals(2, basicListInt.getSize());
	}
	
	@Test
	public void testToArrayList() {
		ArrayList<String> namesList;
		namesList = basicListString.toArrayList();
		assertEquals("Jerry", namesList.get(0));
		assertEquals("Tom", namesList.get(1));
	}

	@Test
	public void testRemove() {
		
		assertEquals(13, basicListInt.getFirst(), .01);
		assertEquals(80085, basicListInt.getLast(), .01);
		
		basicListInt.remove(37, comparatorI);
		basicListInt.remove(13, comparatorI);
		
		assertEquals(80085, basicListInt.getFirst(), .01);		
	}
	
	
}
