// ============================================================
// DO NOT EDIT. This file is NOT part of your submission and is
// replaced with the original when your work is graded. Changes
// here may cause the autograder to fail!
// ============================================================

package recursion;

public class Node<E> {
    E data;
    Node<E> next;

    Node(E d) {
        data = d;
    }

    Node(E d, Node<E> n) {
        data = d;
        next = n;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + ", next=" + next + "]";
    }
}
