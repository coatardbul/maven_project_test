package baseJava.java8;

import common.entity.Book;

import java.util.Optional;

public class SuperLean {
    public static void main(String[] args) {
        Book b=new Book();
        Book s=new Book();
        s.setName("sfsdfsdfs");
        String ssss = Optional.ofNullable(s.getName()).orElse("ssss");
        String name = Optional.ofNullable(b).orElse(s).getName();

        System.out.println(ssss);
    }
}
