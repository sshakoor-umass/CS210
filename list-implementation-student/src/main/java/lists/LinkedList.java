/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

import java.util.Iterator;

public class LinkedList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    Node<E> head;
    Node<E> tail;
    int size;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        Node<E> n = head;

        while (n != null) {
            result = prime * result + n.data.hashCode();
            n = n.next;
        }

        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof List))
            return false;

        List other = (List) obj;

        if (size != other.size())
            return false;

        Node<E> current = head;

        for (int i = 0; i < size; i++) {
            if (!current.data.equals(other.get(i))) {
                return false;
            }

            current = current.next;
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<E>(e);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == size) {
            add(e);
            return;
        }

        if (index == 0) {
            Node<E> newNode = new Node<E>(e, head);
            head = newNode;

            if (size == 0) {
                tail = newNode;
            }

            size++;
            return;
        }

        Node<E> current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node<E> newNode = new Node<E>(e, current.next);
        current.next = newNode;

        size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            E removed = head.data;

            head = head.next;
            size--;

            if (size == 0) {
                tail = null;
            }

            return removed;
        }

        Node<E> current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node<E> removedNode = current.next;
        E removed = removedNode.data;

        current.next = removedNode.next;

        if (removedNode == tail) {
            tail = current;
        }

        size--;

        return removed;
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E old = current.data;
        current.data = e;

        return old;
    }

    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.data.equals(e)) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<E>(head);
    }
}