
/*
 * Copyright 2026 Marc Liberatore.
 */
package hashtables;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChainingHashTable<E> implements HashTable<E> {

    LinkedList<E>[] array;
    int size = 0;
    int capacity;
    int n;

    public ChainingHashTable() {
        this.n = 3;
        this.capacity = 7;
        this.array = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            array[i] = new LinkedList<>();
        }
    }

    public ChainingHashTable(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        this.n = 1;

        while ((1L << this.n) - 1 < n) {
            this.n++;
        }

        this.capacity = (int) ((1L << this.n) - 1);
        this.array = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            array[i] = new LinkedList<>();
        }
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

    private void resize() {
        LinkedList<E>[] oldArray = array;

        n++;
        capacity = (int) ((1L << n) - 1);

        array = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            array[i] = new LinkedList<>();
        }

        for (LinkedList<E> bucket : oldArray) {
            for (E element : bucket) {
                int newIndex = index(element);
                array[newIndex].add(element);
            }
        }
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

        for (E element : array[index]) {
            if (element.equals(e)) {
                return element;
            }
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