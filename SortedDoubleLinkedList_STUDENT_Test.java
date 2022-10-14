

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.UnsupportedOperationException;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> sortedListString;
	StringComparator comparatorS;
	
	private class StringComparator implements Comparator<String>
	{
		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
	}
	
	@Before
	public void setUp() throws Exception {
		comparatorS = new StringComparator();
		sortedListString = new SortedDoubleLinkedList<String>(comparatorS);
		sortedListString.add("Tom");
		sortedListString.add("Ben");
		sortedListString.add("Jerry");
	}

	@After
	public void tearDown() throws Exception {
		sortedListString = null;
		comparatorS = null;
	}
	
	@Test
	public void testGetSize() {
		assertEquals(3,sortedListString.getSize());
	}
	
	
	@Test
	public void testOrder() {
		ListIterator<String> iterator = sortedListString.iterator();
		sortedListString.add("Next");
		sortedListString.add("Hard");
		assertEquals(5, sortedListString.getSize());
		assertEquals(true, iterator.hasNext());
		assertEquals("Ben", iterator.next());
		assertEquals("Hard", iterator.next());
		assertEquals("Jerry", iterator.next());
		assertEquals("Next", iterator.next());
		assertEquals("Tom", iterator.next());
		assertEquals(false, iterator.hasNext());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Tom", iterator.previous());
		assertEquals("Next", iterator.previous());
		assertEquals("Jerry", iterator.previous());
		assertEquals("Hard", iterator.previous());
		assertEquals("Ben", iterator.previous());
		assertEquals(false, iterator.hasPrevious());
	}
	
	
	
	
	
}
