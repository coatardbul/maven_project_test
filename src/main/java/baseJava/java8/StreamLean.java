package baseJava.java8;

import com.google.common.base.Function;
import common.entity.Book;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StreamLean extends SuperLean {


    @Test
    public void staticMethod(){
        List<String> list = Arrays.asList("aaaa", "bbbb", "cccc");

        //静态方法语法	ClassName::methodName
        list.forEach(StreamLean::print);
        //对象实例语法
//        list.forEach(new StreamLean()::print1);
//        //对象的超类方法语法
//        list.forEach(super::print1);
//        list.forEach(System.out::println);

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
