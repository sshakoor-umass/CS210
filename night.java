public class night {
    
}
public static <E> int countOccurences(List<E> nums, E target)
{
    int count = 0;
    for (E x:nums)
    {
        if (x.equals(target))
        {
            count++;
        }
    }
    return count;
}
public void add (E value)
{
    
}