/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


// @Timeout(10)
public class ListTest {

    @Test
    public void testEmptyEquality() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();
        
        assertTrue(a.equals(l));
        assertTrue(l.equals(a));
    }

    @Test
    public void testEqualityOne() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();

        a.add(12);
        l.add(12);
        
        assertTrue(a.equals(l));
        assertTrue(l.equals(a));
    }

    @Test
    public void testInequalityOne() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();

        a.add(12);
        l.add(31);

        assertFalse(a.equals(l));
        assertFalse(l.equals(a));
    }

    @Test
    public void testEqualityTwo() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();

        a.add(12);
        l.add(12);

        assertTrue(a.equals(l));
        assertTrue(l.equals(a));

        a.add(31);
        l.add(31);

        assertTrue(a.equals(l));
        assertTrue(l.equals(a));
    }

    @Test
    public void testInequalityTwo() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();

        a.add(12);
        l.add(12);

        assertTrue(a.equals(l));
        assertTrue(l.equals(a));

        a.add(31);
        l.add(41);

        assertFalse(a.equals(l));
        assertFalse(l.equals(a));
    }
    
    @Test
    public void testEqualityMany() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            a.add(i);
            l.add(i);
            assertTrue(a.equals(l));
            assertTrue(l.equals(a));
        }
    }

    @Test
    public void testEqualityManyWithRemoval() {
        List<Integer> a = new ArrayList<>();
        List<Integer> l = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            a.add(i);
            l.add(i);
            assertTrue(a.equals(l));
            assertTrue(l.equals(a));
        }

        for (int i = 999; i >= 0; i--) {
            a.remove(i);
            l.remove(i);
            assertTrue(a.equals(l));
            assertTrue(l.equals(a));
        }
    }
}
