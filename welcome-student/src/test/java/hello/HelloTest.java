/*
 * Copyright 2021 Marc Liberatore.
 */

package hello;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class HelloTest {
	@Test
	public void testHelloString() {
		assertEquals("Hello, CICS 210 Data Structures World!",
				Hello.helloString(), "helloString returns incorrect value");
	}
}
