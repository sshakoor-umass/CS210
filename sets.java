public static void main (String[] args)
{

}
static <E> Set<E> intersection(Set<E> s, Set<E> t) {
    Set<E> result = new HashSet<E>();
    for (E element : s){
        if (t.contains(element)){
            result.add(element);
        }
    }
    return result;
}