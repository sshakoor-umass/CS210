
/*
 * Copyright 2026 Marc Liberatore.
 */
package hashtables;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OpenHashTable<E> implements HashTable<E> {

    E[] array;
    boolean[] tombstone;
    int size = 0;
    int n;
    int capacity;

    public OpenHashTable() {
        this.n = 3;
        this.capacity = 7;
        this.array = (E[]) new Object[capacity];
        this.tombstone = new boolean[capacity];
    }

    public OpenHashTable(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        this.n = 1;
        while ((1L << this.n) - 1 < n) {
            this.n++;
        }

        this.capacity = (int) ((1L << this.n) - 1);
        this.array = (E[]) new Object[capacity];
        this.tombstone = new boolean[capacity];
    }

    private int index(E e) {
        return Math.floorMod(e.hashCode(), capacity);
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

    public int tombstones() {
        int count = 0;
        for (boolean t : tombstone) {
            if (t) {
                count++;
            }
        }
        return count;
    }

    public double loadFactorWithTombstones() {
        return (size + tombstones()) / (double) capacity;
    }

    private void resize() {
        E[] oldArray = array;
        boolean[] oldTombstone = tombstone;

        n++;
        capacity = (int) ((1L << n) - 1);

        array = (E[]) new Object[capacity];
        tombstone = new boolean[capacity];
        size = 0;

        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null && !oldTombstone[i]) {
                add(oldArray[i]);
            }
        }
    }

    @Override
    public boolean add(E e) {
        if (loadFactorWithTombstones() > 0.75) {
            resize();
        }

        int start = index(e);
        int firstTombstone = -1;

        for (int i = 0; i < capacity; i++) {
            int current = (start + i) % capacity;

            if (array[current] == null) {
                int target = firstTombstone != -1
                    ? firstTombstone : current;

                array[target] = e;
                tombstone[target] = false;
                size++;
                return true;
            }

            if (tombstone[current]) {
                if (firstTombstone == -1) {
                    firstTombstone = current;
                }
            } else if (array[current].equals(e)) {
                return false;
            }
        }

        if (firstTombstone != -1) {
            array[firstTombstone] = e;
            tombstone[firstTombstone] = false;
            size++;
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(E e) {
        int start = index(e);

        for (int i = 0; i < capacity; i++) {
            int current = (start + i) % capacity;

            if (array[current] == null) {
                return false;
            }

            if (!tombstone[current]
                    && array[current].equals(e)) {
                tombstone[current] = true;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public E get(E e) {
        int start = index(e);

        for (int i = 0; i < capacity; i++) {
            int current = (start + i) % capacity;

            if (array[current] == null) {
                return null;
            }

            if (!tombstone[current]
                    && array[current].equals(e)) {
                return array[current];
            }
        }

        return null;
    }

    @Override
    public boolean contains(E e) {
        return get(e) != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                while (current < capacity &&
                       (array[current] == null ||
                        tombstone[current])) {
                    current++;
                }

                return current < capacity;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return array[current++];
            }
        };
    }
}
