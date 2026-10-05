/*
 * Copyright 2025 Marc Liberatore.
 */

package lists;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


// @Timeout(10)
public class ArrayListIteratorTest {

	@Test
	public void testArrayListEmptyIterator() {
		List<String> l = new ArrayList<>();
		Iterator<String> i = l.iterator();
		assertFalse(i.hasNext());
	}

	@Test
	public void testArrayListIteratorOne() {
		List<String> l = new ArrayList<>();
		l.add("one");
		Iterator<String> i = l.iterator();

		assertTrue(i.hasNext());
		assertEquals("one", i.next());

		assertFalse(i.hasNext());
	}

	@Test
	public void testArrayListIteratorTwo() {
		List<String> l = new ArrayList<>();
		l.add("one");
		l.add("two");
		Iterator<String> i = l.iterator();

		assertTrue(i.hasNext());
		assertEquals("one", i.next());

		assertTrue(i.hasNext());
		assertEquals("two", i.next());

		assertFalse(i.hasNext());
	}

	@Test
	public void testArrayListEmptyIteratorForEach() {
		List<String> l = new ArrayList<>();
		for (String s : l) {
			fail(); // should never get here, as the list is empty!
		}
	}

	@Test
	public void testArrayListForEachTwo() {
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);

		Integer c = 1;
		for (Integer i : l) {
			assertEquals(c, i);
			c += 1;
		}
	}

	@Test
	public void testIteratorException() {
	    assertThrows(NoSuchElementException.class, () -> {
		List<Integer> l = new ArrayList<>();
		Iterator<Integer> i = l.iterator();

		i.next();
	});
	}
}
