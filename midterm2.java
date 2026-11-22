public class midterm2 {
    public static Map<String, Integer> countWords(List<String> words) {
        // Your implementation
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (String word:words)
        {
            if (res.containsKey(word))
            {
                // Word's already there
                res.put(word,res.get(word)+1);
            }
            else
            {
                res.put(word,1);
            }
        }
        return res;
    }
}