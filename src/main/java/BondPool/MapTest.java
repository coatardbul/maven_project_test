package BondPool;

import org.junit.Test;

import java.util.*;

public class MapTest {
    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("sb", 21321);
        map.put("hello", 21321);
        map.put("eret", 21321);
        Set<String> s = map.keySet();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if ("sb".equals((String) o)) {
                i.remove();
            }
        }
        System.out.println(map);

    }

    @Test
    public void test3(){
        Map<String, Object> map = new HashMap<>();
        map.put("sb", 21321);
        map.put("hello", 21321);
        map.put("eret", 21321);
      for(String str:map.keySet()){
          if(str.equals("sb")){
              map.put(str,"dfsdf");
             map.put("sdsdsd",1213212);
              map.put(str,null);
              break;
          }

      }
      map.clear();
        System.out.println(map);
    }
}
