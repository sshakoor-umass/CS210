
/*
 * Copyright 2023 Marc Liberatore.
 */

package treaps;

import java.util.ArrayList;
import java.util.List;

public class Treap<E extends Comparable<E>> {
    Node<E> root;
    int size;

    public int size() {
        return size;
    }

    /**
     * Return true iff the tree contains the value e.
     */
    public boolean contains(E e) {
        return find(e) != null;
    }

    private Node<E> find(E e, Node<E> n) {
        if (n == null) {
            return null;
        } else if (e.equals(n.data)) {
            return n;
        } else if (e.compareTo(n.data) < 0) {
            return find(e, n.left);
        } else {
            return find(e, n.right);
        }
    }

    private Node<E> find(E e) {
        return find(e, root);
    }

    /**
     * Perform an in-order traversal.
     */
    static <E> List<E> inOrder(Node<E> node) {
        List<E> result = new ArrayList<>();

        if (node == null) {
            return result;
        }

        result.addAll(inOrder(node.left));
        result.add(node.data);
        result.addAll(inOrder(node.right));

        return result;
    }

    /**
     * Return true iff the tree is a valid BST.
     */
    static <E extends Comparable<E>> boolean isBST(Node<E> n) {
        return isBSTHelper(n, null, null);
    }

    private static <E extends Comparable<E>> boolean isBSTHelper(
            Node<E> n, E min, E max) {

        if (n == null) {
            return true;
        }

        if (min != null && n.data.compareTo(min) <= 0) {
            return false;
        }

        if (max != null && n.data.compareTo(max) >= 0) {
            return false;
        }

        return isBSTHelper(n.left, min, n.data)
            && isBSTHelper(n.right, n.data, max);
    }

    /**
     * Return true iff the tree satisfies the max-heap property.
     */
    static <E extends Comparable<E>> boolean isHeap(Node<E> n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && n.left.priority > n.priority) {
            return false;
        }

        if (n.right != null && n.right.priority > n.priority) {
            return false;
        }

        return isHeap(n.left) && isHeap(n.right);
    }

    /**
     * Add a value to the treap.
     */
    public void add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size = 1;
            return;
        }

        add(e, root);
    }

    /**
     * Add a value while maintaining BST and heap properties.
     */
    public void add(E e, Node<E> node) {
        if (e.equals(node.data)) {
            node.data = e;
            return;
        }

        if (e.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, node);
                size++;
                restoreHeap(node.left);
                return;
            } else {
                add(e, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(e, node);
                size++;
                restoreHeap(node.right);
                return;
            } else {
                add(e, node.right);
            }
        }
    }

    /**
     * Restore the heap property by rotating a node upward.
     */
    private void restoreHeap(Node<E> node) {
        while (node.parent != null
                && node.priority > node.parent.priority) {

            Node<E> parent = node.parent;

            if (parent.left == node) {
                rotateLL(parent);
            } else {
                rotateRR(parent);
            }
        }
    }

    /**
     * Perform an LL rotation around n.
     */
    private void rotateLL(Node<E> n) {
        Node<E> A, B, T1, T2, T3, p;

        B = n;
        p = B.parent;
        A = B.left;
        T1 = A.left;
        T2 = A.right;
        T3 = B.right;

        if (B == root) {
            root = A;
            A.parent = null;
        } else {
            if (p.left == B) {
                p.left = A;
            } else {
                p.right = A;
            }
            A.parent = p;
        }

        A.right = B;
        B.parent = A;

        B.left = T2;
        if (T2 != null) {
            T2.parent = B;
        }
    }

    /**
     * Perform an RR rotation around n.
     */
    private void rotateRR(Node<E> n) {
        Node<E> A, B, T2, p;

        B = n;
        p = B.parent;
        A = B.right;
        T2 = A.left;

        if (B == root) {
            root = A;
            A.parent = null;
        } else {
            if (p.left == B) {
                p.left = A;
            } else {
                p.right = A;
            }
            A.parent = p;
        }

        A.left = B;
        B.parent = A;

        B.right = T2;
        if (T2 != null) {
            T2.parent = B;
        }
    }

    public static void main(String[] args) {
        Treap<Integer> t = new Treap<>();

        for (int i = 0; i < 15; i++) {
            t.add(i);
            TreePrinter.print(t.root);
            System.out.println(isBST(t.root) + "/" + isHeap(t.root));
        }
    }
}
