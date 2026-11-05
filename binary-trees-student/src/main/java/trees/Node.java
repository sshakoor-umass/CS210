// ============================================================
// DO NOT EDIT. This file is NOT part of your submission and is
// replaced with the original when your work is graded. Changes
// here may cause the autograder to fail!
// ============================================================

/*
 * Copyright 2023 Marc Liberatore.
 */
package trees;

public class Node<E> {
    E data;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    Node(E d) {
        data = d;
    }

    Node(E d, Node<E> p) {
        data = d;
        parent = p;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + "]";
    }
    
}

