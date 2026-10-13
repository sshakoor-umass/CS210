/*
 * Copyright 2026 Marc Liberatore.
 */

package comparables;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of "simple" polynomials, that is, polynomials
 * with a single indeterminate value) where all coefficients are integers,
 * such as (x^3 + x^2 - x + 5) or (x^4 + x).
 * 
 * Internally, the coefficients of each term are stored in a list. Each entry
 * in the list represents one term of the polynominal. Indices
 * correspond to the exponent of the term, and value corresond
 * to the coefficient of the term (thus, terms that are omitted have a
 * zero as a value).
 * 
 * For example (x^3 + x^2 - x + 5) is stored as the list:
 * [5, -1, 1, 1]
 * 
 * and (x^4 + x) is stored as the list:
 * [0, 1, 0, 0, 1]
 */
public class SimplePolynomial implements Comparable<SimplePolynomial> {
    List<Integer> coefficients;

    public SimplePolynomial(List<Integer> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    /**
     * Returns a copy of the given coefficient list with trailing zeros removed.
     */
    private static List<Integer> normalize(List<Integer> coeffs) {
        int last = coeffs.size() - 1;
        while (last > 0 && coeffs.get(last) == 0) {
            last--;
        }
        return new ArrayList<>(coeffs.subList(0, last + 1));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        List<Integer> norm = normalize(coefficients);
        result = prime * result + norm.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimplePolynomial other = (SimplePolynomial) obj;
        return normalize(coefficients).equals(normalize(other.coefficients));
    }

    @Override
    public String toString() {
        List<Integer> norm = normalize(coefficients);
        StringBuilder sb = new StringBuilder();
        for (int i = norm.size() - 1; i >= 0; i--) {
            int coeff = norm.get(i);
            if (coeff == 0)
                continue;

            String termVar;
            if (i == 0) {
                termVar = "";
            } else if (i == 1) {
                termVar = "x";
            } else {
                termVar = "x^" + i;
            }

            String coeffStr;
            if (i == 0 || Math.abs(coeff) != 1) {
                coeffStr = Integer.toString(Math.abs(coeff));
            } else {
                coeffStr = "";
            }

            if (sb.length() == 0) {
                sb.append(coeff < 0 ? "-" : "").append(coeffStr).append(termVar);
            } else {
                sb.append(coeff < 0 ? " - " : " + ").append(coeffStr).append(termVar);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * Returns a new SimplePolynomial that is the sum of this polynomial and p.
     */
    public SimplePolynomial add(SimplePolynomial p) {
        int size = Math.max(coefficients.size(), p.coefficients.size());
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int a = i < coefficients.size() ? coefficients.get(i) : 0;
            int b = i < p.coefficients.size() ? p.coefficients.get(i) : 0;
            result.add(a + b);
        }
        return new SimplePolynomial(normalize(result));
    }

    /**
     * Returns a new SimplePolynomial that is the sum of this polynomial and the
     * term (coefficient * x^exponent).
     */
    public SimplePolynomial add(int coefficient, int exponent) {
        List<Integer> result = new ArrayList<>(coefficients);
        while (result.size() <= exponent) {
            result.add(0);
        }
        result.set(exponent, result.get(exponent) + coefficient);
        return new SimplePolynomial(normalize(result));
    }

    @Override
    public int compareTo(SimplePolynomial p) {
        List<Integer> a = normalize(coefficients);
        List<Integer> b = normalize(p.coefficients);

        // Lower degree comes first
        if (a.size() != b.size()) {
            return Integer.compare(a.size(), b.size());
        }

        // Same degree: compare coefficients from highest exponent down
        for (int i = a.size() - 1; i >= 0; i--) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) {
                return cmp;
            }
        }
        return 0;
    }
}
