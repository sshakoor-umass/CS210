/*
 * Copyright 2023 Marc Liberatore.
 */

package comparators;

import java.util.Comparator;

/**
 * A comparator to determine which of two WebPageRecords represents a longer web
 * page. The page with the larger `length` attribute comes before the other.
 * If there is a tie, break it using the length of the first line -- again,
 * larger comes first.
 * If there is still a tie, break it by comparing which URL comes
 * lexicographically first.
 * Any remaining ties mean the two WebPageRecords should be considered equal.
 */
public class LargestPageComparator implements Comparator<WebPageRecord> {
    @Override
    public int compare(WebPageRecord x, WebPageRecord y) {
        // Larger length comes first, so compare y to x
        int lengthComparison = Integer.compare(y.length, x.length);
        if (lengthComparison != 0) {
            return lengthComparison;
        }

        // Tie: longer first line comes first
        int firstLineComparison = Integer.compare(y.firstLine.length(), x.firstLine.length());
        if (firstLineComparison != 0) {
            return firstLineComparison;
        }

        // Still tied: URLs in lexicographic order (ascending)
        return x.URL.compareTo(y.URL);
    }
}
