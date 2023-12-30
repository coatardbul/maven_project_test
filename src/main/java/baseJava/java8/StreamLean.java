package baseJava.java8;

import com.google.common.base.Function;
import common.entity.Book;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamLean extends SuperLean {


    @Test
    public void staticMethod(){
        List<String> list = Arrays.asList("2022-01-04", "2022-01-02", "2022-01-03");
        list= list.stream().sorted().collect(Collectors.toList());
System.out.println(list);

String datestr="2022年02月03日";
    String hello = "[\u4E00-\u9FA5]";
    System.out.println(datestr.replaceAll(hello,"-"));

    }
    @Test
    public void filterInfo(){
        List<Book> bookList = new ColletorLean().getBookList();
        bookList.stream().filter(p->p.getType().equals("科幻")).forEach(System.out::println);
    }
    public static void  print(String content){
        System.out.println(content);
    }
    public  void  print2(String content){
        System.out.println(content);
    }
}
