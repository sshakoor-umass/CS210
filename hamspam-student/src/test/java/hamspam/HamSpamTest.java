/*
 * Copyright 2021 Marc Liberatore.
 */

package hamspam;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class HamSpamTest {
	private final HamSpam hamspamThreeFour = new HamSpam(3, 4);
	private final HamSpam hamspamTwoFive = new HamSpam(2, 5);
	private final HamSpam hamspamThreeSix = new HamSpam(6, 3);
	
	@Test
	public void testNotHamOrSpamGetValue() {
		assertEquals("2",
				hamspamThreeFour.getValue(2), "getValue returns incorrect value");
	}

	@Test
	public void testNotHamOrSpamGetValueAlt() {
		assertEquals("3",
				hamspamTwoFive.getValue(3), "getValue returns incorrect value");
	}

	@Test
	public void testHamOrSpamGetValue() {
		assertEquals("ham",
				hamspamThreeFour.getValue(3), "getValue returns incorrect value");
		assertEquals("spam",
				hamspamThreeFour.getValue(4), "getValue returns incorrect value");
	}

	@Test
	public void testHamOrSpamMultipleGetValue() {
		assertEquals("ham",
				hamspamThreeFour.getValue(9), "getValue returns incorrect value");
		assertEquals("spam",
				hamspamThreeFour.getValue(8), "getValue returns incorrect value");
	}

	@Test
	public void testHamAndSpamGetValue() {
		assertEquals(
			"hamspam", hamspamThreeFour.getValue(12), "getValue returns incorrect value");
	}

	@Test
	public void testGetValuesSimple() {
		assertArrayEquals(new String[] {
				"1", "2", "ham", "spam" }, hamspamThreeFour.getValues(1, 4), "getValues returns incorrect values");
	}

	@Test
	public void testGetValuesOffset() {
		assertArrayEquals(new String[] {
				"2", "ham", "spam", "5", "ham" },
				hamspamThreeFour.getValues(2, 6), "getValues returns incorrect values");
	}

	@Test
	public void testNotHamOrSpamGetValueShared() {
		assertEquals("7",
				hamspamThreeSix.getValue(7), "getValue returns incorrect value");
	}

	@Test
	public void testHamOrSpamMultipleGetValueShared() {
		assertEquals("spam",
				hamspamThreeSix.getValue(9), "getValue returns incorrect value");
	}

	@Test
	public void testHamAndSpamGetValueShared() {
		assertEquals(
			"hamspam", hamspamThreeSix.getValue(6), "getValue returns incorrect value");
	}

	@Test
	public void testHamOrSpamGetValueAlt() {
		assertEquals("ham",
				hamspamTwoFive.getValue(2), "getValue returns incorrect value");
		assertEquals("ham",
				hamspamTwoFive.getValue(4), "getValue returns incorrect value");
		assertEquals("spam",
				hamspamTwoFive.getValue(5), "getValue returns incorrect value");
	}

	@Test
	public void testHamAndSpamGetValueAlt() {
		assertEquals("hamspam",
				hamspamTwoFive.getValue(10), "getValue returns incorrect value");
	}


	@Test
	public void testGetValuesAlt() {
		assertArrayEquals(new String[] {
				"1", "ham", "3", "ham", "spam" },
				hamspamTwoFive.getValues(1, 5), "getValues returns incorrect values");

		assertArrayEquals(new String[] {
				"7", "ham", "9", "hamspam", "11" },
				hamspamTwoFive.getValues(7, 11), "getValues returns incorrect values");
	}

	@Test
	public void testGetValuesShared() {
		assertArrayEquals(new String[] {
				"spam", "4", "5", "hamspam" }, hamspamThreeSix.getValues(3, 6), "getValues returns incorrect values");
	}
}
