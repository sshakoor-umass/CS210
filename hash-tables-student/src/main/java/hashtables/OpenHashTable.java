/*
 * Copyright 2026 Marc Liberatore.
 */
package hashtables;

import java.util.Iterator;

/**
 * An implementation of HashTable.
 * 
 * This implementation uses "open addressing" to resolve collisions. Open
 * addressing means that the underlying array stores references to elements
 * (*not* ArrayLists of references!). when there is a collision, the hash table
 * searches forward linearly in the array until the element being sought is
 * found, or until an empty (non-tombstone) cell is found.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor with tombstones (i.e., (size + tombstones) /
 * capacity) exceeds 0.75, the next add() triggers a resize by incrementing n
 * (by one). Tombstones count toward the load factor because they occupy slots
 * and lengthen probe chains.
 * 
 * For example, when n=3, then capacity=7. When size=6 and tombstones=0, then
 * load factor with tombstones ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15. Similarly, 
 * when size=5 and tombstones=1, the load factor with tombstones is also 
 * (5+1)/7 ~=0.86, so the next add() triggers a resize to capacity=15, 
 * even though the plain load factor (5/7 ~=0.71) does not exceed 0.75.
 */
public class OpenHashTable<E> implements HashTable<E> {
    E[] array;
    boolean[] tombstone;
    int size = 0;
    int n;
    int capacity;
    
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    public OpenHashTable() {
        this(7);
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public OpenHashTable(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.n = n;
        this.capacity = (1 << n) - 1;
        this.array = (E[]) new Object[capacity];
        this.tombstone = new boolean[capacity];
    }

    private int index(E e) {
        return Math.abs(e.hashCode() % capacity);
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public double loadFactor() {
        return size / (double) capacity;
    }

    /**
     * 
     * @return the number of tombstones
     */
    public int tombstones() {
        int count = 0;
        for (boolean t : tombstone) {
            if (t) count++;
        }
        return count;
    }

    /**
     * Return the load factor: (size + number of tombstones) / capacity.
     * 
     * @return the load factor, including tombstones
     */
    public double loadFactorWithTombstones() {
        return (size + tombstones()) / (double) capacity;
    }

    @Override
    public boolean add(E e) {
        // Note: in addition to the considerations from ChainingHashTable,
        // remember that insertions can happen either into empty (null)
        // calles, or into non-null cells that are marked as tombstones
        // which can be overwritten (and the correspnding tombstone
        // value cleared)
        int index = index(e);
        while (array[index] != null) {
            if (array[index].equals(e)) {
                array[index] = e;
                tombstone[index] = false;
                size++;
                return true;
            }
            index = (index + 1) % capacity;
        }
        array[index] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(E e) {
        // To remove a given value, don't actually remove it from the array;
        // just set the corresponding cell in the tombstone array
        int index = index(e);
        while (array[index] != null) {
            if (array[index].equals(e)) {
                tombstone[index] = true;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
        }
        return false;
    }

    @Override
    public E get(E e) {
        // To do lookups, you have to check the cell and all the following
        // cells (including tombstoned cells) until you hit a non-tombstoned
        // empty cell
        int index = index(e);
        while (array[index] != null) {
            if (array[index].equals(e) && !tombstone[index]) {
                return array[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    @Override
    public boolean contains(E e) {
        // The same considerations as in get() apply here.
        int index = index(e);
        while (array[index] != null) {
            if (array[index].equals(e) && !tombstone[index]) {
                return true;
            }
            index = (index + 1) % capacity;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                while (index < capacity) {
                    if (array[index] != null && !tombstone[index]) {
                        count++;
                        return array[index++];
                    }
                    index++;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
