/*
 * Copyright 2026 Marc Liberatore.
 */
package hashtables;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * An implementation of HashTable.
 * 
 * This implementation uses chaining to resolve collisions. Chaining means 
 * the underlying array stores references to growable structures (like 
 * LinkedLists) that we expect to remain small in size. When there is a 
 * collision, the element is added to the end of the growable structure. It
 * must search the entire growable structure whenever checking membership
 * or removing elements.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor exceeds 0.75, the next add() triggers a
 * resize by incrementing n (by one). For example, when n=3, then capacity=7.
 * When size=6, then load factor ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15.
 */
public class ChainingHashTable<E> implements HashTable<E> {
    LinkedList<E>[] array;
    int size = 0;
    int capacity;
    int n;
    
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    public ChainingHashTable() {
        this(7);
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public ChainingHashTable(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.n = n;
        this.capacity = (1 << n) - 1;
        this.array = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = new LinkedList<>();
        }
    }

    private int index(E e) {
        return Math.abs(e.hashCode() % capacity);
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double loadFactor() {
        return (double) size / capacity;
    }

    @Override
    public boolean add(E e) {
        if (loadFactor() > 0.75) {
            resize();
        }
        int index = index(e);
        if (!array[index].contains(e)) {
            array[index].add(e);
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        int index = index(e);
        if (array[index].remove(e)) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E e) {
        int index = index(e);
        return array[index].contains(e);
    }

    @Override
    public E get(E e) {
        int index = index(e);
        if (array[index].contains(e)) {
            return e;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int currentBucket = 0;
            Iterator<E> currentIterator = array[0].iterator();

            @Override
            public boolean hasNext() {
                while (currentBucket < array.length) {
                    if (currentIterator.hasNext()) {
                        return true;
                    }
                    currentBucket++;
                    if (currentBucket < array.length) {
                        currentIterator = array[currentBucket].iterator();
                    }
                }
                return false;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}
