/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ExtendedArrayList<E> extends ArrayList<E> {
	
	/**
	 * Counts the number of elements in this list that are equal to e.
	 * 
	 * Uses .equals to check for equality. 
	 * 
	 * @param e the element to count
	 * @return the number of elements equal to e
	 */
	public int count(E e) {
		int count = 0;
		for (E element : this) {
			if (element.equals(e)) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Rotates the list right n places.
	 * 
	 * Rotating a list right means moving the last item to the front of the list:
	 * 
	 * 1, 2, 3, 4, 5 when rotated right once becomes 5, 1, 2, 3, 4
	 * 
	 * A list is rotated right two places as follows:
	 * 
	 * 4, 5, 1, 2, 3
	 * 
	 * and so on.
	 * 
	 * @param n the distance to rotate the list right
	 */
	public void rotateRight(int n) {
		if (size() == 0) return;

		n = n % size();  // Normalize n
		if (n < 0) n += size();

		List<E> temp = new ArrayList<>(this);
		for (int i = 0; i < size(); i++) {
			set((i + n) % size(), temp.get(i));
		}
	}

	/**
	 * Intersperses e between each existing element of the list.
	 * 
	 * For example, given the list: "hey", "ho", "hi", if we intersperse "yo" we get:
	 * "hey", "yo", "ho", "yo", "hi"
	 * 
	 * @param e the element to intersperse
	 */
	public void intersperse(E e) {
		List<E> temp = new ArrayList<>();
		for (E element : this) {
			temp.add(element);
			temp.add(e);
		}
		if (!temp.isEmpty()) {
			temp.remove(temp.size() - 1);
		}
		clear();
		addAll(temp);
	}
	
	/**
	 * Returns a copy of this list in reverse order.
	 * 
	 * This list is unmodified by this operation.
	 * 
	 * @return a reversed copy of the list
	 */
	public List<E> reversed() {
		List<E> reversed = new ArrayList<>();
		for (int i = size() - 1; i >= 0; i--) {
			reversed.add(get(i));
		}
		return reversed;
	}
}
