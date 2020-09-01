package baseJava.collection;

import common.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    public static void main(String[] args) {
//        Vector v = new Vector();
//        v.add("12312");
//
//
//        List<String> list = new ArrayList<>();
//        list.add("as");
//
//        Set<String> s = new HashSet<>();


        User user = new User();
        user.setId(11111);
        user.setUserName("11111");
        user.setAddr("11111");

        User user1 = new User();
        user1.setId(22222);
        user1.setUserName("22222");
        user1.setAddr("22222");

        User user2 = new User();
        user2.setId(33333);
        user2.setUserName("33333");
        user2.setAddr("33333");



        List<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);


//        List<String> collect = userList.stream().map(ListTest::queryStringList).collect(Collectors.toList());
//        System.out.println(collect);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> "efg".equals(string)).collect(Collectors.toList());
        System.out.println(filtered);
    }
    private String queryStringList (User user){
        return user.getAddr();
    }
}
