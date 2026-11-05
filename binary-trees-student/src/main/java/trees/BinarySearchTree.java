/*
 * Copyright 2026 Marc Liberatore.
 */
package trees;

/**
 * An implementation of a naive binary search tree, with no balance guarantees.
 */
public class BinarySearchTree<E extends Comparable<E>> {
    Node<E> root;
    int size;

    /**
     * @return the number of elements stored in this binary tree
     */
    public int size() {
        return size;
    }

    /**
     * Add an element to the tree. If there is an element in the tree that is
     * equals()
     * to the element, replace the old element in the tree with the new element.
     * 
     * Assume the element is non-null.
     * 
     * @param e the non-null element to insert.
     */
    public void add(E e) {
        root = addRecursive(root, e);
        size++;
    }

    private Node<E> addRecursive(Node<E> current, E e) {
        if (current == null) {
            return new Node<>(e);
        }
        if (e.compareTo(current.value) < 0) {
            current.left = addRecursive(current.left, e);
        } else {
            current.right = addRecursive(current.right, e);
        }
        return current;
    }

    /**
     * Return true if the element e is stored in the tree. Uses semantic equality
     * (equals()) to check.
     * 
     * @param e
     * @return true iff e is in the tree
     */
    public boolean contains(E e) {
        return containsRecursive(root, e);
    }

    private boolean containsRecursive(Node<E> current, E e) {
        if (current == null) {
            return false;
        }
        if (e.equals(current.value)) {
            return true;
        }
        if (e.compareTo(current.value) < 0) {
            return containsRecursive(current.left, e);
        }
        return containsRecursive(current.right, e);
    }

    /**
     * Remove the element e from the tree if present; do nothing otherwise.
     * 
     * @param e the element to be removed.
     */
    public void remove(E e) {
        root = removeRecursive(root, e);
        size--;
    }
    private Node<E> removeRecursive(Node<E> current, E e) {
        if (current == null)
        {
            return null;
        }
        if (e.equals(current.value)) {
            // Case 1: No children
            if (current.left == null && current.right == null){
                return null;
            }
            // Case 2: One child
            if (current.left == null)
            {
                return current.right;
            }
            if (current.right == null)
            {
                return current.left;
            }
            // Case 3: Two Children
            // Find the smallest value in the right subtree
            Node<E> smallest = findSmallest(current.right);
            current.value = smallest.value;
            current.right = removeRecursive(current.right, smallest.value);
        }
        return current;
    }

    /**
     * Return the least element in the tree, according to the partial ordering
     * on E.
     * 
     * @return the least element in the tree
     */
    public E getLeastElement() {
        if (root == null) {
            return null;
        }
        Node<E> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

}
