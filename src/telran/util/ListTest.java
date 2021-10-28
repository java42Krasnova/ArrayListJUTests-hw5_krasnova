package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
	private List<Integer> numbers;
	private List<String> strings;
	Integer initialNumbers[] = { 10, 20, 40 };
	String initialStrings[] = { "name1", "name2" };

	@BeforeEach
	void setUp() throws Exception {
		numbers = getInitialNumbers();

		strings = getInitialStrings ();
		strings = getInitialStrings();
	}
	
	private List<String> getInitialStrings() {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < initialStrings.length; i++) {
			res.add(initialStrings[i]);
		}
		return res;
	}

	private List<Integer> getInitialNumbers() {

		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < initialNumbers.length; i++) {
			res.add(initialNumbers[i]);
		}
		return res;
	}

	@Test

	void testAdd()
	{
		//Integer expInt[] = {10,20,30,40};
		//adds element in position to array
		assertTrue(numbers.add(2, 30));
		assertEquals(4, numbers.size());// checks the size of new array
		
		//String expStr[]= {"newElement", "name1", "name2"};
		//adds new element in first  position to array
		assertTrue(strings.add(0, "newElement"));
		assertEquals(3, strings.size());// checks the size of new array
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
		numbers.add(3, 45);
		System.out.println(numbers.get(2));
		assertEquals(4,numbers.size());
	}
	@Test

	void testGet() {
		assertEquals(10, numbers.get(0));
		assertEquals("name1", strings.get(0));
		assertEquals(null, numbers.get(-1));
		assertEquals(null, numbers.get(3));

	}

	@Test
	void testAddAtIndex() {
		int inserted0 = 100;
		int inserted2 = -8;
		int inserted4 = 1000;
		Integer[] expected = { inserted0, 10, inserted2, 20, 40, inserted4 };
		assertEquals(true, numbers.add(0, inserted0));
		assertEquals(true, numbers.add(2, inserted2));
		assertEquals(true, numbers.add(5, inserted4));
		assertArrayEquals(expected, getArrayFromList(numbers));
		assertEquals(false, numbers.add(7, 1000));
		assertEquals(false, numbers.add(-1, 1000));
	}
	
	@Test
	void testRemove() {
		Integer expected0[] = { 20, 40 };
		Integer expected1[] = { 20 };
		assertEquals(null, numbers.remove(3));
		assertEquals(null, numbers.remove(-1));
		assertEquals(10, numbers.remove(0));
		assertArrayEquals(expected0, getArrayFromList(numbers));
		assertEquals(40, numbers.remove(1));
		assertArrayEquals(expected1, getArrayFromList(numbers));

	}

	@Test
	void testSize() {
		assertEquals(initialNumbers.length, numbers.size());
		numbers.add(100);
		assertEquals(initialNumbers.length + 1, numbers.size());
		numbers.remove(0);
		assertEquals(initialNumbers.length, numbers.size());
	}

	private <T> T[] getArrayFromList(List<T> list) {
		int size = list.size();
		@SuppressWarnings("unchecked")
		T[] res = (T[]) new Object[size];

		for (int i = 0; i < size; i++) {
			res[i] = list.get(i);
		}
		
		return res;
	}

	@Test
	void testConteisNumbers() {
		assertTrue(numbers.contains(initialNumbers[0]));
		assertFalse(numbers.contains(1000));
		numbers.add(1000);
		assertTrue(numbers.contains(1000));
	}

	@Test
	void testConteisStrings() {
		strings.add("Hello");
		String pattern = new String("Hello");
		assertTrue(strings.contains(pattern));
		assertTrue(strings.contains("Hello"));

	}

	@Test
	void containsePredicateNumbersTest() {
		Predicate<Integer> predicate100 = new GreaterNumberPredicate(100);
		Predicate<Integer> predicate25 = new GreaterNumberPredicate(25);
		assertFalse(numbers.contains(predicate100));
		assertTrue(numbers.contains(predicate25));

	}

	@Test
	void containsePredicateStringsTest() {
		Predicate<String> predicateName = new StartWithPredicate("name");
		Predicate<String> predicateMain = new StartWithPredicate("Main");
		assertFalse(strings.contains(predicateMain));
		assertTrue(strings.contains(predicateName));
	}

	@Test
	void testContaisPersons() {
		Person pers = new Person(123, "Moshe");
		Person pers2 = new Person(124, "Vasya");
		List<Person> persons = new ArrayList<>();
		persons.add(pers);
		persons.add(pers2);
		assertTrue(persons.contains(new Person(124, "Vasya")));
		assertTrue(persons.contains(pers));
		assertFalse(persons.contains(new Person(125, "Olya")));

	}

	@Test
	void testIndexOfNumbers() {
		assertEquals(1, numbers.indexOf(20));
		assertEquals(-1, numbers.indexOf(80));
		numbers.add(45);
		assertEquals(3, numbers.indexOf(45));
		numbers.add(2, 33);
		assertEquals(2, numbers.indexOf(33));
		assertEquals(3, numbers.indexOf(40));
		;

	}

	@Test
	void testIndexOfStrings() {
		assertEquals(1, strings.indexOf("name2"));
		assertEquals(-1, strings.indexOf("test"));
		strings.add(1, "name1");
		assertEquals(0, strings.indexOf("name1"));
	}

	@Test
	void testLastIndexOfNumbers() {
		assertEquals(-1, numbers.lastIndexOf(22));
		numbers.add(1, 22);
		numbers.add(2, 22);
		assertEquals(2, numbers.lastIndexOf(22));

	}

	@Test
	void testIndexOfNumbersPredicate() {
		numbers.add(20);
		numbers.add(2, 25);
//10,20,25,40,20
		Predicate<Integer> predNum1 = new GreaterNumberPredicate(20);
		assertEquals(2, numbers.indexOf(predNum1));

	}

	@Test
	void testLastIndexOfStringPredicate() {
		// "name1","name2"
		strings.add("name1");
		strings.add("name1");
		strings.add("name3");
		// "name1","name2","name1","name1","name3"
		Predicate<String> predStr1 = new StartWithPredicate("name1");
		Predicate<String> predStr2 = new StartWithPredicate("name4");
		assertEquals(3, strings.lastIndexOf(predStr1));
		assertEquals(-1, strings.lastIndexOf(predStr2));
	}

	@Test
	void testRemoveIf() {
		// "name1","name2"
		strings.add("name1");
		strings.add("name1");
		strings.add("name3");
		strings.add("name2");
		// "name1","name2","name1","name1","name3","name2"

		Predicate<String> predStr1 = new StartWithPredicate("name2");
		assertTrue(strings.removeIf(predStr1));
		String arrExp[] = { "name1", "name1", "name1", "name3" };
		assertArrayEquals(arrExp, getArrayFromList(strings));
		assertEquals(-1, strings.indexOf(predStr1));
		assertFalse(strings.removeIf(predStr1));
		assertNull(strings.get(5));
		 assertTrue(strings.removeIf(new StartWithPredicate("name")));
		assertArrayEquals(new Integer[0],getArrayFromList(strings)); 
		
	}
	
	
	@Test
	void removeAllTest()
	{
		numbers.add(20);
		List<Integer> otherNumbers = new ArrayList<>();
		otherNumbers.add(20);
		otherNumbers.add(40);
		assertTrue(numbers.removeAll(otherNumbers));
		Integer exp[] = {10};
		assertArrayEquals(exp, getArrayFromList(numbers));
		assertFalse(numbers.removeAll(otherNumbers));
		
	}
	
	@Test
	void removeAllSame()
	{
		assertTrue(numbers.removeAll(numbers));
		assertArrayEquals(new Integer[0], getArrayFromList(numbers));
	}
	@Test
	void testRemovePattern() 
	{
		Integer exp[] = {10,40};
		Integer removeElem = 20;//bug?
		numbers.remove(removeElem);
		assertArrayEquals(exp,getArrayFromList(numbers));		
}
	
	@Test
	void testRetainAll()
	{
		List<String> listToRetail = new ArrayList();
		strings.add("name1");
		strings.add("name1");
		strings.add("name3");
		// "name1","name2","name1","name1","name3"
		listToRetail.add("name1");
		strings.retainAll(listToRetail);
		String exp[] = {"name1", "name1","name1"};
		assertArrayEquals(exp, getArrayFromList(strings));
	}

	@Test 
	void testArraySortComparator()
	{
		List<Integer> arr = new ArrayList<>();
		arr.add(20);
		arr.add(10);
		arr.add(40);
		arr.sort(new ComparatorBeckw());
		Integer exp[] = {40, 20,10};
		for(int i =0; i<arr.size(); i++)
		assertArrayEquals(exp, getArrayFromList(arr));
		List<String> arrStr = new ArrayList<>();
		arrStr.add("x");
		arrStr.add("y");
		arrStr.add("z");
		arrStr.add("a");
		arrStr.sort(new ComparatorBeckwStr());
		String expStr [] = {"z","y","x","a"};
		assertArrayEquals(expStr, getArrayFromList(arrStr));
		
	}
	@Test 
	void testArraySortNatural()
	{
		List<Integer> arr = new ArrayList<>();
		arr.add(70);
		arr.add(10);
		arr.add(20);
		arr.add(9);
		arr.sort();
		Integer exp[] = {9, 10, 20, 70};
		assertArrayEquals(exp, getArrayFromList(arr));
		List<String> arrStr = new ArrayList<>();
		arrStr.add("z");
		arrStr.add("y");
		arrStr.add("x");
		arrStr.sort();
		String expStr [] = {"x","y","z"};
		assertArrayEquals(expStr, getArrayFromList(arrStr));
	}
}
