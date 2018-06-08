/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		assertEquals("check if previous is pointing ", list1.head.next.prev, list1.head);
		
		try {
			list1.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		list1.add(52);
		int checkAnswer =  list1.get(3);
		assertEquals("check Last element of List 1", 52,checkAnswer);
		emptyList.add(12);
		assertEquals("checkSize of empty List ", 1, emptyList.size());
		checkAnswer =  emptyList.get(emptyList.size()-1);
		assertEquals("check Last element of empty List", 12,checkAnswer);
		
		
		
		try {
			list1.add(null);
			fail("Check out of Null Pointer");
		}
		catch (NullPointerException e) {
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("checkSize of empty List ", 0, emptyList.size());
		assertEquals("checkSize of short List ", 2, shortList.size());
		assertEquals("checkSize of Long List ", 10, longerList.size());
		assertEquals("checkSize of list1 List ", 3, list1.size());
		emptyList.add(12);
		assertEquals("checkSize of empty List ", 1, emptyList.size());
		emptyList.add(12);
		assertEquals("checkSize of empty List ", 2, emptyList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		list1.add(1, 19);
		int getValue = list1.get(1);
		assertEquals("check value of List 1 after putting value at index 1 ", 19, getValue);
		list1.add(4, 91);
		getValue = list1.get(4);
		assertEquals("check value of List 1 after putting value at index 3 ", 91, getValue);
		getValue = list1.tail.prev.data;
		assertEquals("check value of List 1 after putting value at index 3 using tail ", 91, getValue);
		list1.add(0, 1);
		getValue = list1.get(0);
		assertEquals("check value of List 1 after putting value at index 0", 1, getValue);
		getValue = list1.head.next.data;
		assertEquals("check value of List 1 after putting value at index 0 using head", 1, getValue);
		
		assertEquals("checkSize of list1 List ", 6, list1.size());
		try {
			list1.add(7, 23);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			list1.add(-1, 90);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			list1.add(4, null);
			fail("Check out of Null Pointer");
		}
		catch (NullPointerException e) {
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		int getVal = list1.set(0, 21);
		assertEquals("Check set value at index 0 ", (Integer)21, list1.get(0));
		assertEquals("Check returned value at index 0 ", 65, getVal);
	    getVal = list1.set(2, 31);
	    assertEquals("Check set value at index 0 ", (Integer)31, list1.get(2));
	    assertEquals("Check returned value at index 0 ", 42, getVal);
	    
	    try {
			list1.set(3, 23);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	    
	    try {
			list1.set(0, null);
			fail("Check out of Null Pointer");
		}
		catch (NullPointerException e) {
		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}
