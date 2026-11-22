// ============================================================
// DO NOT EDIT. This file is NOT part of your submission and is
// replaced with the original when your work is graded. Changes
// here may cause the autograder to fail!
// ============================================================

/*
 * Copyright 2023 Marc Liberatore.
 */
package treaps;

import java.util.Random;

public class Node<E> {
    static Random r = new Random();
    E data;
    int priority = r.nextInt(100); // limited to 100 for readability's sake
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
        return "Node [data=" + data + ", priority=" + priority + "]";
    }
    
}

