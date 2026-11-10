/*
 * Copyright 2026 Marc Liberatore.
 */
package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of utility methods for trees and their nodes.
 * 
 * You will almost certainly need to add some methods here to complete
 * the unimplemented methods!
 */
public class TreeUtilities {
    /**
     * Perform an in-order traversal of the tree rooted at the given node, and return
     * a list of the elements in the order they were visited.
     * @param node
     * @return a list of elements from the tree from an in-order traversal starting at node
     */
    static <E> List<E> inOrder(Node<E> node) {
        List<E> result = new ArrayList<>();
        inOrderWalk(node, result);
        return result;
    }

    private static <E> void inOrderWalk(Node<E> node, List<E> result) {
        if (node == null) {
            return;
        }
        inOrderWalk(node.left, result);
        result.add(node.data);
        inOrderWalk(node.right, result);
    }

    /**
     * Returns the height of the node n.
     * 
     * null has a height of -1; otherwise, the height is defined as 
     * 1 + the height of the larger of the node's two subtrees.
     * 
     * @param n
     * @return the height of the node n
     */
    static <E> int height(Node<E> n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * Returns true iff the tree rooted at n is a Binary Search Tree.
     * 
     * It must have no more than two children per node.
     * 
     * Each node's value must be greater than all the values in its left subtree, and smaller
     * than all the values in its right subtree. (This implies duplicate values are not allowed.)
     * 
     * @param n true iff the tree rooted at n is a Binary Search Tree
     * @return 
     */
    static <E extends Comparable<E>> boolean isBST(Node<E> n) {
        return isBSTWithin(n, null, null);
    }

    private static <E extends Comparable<E>> boolean isBSTWithin(Node<E> n, E lower, E upper) {
        if (n == null) {
            return true;
        }
        if (lower != null && n.data.compareTo(lower) <= 0) {
            return false;
        }
        if (upper != null && n.data.compareTo(upper) >= 0) {
            return false;
        }
        return isBSTWithin(n.left, lower, n.data) && isBSTWithin(n.right, n.data, upper);
    }

    /**
     * Returns true iff the tree rooted at n is an AVL tree.
     * 
     * Note this method should only work on the root -- it is a special node
     * that has a null parent pointer, which is not true of any other node
     * in the tree.
     * 
     * AVL trees are Binary Search trees with the additional property that 
     * every node in the tree has the AVL property.
     * 
     * A node has the AVL property iff the height of its left subtree and the
     * height of its right subtree differ by no more than 1.
     * 
     * @param <E>
     * @param n the root of a tree -- note this method should *only* work on the root
     * @returntrue iff the tree rooted at n is an AVL tree
     */
    static <E extends Comparable<E>> boolean isAVLTree(Node<E> n) {
        return (n == null || n.parent == null)
                && isBST(n) && isAVLWithParents(n, null);
    }

    private static <E> boolean isAVLWithParents(Node<E> n, Node<E> parent) {
        if (n == null) {
            return true;
        }
        if (n.parent != parent) {
            return false;
        }
        if (Math.abs(height(n.left) - height(n.right)) > 1) {
            return false;
        }
        return isAVLWithParents(n.left, n) && isAVLWithParents(n.right, n);
    }
    
    /**
     * Returns true iff the subtrees rooted at n and m have the same values 
     * and same structure. 
     * 
     * Only checks child references, not parent references.
     * @param n
     * @param m
     * @return true iff the subtrees rooted at n and m have the same values and same structure
     */
    static <E> boolean equalSubtrees(Node<E> n, Node<E> m) {
        if (n == null && m == null) {
            return true;
        }
        if (n == null || m == null) {
            return false;
        }
        return n.data.equals(m.data) && equalSubtrees(n.left, m.left) && equalSubtrees(n.right, m.right);
    }
}