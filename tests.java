import java.util.List;

public List<Integer> greaterThan(List<Integer>l) {
    list<Integer> result = new ArrayList<>();
    for (int i = 0; i < l.size(); i++){
        if (l.get(i) > 10) {
            result.add(l.get(i));
        }
    }
    return result;
}