package baseJava.java8;

import common.entity.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ColletorLean {

    @Test
    public void toMap() {
        List<Book> bookList = getBookList();
        Map<String, String> collect = bookList.stream().collect(Collectors.toMap(Book::getType, Book::getName,(o1,o2)->o2));
        Map<String, String> collect1 = bookList.stream().collect(Collectors.toMap(Book::getType,   Book::getName,(o1, o2)->o2,ConcurrentHashMap::new));
        System.out.println(collect);
        System.out.println(collect1);

    }

    public List<Book> getBookList() {
        Book b1 = new Book("1", "天空之城1", "玄幻");
        Book b2 = new Book("2", "天空之城2", "玄幻");
        Book b3 = new Book("3", "天空之城3", "玄幻");
        Book b4 = new Book("4", "天空之城4", "玄幻");
        Book b5 = new Book("5", "三体1", "科幻");
        Book b6 = new Book("6", "三体2", "科幻");
        List<Book> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.add(b6);
        return list;
    }
}
