package genericity;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class genericityUtil<E> {

    public static  <T> List<T> removeAllOptimize(List<T> source, List<T> destination) {
        List<T> result = new LinkedList<T>();
        Set<T> destinationSet = new HashSet<T>(destination);
        for(T t : source) {
            if (!destinationSet.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }

//    public static  void removeAllOpt123dsadimize(List<? extends E> source, List<? extends E> destination) {
//
//    }
}
