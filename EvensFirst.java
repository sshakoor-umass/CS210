import java.util.Comparator;

public class EvensFirst implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
        if (a % 2 == 0 && b % 2 != 0) {
            return -1;
        } else if (a % 2 != 0 && b % 2 == 0) {
            return 1;
        } 
        return Integer.compare(a, b);
    }
}
