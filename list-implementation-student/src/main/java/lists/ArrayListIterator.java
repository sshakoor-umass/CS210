/*
 * Copyright 2025 Marc Liberatore.
 */

package lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayListIterator<E> implements Iterator<E> {

    private ArrayList<E> list;
    private int current;

    public ArrayListIterator(ArrayList<E> list) {
        this.list = list;
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < list.size();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        E value = list.get(current);
        current++;

        return value;
    }
}