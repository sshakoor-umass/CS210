/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {

    private List<Fragment> fragments;

    /**
     * Creates a new Assembler containing a list of fragments.
     *
     * The list is copied into this assembler so that the original list will not be
     * modified by the actions of this assembler.
     *
     * @param fragments
     */
    public Assembler(List<Fragment> fragments) {
        this.fragments = new ArrayList<>(fragments);
    }

    /**
     * Returns the current list of fragments this assembler contains.
     *
     * @return the current list of fragments
     */
    public List<Fragment> getFragments() {
        return fragments;
    }

    /**
     * Attempts to perform a single assembly, returning true iff an assembly was
     * performed.
     *
     * This method chooses the best assembly possible, that is, it merges the two
     * fragments with the largest overlap, breaking ties between merged fragments by
     * choosing the shorter merged fragment.
     *
     * Merges must have an overlap of at least 1.
     *
     * After merging two fragments into a new fragment, the new fragment is inserted
     * into the list of fragments in this assembler, and the two original fragments
     * are removed from the list.
     *
     * @return true iff an assembly was performed
     */
    public boolean assembleOnce() {
        Fragment bestFrag1 = null;
        Fragment bestFrag2 = null;
        Fragment bestMerged = null;
        int bestOverlap = 0;

        for (int i = 0; i < fragments.size(); i++) {
            for (int j = i + 1; j < fragments.size(); j++) {

                Fragment frag1 = fragments.get(i);
                Fragment frag2 = fragments.get(j);

                // Check frag1 -> frag2
                int overlap = frag1.calculateOverlap(frag2);

                if (overlap > 0) {
                    Fragment merged = frag1.mergedWith(frag2);

                    if (overlap > bestOverlap ||
                            (overlap == bestOverlap &&
                            (bestMerged == null || merged.length() < bestMerged.length()))) {

                        bestOverlap = overlap;
                        bestFrag1 = frag1;
                        bestFrag2 = frag2;
                        bestMerged = merged;
                    }
                }

                // Check frag2 -> frag1
                overlap = frag2.calculateOverlap(frag1);

                if (overlap > 0) {
                    Fragment merged = frag2.mergedWith(frag1);

                    if (overlap > bestOverlap ||
                            (overlap == bestOverlap &&
                            (bestMerged == null || merged.length() < bestMerged.length()))) {

                        bestOverlap = overlap;
                        bestFrag1 = frag2;
                        bestFrag2 = frag1;
                        bestMerged = merged;
                    }
                }
            }
        }

        // No valid assembly was found
        if (bestMerged == null) {
            return false;
        }

        // Remove originals and add merged fragment
        fragments.remove(bestFrag1);
        fragments.remove(bestFrag2);
        fragments.add(bestMerged);

        return true;
    }

    /**
     * Repeatedly assembles fragments until no more assembly can occur.
     */
    public void assembleAll() {
        while (assembleOnce()) {
            // Keep assembling until assembleOnce() returns false
        }
    }
}