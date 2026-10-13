/*
 * Copyright 2026 Marc Liberatore.
 */

package comparables;

public class Coin implements Comparable<Coin> {
    double value;

    public Coin(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Coin other = (Coin) obj;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Coin [value=" + value + "]";
    }

    @Override
    public int compareTo(Coin otherCoin) {
        // TODO: Define a sensible natural ordering on coins 
        //       (smaller values come before larger)
        return this.value - otherCoin.value;
    }    
}
