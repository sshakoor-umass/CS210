/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import java.util.List;

public class ListExercises {

	/**
	 * Counts the number of characters in total across all strings in the supplied list;
	 * in other words, the sum of the lengths of the all the strings.
	 * @param l a non-null list of strings
	 * @return the number of characters
	 */
	public static int countCharacters(List<String> l) {
		int count = 0;
		for (String s : l) {
			count += s.length();
		}
		return count;
	}
	
	/**
	 * Splits a string into words and returns a list of the words. 
	 * If the string is empty, split returns a list containing an empty string.
	 * 
	 * @param s a non-null string of zero or more words
	 * @return a list of words
	 */
	public static List<String> split(String s) {
		List<String> words = new ArrayList<>();
		if (s.isEmpty()) {
			words.add("");
			return words;
		}
		String[] split = s.split("\\s+");
		for (String word : split) {
			words.add(word);
		}
		return words;
	}

	/**
	 * Returns a copy of the list of strings where each string has been 
	 * uppercased (as by String.toUpperCase).
	 * 
	 * The original list is unchanged.
	 * 
	 * @param l a non-null list of strings
	 * @return a list of uppercased strings
	 */
	public static List<String> uppercased(List<String> l) {
		List<String> uppercasedList = new ArrayList<>();
		for (String s : l) {
			uppercasedList.add(s.toUpperCase());
		}
		return uppercasedList;
	}

	/**
	 * Returns true if and only if each string in the supplied list of strings
	 * starts with an uppercase letter. If the list is empty, returns false.
	 * 
	 * @param l a non-null list of strings
	 * @return true iff each string starts with an uppercase letter
	 */
	public static boolean allCapitalizedWords(List<String> l) {
		if (l.isEmpty()) return false;
		for (String s : l) {
			if (s.isEmpty() || !Character.isUpperCase(s.charAt(0))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a list of strings selected from a supplied list, which contain the character c.
	 * 
	 * The returned list is in the same order as the original list, but it omits all strings 
	 * that do not contain c.
	 * 
	 * The original list is unmodified.
	 * 
	 * @param l a non-null list of strings
	 * @param c the character to filter on
	 * @return a list of strings containing the character c, selected from l
	 */
	public static List<String> filterContaining(List<String> l, char c) {
		List<String> filtered = new ArrayList<>();
		for (String s : l) {
			if (s.indexOf(c) != -1) {
				filtered.add(s);
			}
		}
		return filtered;
	}
	
	/**
	 * Inserts a string into a sorted list of strings, maintaining the sorted property of the list.
	 * 
	 * @param s the string to insert
	 * @param l a non-null, sorted list of strings
	 */
	public static void insertInOrder(String s, List<String> l) {
		int i = 0;
		while (i < l.size() && l.get(i).compareTo(s) < 0) {
			i++;
		}
		l.add(i, s);
	}
}
