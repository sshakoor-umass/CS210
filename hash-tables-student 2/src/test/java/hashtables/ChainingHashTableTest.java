/*
 * Copyright 2026 Marc Liberatore.
 */

package hashtables;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// @Timeout(10)
public class ChainingHashTableTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testNewTable() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(0, t.size());
        assertEquals(7, t.capacity());
    }

    @Test
    public void testNewTableCapacity5() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>(5);
        assertEquals(7, t.capacity());
    }

    @Test
    public void testNewTableCapacity15() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>(15);
        assertEquals(15, t.capacity());
    }

    @Test
    public void testNewTableCapacityMultiple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>(2);
        assertEquals(3, t.capacity());

        t = new ChainingHashTable<>(7);
        assertEquals(7, t.capacity());

        t = new ChainingHashTable<>(20);
        assertEquals(31, t.capacity());

        t = new ChainingHashTable<>(65000);
        assertEquals(65535, t.capacity());
    }

    @Test
    public void testSizeSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(0, t.size());

        t.add(0);
        assertEquals(1, t.size());

        t.add(1);
        assertEquals(2, t.size());

        t.add(2);
        assertEquals(3, t.size());
    }

    @Test
    public void testSizeCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(0, t.size());

        t.add(0);
        assertEquals(1, t.size());

        t.add(7);
        assertEquals(2, t.size());

        t.add(15);
        assertEquals(3, t.size());
    }

    @Test
    public void testCapacitySimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(7, t.capacity());

        t.add(0);
        assertEquals(7, t.capacity());

        t.add(1);
        assertEquals(7, t.capacity());

        t.add(2);
        assertEquals(7, t.capacity());
    }

    @Test
    public void testCapacityCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(7, t.capacity());

        t.add(0);
        assertEquals(7, t.capacity());

        t.add(7);
        assertEquals(7, t.capacity());

        t.add(14);
        assertEquals(7, t.capacity());
    }

    @Test
    public void testLoadFactorSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(0, t.loadFactor(), 0);

        t.add(0);
        assertEquals(1.0/7, t.loadFactor(), 0);

        t.add(1);
        assertEquals(2.0/7, t.loadFactor(), 0);

        t.add(2);
        assertEquals(3.0/7, t.loadFactor(), 0);
    }

    @Test
    public void testLoadFactorCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(0, t.loadFactor(), 0);

        t.add(0);
        assertEquals(1.0 / 7, t.loadFactor(), 0);

        t.add(7);
        assertEquals(2.0 / 7, t.loadFactor(), 0);

        t.add(14);
        assertEquals(3.0 / 7, t.loadFactor(), 0);
    }
    
    @Test
    public void testContainsSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        t.add(0);
        assertTrue(t.contains(0));
        assertFalse(t.contains(7));
    }

    @Test
    public void testContainsCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        t.add(0);
        assertTrue(t.contains(0));
        assertFalse(t.contains(7));

        t.add(7);
        assertTrue(t.contains(0));
        assertTrue(t.contains(7));
    }

    @Test
    public void testGetSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(null, t.get(0));
        assertEquals(null, t.get(7));

        t.add(0);

        assertEquals(Integer.valueOf(0), t.get(0));
        assertEquals(null, t.get(7));
    }
    
    @Test
    public void testGetCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(null, t.get(0));
        assertEquals(null, t.get(7));

        t.add(0);

        assertEquals(Integer.valueOf(0), t.get(0));
        assertEquals(null, t.get(7));

        t.add(7);

        assertEquals(Integer.valueOf(0), t.get(0));
        assertEquals(Integer.valueOf(7), t.get(7));
    }



    @Test
    public void testRemoveSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        t.add(0);

        assertTrue(t.contains(0));
        assertFalse(t.contains(7));

        assertTrue(t.remove(0));

        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        assertFalse(t.remove(0));
        assertFalse(t.remove(7));
    }

    @Test
    public void testRemoveCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        t.add(0);

        assertTrue(t.contains(0));
        assertFalse(t.contains(7));

        t.add(7);

        assertTrue(t.contains(0));
        assertTrue(t.contains(7));

        assertTrue(t.remove(7));

        assertTrue(t.contains(0));
        assertFalse(t.contains(7));

        assertFalse(t.remove(7));
        assertTrue(t.remove(0));

        assertFalse(t.contains(0));
        assertFalse(t.contains(7));
    }

    @Test
    public void testRemoveCollisionOffset() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        t.add(0);

        assertTrue(t.contains(0));
        assertFalse(t.contains(7));

        t.add(7);

        assertTrue(t.contains(0));
        assertTrue(t.contains(7));

        assertTrue(t.remove(0));

        assertFalse(t.contains(0));
        assertTrue(t.contains(7));
    }    

    @Test
    public void testRemoveCollisionOffsetReadd() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertFalse(t.contains(0));
        assertFalse(t.contains(7));

        t.add(0);

        assertTrue(t.contains(0));
        assertFalse(t.contains(7));

        t.add(7);

        assertTrue(t.contains(0));
        assertTrue(t.contains(7));

        assertTrue(t.remove(0));

        assertFalse(t.contains(0));
        assertTrue(t.contains(7));

        assertTrue(t.add(0));
        assertTrue(t.contains(0));
        assertTrue(t.contains(7));
    }

    @Test
    public void testAddMultiple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertTrue(t.add(0));
        assertFalse(t.add(0));
    }

    @Test
    public void testIteratorEmpty() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        for (Integer i : t) {
            fail();
        }
    }

    @Test
    public void testIteratorSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        for (int i = 0; i < 6; i++) {
            t.add(i);
        }

        List<Integer> l = new ArrayList<>();
        for (Integer i : t) {
            l.add(i);
        }

        assertEquals(6, l.size());
        for (int i = 0; i < 6; i++) {
            assertTrue(l.contains(i));
        }
    }

    @Test
    public void testIteratorCollisions() throws Exception {
        List<Integer> values = Arrays.asList(0, 7, 14, 2, 9);
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        for (Integer i : values) {
            t.add(i);
        }

        List<Integer> l = new ArrayList<>();
        for (Integer i : t) {
            l.add(i);
        }

        assertEquals(5, l.size());
        for (Integer i : values) {
            assertTrue(l.contains(i));
        }
    }
    
    @Test
    public void testEnlargeSimple() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(7, t.capacity());
        for (int i = 0; i < 6; i++) {
            t.add(i);
            assertEquals(7, t.capacity());
        }
        t.add(6);
        assertEquals(15, t.capacity());
    }

    @Test
    public void testEnlargeCollision() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        assertEquals(7, t.capacity());
        for (int i = 1; i < 7; i++) {
            t.add(i * 7);
            assertEquals(7, t.capacity());
        }
        t.add(0);
        assertEquals(15, t.capacity());
    }

    @Test
    public void test100Random() throws Exception {
        Random random = new Random(0);
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        Set<Integer> h = new HashSet<Integer>();
        int n = 3;
        int cap = 7;
        for (int i = 0; i < 100; i++) {
            final int r = random.nextInt(100);
            if (h.contains(r)) {
                assertFalse(t.add(r));
            } else {
                if (t.loadFactor() > 0.75) {
                    n++;
                    cap = (int) (Math.pow(2, n) - 1);
                }
                assertTrue(t.add(r));
                assertEquals(cap, t.capacity());
                h.add(r);
            }
            assertEquals(h.size(), t.size());
        }

        for (Integer i : h) {
            assertTrue(t.contains(i));
        }
    }
    
    @Test
    public void test1000RandomAddRemove() throws Exception {
        Random random = new Random(0);
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        Set<Integer> h = new HashSet<Integer>();
        int n = 3;
        int cap = 7;
        for (int i = 0; i < 1000; i++) {
            final int r = random.nextInt(100);
            if (random.nextDouble() < 0.67) {
                // add
                if (t.loadFactor() > 0.75) {
                    n++;
                    cap = (int) (Math.pow(2, n) - 1);
                }
                if (h.contains(r)) {
                    assertFalse(t.add(r));
                } else {
                    assertTrue(t.add(r));
                    assertEquals(cap, t.capacity());
                    h.add(r);
                }
            } else {
                // remove
                if (h.contains(r)) {
                    assertTrue(t.remove(r));
                    h.remove(r);
                } else {
                    assertFalse(t.remove(r));
                }
            }
            assertEquals(h.size(), t.size());
        }

        for (Integer i : h) {
            assertTrue(t.contains(i));
        }
    }

    @Test
    public void test1000x1000RandomAddRemove() throws Exception {
        Random random = new Random(0);
        for (int round = 0; round < 1000; round++) {
            ChainingHashTable<Integer> t = new ChainingHashTable<>();
            Set<Integer> h = new HashSet<Integer>();
            int n = 3;
            int cap = 7;
            for (int i = 0; i < 1000; i++) {
                final int r = random.nextInt(100);
                if (random.nextDouble() < 0.67) {
                    // add
                    if (t.loadFactor() > 0.75) {
                        n++;
                        cap = (int) (Math.pow(2, n) - 1);
                    }
                    if (h.contains(r)) {
                        assertFalse(t.add(r));
                    } else {
                        assertTrue(t.add(r));
                        assertEquals(cap, t.capacity());
                        h.add(r);
                    }
                } else {
                    // remove
                    if (h.contains(r)) {
                        assertTrue(t.remove(r));
                        h.remove(r);
                    } else {
                        assertFalse(t.remove(r));
                    }
                }
                assertEquals(h.size(), t.size());
            }

            for (Integer i : h) {
                assertTrue(t.contains(i));
            }
        }
    }

    @Test
    public void testSizeAfterRemove() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        t.add(0);
        t.add(1);
        assertEquals(2, t.size());

        t.remove(0);
        assertEquals(1, t.size());

        t.remove(1);
        assertEquals(0, t.size());
    }

    @Test
    public void testSizeUnchangedOnDuplicate() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        t.add(0);
        assertEquals(1, t.size());

        t.add(0);
        assertEquals(1, t.size());
    }

    @Test
    public void testSizeAfterEnlarge() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        for (int i = 0; i < 7; i++) {
            t.add(i);
        }
        assertEquals(15, t.capacity());
        assertEquals(7, t.size());
    }

    @Test
    public void testGetAfterRemove() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        t.add(0);
        assertEquals(Integer.valueOf(0), t.get(0));

        t.remove(0);
        assertNull(t.get(0));
    }

    @Test
    public void testGetCollisionAfterRemove() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        t.add(0);
        t.add(7); // collides with 0 in a capacity-7 table

        t.remove(0); // removes 0 from the chain; 7 must remain findable

        assertNull(t.get(0));
        assertEquals(Integer.valueOf(7), t.get(7));
    }

    @Test
    public void testIteratorHasNextIdempotent() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        t.add(1);
        Iterator<Integer> it = t.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());

        assertEquals(Integer.valueOf(1), it.next());

        assertFalse(it.hasNext());
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorNextThrowsNoSuchElement() throws Exception {
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        Iterator<Integer> it = t.iterator();
        assertFalse(it.hasNext());
        try {
            it.next();
        } catch (NoSuchElementException e) {
            return;
        }
        fail("NoSuchElementException expected!");
    }

    @Test
    public void testChainingHashTableAll() throws Exception {
        testNewTable();
        testNewTableCapacity5();
        testNewTableCapacity15();
        testNewTableCapacityMultiple();
        testSizeSimple();
        testSizeCollision();
        testCapacitySimple();
        testCapacityCollision();
        testLoadFactorSimple();
        testLoadFactorCollision();
        testContainsSimple();
        testContainsCollision();
        testGetSimple();
        testGetCollision();
        testRemoveSimple();
        testRemoveCollision();
        testRemoveCollisionOffset();
        testRemoveCollisionOffsetReadd();
        testAddMultiple();
        testIteratorEmpty();
        testIteratorSimple();
        testIteratorCollisions();
        testEnlargeSimple();
        testEnlargeCollision();
        testSizeAfterRemove();
        testSizeUnchangedOnDuplicate();
        testSizeAfterEnlarge();
        testGetAfterRemove();
        testGetCollisionAfterRemove();
        testIteratorHasNextIdempotent();
        testIteratorNextThrowsNoSuchElement();
        test100Random();
        test1000RandomAddRemove();
        test1000x1000RandomAddRemove();
    }

}
