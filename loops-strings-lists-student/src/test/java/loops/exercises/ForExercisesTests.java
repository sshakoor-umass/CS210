/*
 * Copyright 2025 Marc Liberatore.
 */

package loops.exercises;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


// Uncomment this line if you want to catch infinite loops,
// but know it'll also cause the debugger to timeout!
// @Timeout(10)
public class ForExercisesTests {

	@Test
	public void testSumToZero() {
		assertEquals(0, ForExercises.sumTo(0));
	}

	@Test
	public void testSumToOne() {
		assertEquals(0, ForExercises.sumTo(1));
	}

	@Test
	public void testSumToFive() {
		assertEquals(10, ForExercises.sumTo(5));
	}

	@Test
	public void testSumFromToSingleValue() {
		assertEquals(3, ForExercises.sumFromTo(3, 4));
	}

	@Test
	public void testSumFromToRange() {
		assertEquals(9, ForExercises.sumFromTo(2, 5));
	}

	@Test
	public void testSumFromToFromZero() {
		assertEquals(10, ForExercises.sumFromTo(0, 5));
	}

	@Test
	public void testSumArrayEmpty() {
        // Note that (new int[] {}) is an "array literal" specifying an empty int[] array
		assertEquals(0, ForExercises.sum(new int[] {}));
	}

	@Test
	public void testSumArrayOneElement() {
        // Note that (new int[] {}) is an "array literal" specifying an array with contents [5]
		assertEquals(5, ForExercises.sum(new int[] {5}));
	}

	@Test
	public void testSumArrayMultipleElements() {
        // Note that (new int[] {}) is an "array literal" specifying an array with contents [1, 2, 3, 4]
		assertEquals(10, ForExercises.sum(new int[] {1, 2, 3, 4}));
	}

	@Test
	public void testSumListEmpty() {
		assertEquals(0, ForExercises.sum(new ArrayList<Integer>()));
	}

	@Test
	public void testSumListOneElement() {
        // Note that Arrays.asList(5) creates a List with contents [5]
		assertEquals(5, ForExercises.sum(Arrays.asList(5)));
	}

	@Test
	public void testSumListMultipleElements() {
        // Note that Arrays.asList(5) creates a List with contents [1, 2, 3, 4]
		assertEquals(10, ForExercises.sum(Arrays.asList(1, 2, 3, 4)));
	}

	@Test
	public void testAllEvenEmpty() {
		assertTrue(ForExercises.allEven(new int[] {}));
	}

	@Test
	public void testAllEvenSingleEven() {
		assertTrue(ForExercises.allEven(new int[] {2}));
	}

	@Test
	public void testAllEvenSingleOdd() {
		assertFalse(ForExercises.allEven(new int[] {3}));
	}

	@Test
	public void testAllEvenAllEven() {
		assertTrue(ForExercises.allEven(new int[] {2, 4, 6}));
	}

	@Test
	public void testAllEvenWithOdd() {
		assertFalse(ForExercises.allEven(new int[] {2, 3, 4}));
	}

	@Test
	public void testContainsEmpty() {
		assertFalse(ForExercises.contains(new ArrayList<Integer>(), 5));
	}

	@Test
	public void testContainsOneElementFound() {
		assertTrue(ForExercises.contains(Arrays.asList(5), 5));
	}

	@Test
	public void testContainsOneElementNotFound() {
		assertFalse(ForExercises.contains(Arrays.asList(5), 3));
	}

	@Test
	public void testContainsMultipleElementsFound() {
		assertTrue(ForExercises.contains(Arrays.asList(1, 2, 3), 3));
	}

	@Test
	public void testContainsMultipleElementsNotFound() {
		assertFalse(ForExercises.contains(Arrays.asList(1, 2, 3), 4));
	}

	@Test
	public void testSumEveryOtherEmpty() {
		assertEquals(0, ForExercises.sumEveryOther(new int[] {}));
	}

	@Test
	public void testSumEveryOtherOneElement() {
		assertEquals(5, ForExercises.sumEveryOther(new int[] {5}));
	}

	@Test
	public void testSumEveryOtherTwoElements() {
		assertEquals(5, ForExercises.sumEveryOther(new int[] {5, 3}));
	}

	@Test
	public void testSumEveryOtherThreeElements() {
		assertEquals(4, ForExercises.sumEveryOther(new int[] {1, 2, 3}));
	}

	@Test
	public void testSumEveryOtherFourElements() {
		assertEquals(9, ForExercises.sumEveryOther(new int[] {1, 2, 3, 4, 5}));
	}

	@Test
	public void testDoublingListTwoElementsTrue() {
		assertTrue(ForExercises.doublingList(Arrays.asList(1, 2)));
	}

	@Test
	public void testDoublingListTwoElementsFalse() {
		assertFalse(ForExercises.doublingList(Arrays.asList(1, 3)));
	}

	@Test
	public void testDoublingListThreeElementsTrue() {
		assertTrue(ForExercises.doublingList(Arrays.asList(1, 2, 4)));
	}

	@Test
	public void testDoublingListThreeElementsFalse() {
		assertFalse(ForExercises.doublingList(Arrays.asList(1, 2, 5)));
	}

	@Test
	public void testDoublingListStartingFromZero() {
		assertTrue(ForExercises.doublingList(Arrays.asList(0, 0, 0)));
	}
}
