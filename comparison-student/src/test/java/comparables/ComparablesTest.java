/*
 * Copyright 2026 Marc Liberatore.
 */

package comparables;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


// @Timeout(10)
public class ComparablesTest {

    // -------------------------------------------------------------------------
    // Coin tests
    // -------------------------------------------------------------------------

    final Coin penny   = new Coin(1);
    final Coin nickel  = new Coin(5);
    final Coin dime    = new Coin(10);
    final Coin quarter = new Coin(25);
    final Coin penny2  = new Coin(1);

    @Test
    public void testCoinIdentical() {
        assertEquals(0, penny.compareTo(penny));
    }

    @Test
    public void testCoinEqualValue() {
        assertEquals(0, penny.compareTo(penny2));
        assertEquals(0, penny2.compareTo(penny));
    }

    @Test
    public void testCoinSmallerBeforeLarger() {
        assertTrue(penny.compareTo(nickel) < 0);
        assertTrue(nickel.compareTo(penny) > 0);
    }

    @Test
    public void testCoinOrdering() {
        assertTrue(penny.compareTo(dime) < 0);
        assertTrue(dime.compareTo(quarter) < 0);
        assertTrue(nickel.compareTo(quarter) < 0);
        assertTrue(quarter.compareTo(nickel) > 0);
    }

    // -------------------------------------------------------------------------
    // Movie tests
    // -------------------------------------------------------------------------

    // Different years
    final Movie oldMovie    = new Movie(1950, 8.0, "Sunset Boulevard");
    final Movie recentMovie = new Movie(2000, 7.0, "Gladiator");

    // Same year, different ratings
    final Movie goodMovie2000  = new Movie(2000, 9.0, "Almost Famous");
    final Movie okayMovie2000  = new Movie(2000, 5.0, "Battlefield Earth");

    // Same year, same rating, different titles
    final Movie alphaFirst  = new Movie(1999, 7.5, "American Beauty");
    final Movie alphaSecond = new Movie(1999, 7.5, "Being John Malkovich");

    // Same year, same rating, same title
    final Movie twin1 = new Movie(2010, 8.5, "Inception");
    final Movie twin2 = new Movie(2010, 8.5, "Inception");

    @Test
    public void testMovieIdentical() {
        assertEquals(0, oldMovie.compareTo(oldMovie));
    }

    @Test
    public void testMovieEqual() {
        assertEquals(0, twin1.compareTo(twin2));
        assertEquals(0, twin2.compareTo(twin1));
    }

    @Test
    public void testMovieOldestFirst() {
        assertTrue(oldMovie.compareTo(recentMovie) < 0);
        assertTrue(recentMovie.compareTo(oldMovie) > 0);
    }

    @Test
    public void testMovieRatingTiebreaker() {
        // Same year: higher rating comes first (i.e., compares as less-than)
        assertTrue(goodMovie2000.compareTo(okayMovie2000) < 0);
        assertTrue(okayMovie2000.compareTo(goodMovie2000) > 0);
    }

    @Test
    public void testMovieTitleTiebreaker() {
        // Same year and rating: lexicographic by title
        assertTrue(alphaFirst.compareTo(alphaSecond) < 0);
        assertTrue(alphaSecond.compareTo(alphaFirst) > 0);
    }

    @Test
    public void testMovieYearBeforeRating() {
        // A movie with a worse rating but earlier year should still come first
        Movie earlyLowRated = new Movie(1980, 3.0, "Xanadu");
        Movie lateBestRated = new Movie(2010, 9.9, "Toy Story 3");
        assertTrue(earlyLowRated.compareTo(lateBestRated) < 0);
        assertTrue(lateBestRated.compareTo(earlyLowRated) > 0);
    }

    @Test
    public void testMovieRatingBeforeTitle() {
        // Same year: a higher-rated movie with a later title still comes first
        Movie highRatedLateTitle = new Movie(2005, 9.0, "Z Movie");
        Movie lowRatedEarlyTitle = new Movie(2005, 5.0, "A Movie");
        assertTrue(highRatedLateTitle.compareTo(lowRatedEarlyTitle) < 0);
        assertTrue(lowRatedEarlyTitle.compareTo(highRatedLateTitle) > 0);
    }

    // -------------------------------------------------------------------------
    // SimplePolynomial tests
    // -------------------------------------------------------------------------

