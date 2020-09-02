package baseJava.collection;

import common.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    public static void main(String[] args) {
        List<User> result = new ArrayList<>();

        result.add(new User(1, "1", "123", null, null));
        result.add(new User(2, "2", "123", null, null));
        result.add(new User(3, "3", "123", null, null));
        result.add(new User(4, "4", "123", null, null));
        result.add(new User(5, "5", "123", null, null));
        result.add(new User(6, "6", "123", null, null));

        System.out.println(result);
        result = result.stream().sorted(Comparator.comparing(User::getUserName).reversed()).collect(Collectors.toList());
        System.out.println(result);

    }

    private String queryStringList(User user) {
        return user.getAddr();
    }

    private static User getUser() {
//        User user = new User();
//        user.setId(new Random().nextLong());
//        user.setUserName(String.valueOf(new Random().nextInt()));
//        return user;
return null;
    }
}
