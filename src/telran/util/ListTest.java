package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
private List<Integer> numbers;
private List<String> strings;
Integer initialNumbers [] = {10, 20, 40};
String initialStrings[] = {"name1", "name2"};

	@BeforeEach
	void setUp() throws Exception {
		numbers = getInitialNumbers();
		strings = getInitialStrings ();
	
	}

	private List<String> getInitialStrings() {
		List<String> res = new ArrayList<>();
		for(int i=0; i<initialStrings.length;i++)
		{
			res.add(initialStrings[i]);
		}
		return res;
	}

	private List<Integer> getInitialNumbers() {
		List<Integer> res = new ArrayList<>();
		for(int i=0; i<initialNumbers.length;i++)
		{
			res.add(initialNumbers[i]);
		}
		return res;
	}

	@Test
	void testAdd()
	{
		//[YG] the tests only for return and size, but no test for real adding a given element at a given index
		//Integer expInt[] = {10,20,30,40};
		//adds element in position to array
		assertTrue(numbers.add(2, 30));
		assertEquals(4, numbers.size());// checks the size of new array
		
		//String expStr[]= {"newElement", "name1", "name2"};
		//adds new element in first  position to array
		assertTrue(strings.add(0, "newElement"));
		assertEquals(3, strings.size());// checks the size of new array
		//[YG] the following your comment about the position is incorrect is incorrect
		//adds new element in position size+1  position to array
		assertTrue(strings.add(3,"test2"));
		assertEquals(4, strings.size());//checks the allocate
		
		//wrong index
		assertFalse(strings.add(-2, "test"));
		assertFalse(numbers.add(6, 6));
	}
	
	@Test 
	void testGetSize()
	{
		assertEquals(3, numbers.size());
		numbers.add(3,4);
		assertEquals(4,numbers.size());
	}
	@Test
	void testGet() {
		assertEquals(10, numbers.get(0));
		assertEquals("name1", strings.get(0));
		assertEquals(null, numbers.get(-1));
		assertNull(numbers.get(4));
		
	}

	@Test
	void tesrRemove ()
	{
		assertEquals(10, numbers.remove(0));
		assertEquals(20, numbers.get(0));
		assertEquals(2, numbers.size());
		assertEquals("name2", strings.remove(1));
		assertEquals(1, strings.size());
		assertEquals(null, numbers.remove(3));
		assertNull(strings.remove(6));
	}
}