    // Helpers
    private static SimplePolynomial poly(Integer... coeffs) {
        return new SimplePolynomial(Arrays.asList(coeffs));
    }

    final SimplePolynomial zero        = poly(0);           // 0
    final SimplePolynomial one         = poly(1);           // 1
    final SimplePolynomial negOne      = poly(-1);          // -1
    final SimplePolynomial five        = poly(5);           // 5
    final SimplePolynomial x           = poly(0, 1);        // x
    final SimplePolynomial xPlus1      = poly(1, 1);        // x + 1
    final SimplePolynomial xMinus1     = poly(-1, 1);       // x - 1
    final SimplePolynomial twoX        = poly(0, 2);        // 2x
    final SimplePolynomial xSquared    = poly(0, 0, 1);     // x^2
    final SimplePolynomial xCubed      = poly(0, 0, 0, 1);  // x^3
    final SimplePolynomial xCubedFull  = poly(5, -1, 1, 1); // x^3 + x^2 - x + 5

    // --- equals and trailing-zero normalization ---

    @Test
    public void testPolyEqualsSame() {
        assertEquals(zero, zero);
        assertEquals(xPlus1, xPlus1);
    }

    @Test
    public void testPolyEqualsTrailingZeros() {
        SimplePolynomial zeroWithTrailing = poly(0, 0, 0);
        assertEquals(zero, zeroWithTrailing);
        assertEquals(zeroWithTrailing, zero);

        SimplePolynomial xWithTrailing = poly(0, 1, 0, 0);
        assertEquals(x, xWithTrailing);
        assertEquals(xWithTrailing, x);
    }

    @Test
    public void testPolyNotEqual() {
        assertNotEquals(x, xSquared);
        assertNotEquals(xPlus1, xMinus1);
        assertNotEquals(zero, one);
    }

    // --- compareTo: basic ordering ---

    @Test
    public void testPolyCompareIdentical() {
        assertEquals(0, zero.compareTo(zero));
        assertEquals(0, x.compareTo(x));
        assertEquals(0, xCubedFull.compareTo(xCubedFull));
    }

    @Test
    public void testPolyCompareEqualWithTrailingZeros() {
        SimplePolynomial xWithTrailing = poly(0, 1, 0, 0);
        assertEquals(0, x.compareTo(xWithTrailing));
        assertEquals(0, xWithTrailing.compareTo(x));
    }

    @Test
    public void testPolyCompareDegree() {
        // Higher degree comes after lower degree
        assertTrue(x.compareTo(xSquared) < 0);
        assertTrue(xSquared.compareTo(x) > 0);
        assertTrue(xSquared.compareTo(xCubed) < 0);
        assertTrue(xCubed.compareTo(xSquared) > 0);
    }

    @Test
    public void testPolyCompareConstantVsLinear() {
        assertTrue(five.compareTo(x) < 0);
        assertTrue(x.compareTo(five) > 0);
        // Even a very large constant is less than any non-constant polynomial
        assertTrue(poly(1000).compareTo(x) < 0);
    }

    @Test
    public void testPolyCompareLeadingCoefficient() {
        // Same degree, different leading coefficient
        assertTrue(x.compareTo(twoX) < 0);
        assertTrue(twoX.compareTo(x) > 0);
    }

    @Test
    public void testPolyCompareNegativeLeadingCoefficient() {
        // Negative leading coefficient comes before positive
        assertTrue(xMinus1.compareTo(xPlus1) < 0);
        assertTrue(xPlus1.compareTo(xMinus1) > 0);
        assertTrue(negOne.compareTo(one) < 0);
        assertTrue(one.compareTo(negOne) > 0);
    }

    @Test
    public void testPolyCompareLowerCoefficientTiebreaker() {
        // Same degree and leading coefficient, differ in a lower term
        SimplePolynomial xSquaredPlus1 = poly(1, 0, 1); // x^2 + 1
        SimplePolynomial xSquaredPlus2 = poly(2, 0, 1); // x^2 + 2
        assertTrue(xSquaredPlus1.compareTo(xSquaredPlus2) < 0);
        assertTrue(xSquaredPlus2.compareTo(xSquaredPlus1) > 0);

        // Differ only in x term
        SimplePolynomial withX    = poly(0, 1, 1); // x^2 + x
        SimplePolynomial withoutX = xSquared; // x^2
        assertTrue(xSquared.compareTo(withX) < 0);
        assertTrue(withX.compareTo(withoutX) > 0);
    }

