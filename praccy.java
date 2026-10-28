import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class praccy {

    static Map<Integer, List<String>> groupByLength(List<String> words) {
        // Your implementation
        Map <Integer, List<String>> res = new HashMap<Integer, List<String>>();
        int w_length = 0;
        for (String word: words)
        {
            w_length = word.length();
            if (!res.containsKey(w_length)){
                List <String> fill = new ArrayList<String>();
                res.put(w_length, fill);
            }
            List<String> group = res.get(w_length);
            group.add(word);
        }
        return res;
    }
}