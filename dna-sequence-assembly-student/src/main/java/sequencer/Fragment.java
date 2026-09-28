/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

public class Fragment {

    private String nucleotides;

    /**
     * Creates a new Fragment based upon a String representing a sequence of
     * nucleotides, containing only the uppercase characters G, C, A and T.
     *
     * @param nucleotides
     * @throws IllegalArgumentException if invalid characters are in the sequence of
     *                                  nucleotides
     */
    public Fragment(String nucleotides) throws IllegalArgumentException {
        if (!nucleotides.matches("[GCAT]+")) {
            throw new IllegalArgumentException("Invalid nucleotide sequence");
        }

        this.nucleotides = nucleotides;
    }

    /**
     * Returns the length of this fragment.
     *
     * @return the length of this fragment
     */
    public int length() {
        return nucleotides.length();
    }

    /**
     * Returns a String representation of this fragment, exactly as was passed to
     * the constructor.
     *
     * @return a String representation of this fragment
     */
    @Override
    public String toString() {
        return nucleotides;
    }

    /**
     * Return true if and only if this fragment contains the same sequence of
     * nucleotides as another sequence.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Fragment)) {
            return false;
        }

        Fragment f = (Fragment) o;

        return nucleotides.equals(f.nucleotides);
    }

    /**
     * Returns the number of nucleotides of overlap between the end of this fragment
     * and the start of another fragment, f.
     *
     * @param f the other fragment
     * @return the number of nucleotides of overlap
     */
    public int calculateOverlap(Fragment f) {

        int maxOverlap = Math.min(this.length(), f.length());

        for (int overlap = maxOverlap; overlap > 0; overlap--) {

            String end = nucleotides.substring(this.length() - overlap);
            String start = f.nucleotides.substring(0, overlap);

            if (end.equals(start)) {
                return overlap;
            }
        }

        return 0;
    }

    /**
     * Returns a new fragment based upon merging this fragment with another fragment
     * f.
     *
     * @param f the other fragment
     * @return a new fragment based upon merging this fragment with another fragment
     */
    public Fragment mergedWith(Fragment f) {

        int overlap = calculateOverlap(f);

        String merged = nucleotides + f.nucleotides.substring(overlap);

        return new Fragment(merged);
    }
}