    @Test
    public void testPolyCompareMiddleCoefficientTiebreaker() {
        // Same degree and leading coefficient, differ only in the x term
        // x^3 + x^2 + x  vs  x^3 + x^2
        SimplePolynomial withLinear    = poly(0, 1, 1, 1); // x^3 + x^2 + x
        SimplePolynomial withoutLinear = poly(0, 0, 1, 1); // x^3 + x^2
        assertTrue(withoutLinear.compareTo(withLinear) < 0);
        assertTrue(withLinear.compareTo(withoutLinear) > 0);
    }

    @Test
    public void testPolyCompareConstantCoefficientTiebreaker() {
        // Same degree and all higher coefficients equal, differ only in constant term
        // x^3 + x^2 - x + 5  vs  x^3 + x^2 - x + 3
        SimplePolynomial higherConst = poly(5, -1, 1, 1); // x^3 + x^2 - x + 5
        SimplePolynomial lowerConst  = poly(3, -1, 1, 1); // x^3 + x^2 - x + 3
        assertTrue(lowerConst.compareTo(higherConst) < 0);
        assertTrue(higherConst.compareTo(lowerConst) > 0);
    }

    @Test
    public void testPolyCompareDegreeBeforeCoefficients() {
        // A lower-degree polynomial comes before a higher-degree one
        // even if its coefficients are much larger
        SimplePolynomial bigConstant  = poly(1000000);    // 1000000 (degree 0)
        SimplePolynomial smallCubic   = poly(0, 0, 0, 1); // x^3    (degree 3)
        assertTrue(bigConstant.compareTo(smallCubic) < 0);
        assertTrue(smallCubic.compareTo(bigConstant) > 0);

        // Similarly: a degree-2 polynomial beats any degree-1 polynomial
        SimplePolynomial negativeQuadratic = poly(0, 0, -1); // -x^2
        assertTrue(twoX.compareTo(negativeQuadratic) < 0);
        assertTrue(negativeQuadratic.compareTo(twoX) > 0);
    }

    // --- add(SimplePolynomial) ---

    @Test
    public void testPolyAddZero() {
        assertEquals(x, x.add(zero));
        assertEquals(x, zero.add(x));
    }

    @Test
    public void testPolyAddSimple() {
        assertEquals(xPlus1, x.add(one));
        assertEquals(xPlus1, one.add(x));
    }

    @Test
    public void testPolyAddDifferentDegrees() {
        // x^2 + x
        SimplePolynomial expected = poly(0, 1, 1);
        assertEquals(expected, xSquared.add(x));
        assertEquals(expected, x.add(xSquared));
    }

    @Test
    public void testPolyAddCancellation() {
        // x + (-x) = 0
        SimplePolynomial negX = poly(0, -1);
        assertEquals(zero, x.add(negX));

        // (x + 1) + (-x - 1) = 0
        SimplePolynomial negXMinus1 = poly(-1, -1);
        assertEquals(zero, xPlus1.add(negXMinus1));
    }

    @Test
    public void testPolyAddComplex() {
        // (x^3 + x^2 - x + 5) + (x^2 + x) = x^3 + 2x^2 + 5
        SimplePolynomial xSquaredPlusX = poly(0, 1, 1);
        SimplePolynomial expected = poly(5, 0, 2, 1); // x^3 + 2x^2 + 5
        assertEquals(expected, xCubedFull.add(xSquaredPlusX));
    }

    // --- add(int coefficient, int exponent) ---

    @Test
    public void testPolyAddTermToConstant() {
        // 1 + 1*x = x + 1
        assertEquals(xPlus1, one.add(1, 1));
    }

    @Test
    public void testPolyAddTermHighExponent() {
        // 0 + 1*x^3 = x^3
        assertEquals(xCubed, zero.add(1, 3));
    }

    @Test
    public void testPolyAddTermCancels() {
        // x + (-1)*x = 0
        assertEquals(zero, x.add(-1, 1));
    }

    @Test
    public void testPolyAddTermExistingCoeff() {
        // x + 1*x = 2x
        assertEquals(twoX, x.add(1, 1));
        // (x^3 + x^2 - x + 5) + 2*x^2 = x^3 + 3x^2 - x + 5
        SimplePolynomial expected = poly(5, -1, 3, 1);
        assertEquals(expected, xCubedFull.add(2, 2));
    }
}
