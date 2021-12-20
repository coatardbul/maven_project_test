package baseJava.java8;

import common.entity.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ColletorLean {

    @Test
    public void toMap() {
        List<Book> bookList = getBookList();
        Map<String, String> collect = bookList.stream().collect(Collectors.toMap(Book::getType, Book::getName, (o1, o2) -> o2));
        Map<String, String> collect1 = bookList.stream().collect(Collectors.toMap(Book::getType, Book::getName, (o1, o2) -> o2, ConcurrentHashMap::new));
        bookList.stream().collect(Collectors.toMap(Book::getType, Book::getName, (o1, o2) -> o2));

        System.out.println(collect);
        System.out.println(collect1);

        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date time = calendar.getTime();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = s.format(time);
        System.out.println("开始时间："+format);

    }

    @Test
    public void mapping() {
        List<Book> bookList = getBookList();
        List<String> collect = bookList.stream().collect(Collectors.mapping(Book::getType, Collectors.toList()));
int num=10;
int s=30;

BigDecimal ss=new BigDecimal(1.623);
        System.out.println(ss.intValue());

System.out.println(num%s);

    }

    @Test
    public void count() {
        List<Book> bookList = getBookList();
        long count = bookList.stream().filter(book -> book.getType().equals("玄幻")).count();
        System.out.println(count);
    }

    @Test
    public void filter() {
        List<Book> bookList = getBookListNull();
        List<Book> collect = bookList.stream().filter(x -> x.getId() == "14").collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void collectingAndThen() {
        List<Book> bookList = getBookList();
        bookList.stream().collect(Collectors.groupingBy(Book::getType, Collectors.collectingAndThen(Collectors.reducing((o1, o2) -> Short.valueOf(o1.getId()).shortValue() > Short.valueOf(o2.getId()).shortValue() ? o1 : o2), x -> {
            List list = new ArrayList();
            list.add(x.get());
            return list;
        }))).forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
        bookList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)).forEach(System.out::println);

    }
@Test
public void  test1(){

}
    @Test
    public void sum() {
        List<Book> bookList = getBookList();
        BigDecimal bigDecimal = bookList.stream().map(Book::getBigDecimalNum).collect(Collectors.toList()).stream().reduce(BigDecimal.ZERO, (x1, x2) -> {
            if (x2 == null) {
                return x1;
            }
            if (x1 == null) {
                return x2;
            }
            return x1.add(x2);
        });
        System.out.println(bigDecimal);
    }

    private String convertToString(Object v) {
        return v.toString();
    }

    @Test
    public void reducing() {
        List<Book> bookList = getBookList();
        Optional<Book> collect = bookList.stream().collect(Collectors.reducing((o1, o2) -> Short.valueOf(o1.getId()).shortValue() > Short.valueOf(o2.getId()).shortValue() ? o1 : o2));
        System.out.println(collect.get());


        // sum: 是每次累计计算的结果，b是Function的结果
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(0, x -> x, (sum, b) -> {
            System.out.println("########" + sum + "-" + b);
            return sum + b;
        })));


        // 下面代码是对reducing函数功能实现的描述，用于理解reducing的功能
        int sum = 0;
        List<Integer> list3 = Arrays.asList(1, 3, 4);
        for (Integer item : list3) {
            int b = item + 1;
            System.out.println(sum + "-" + b);
            sum = sum + b;
        }
        System.out.println(sum);


        // 注意reducing可以用于更复杂的累计计算，加减乘除或者更复杂的操作
        // result = 2 * 4 * 5 = 40
        System.out.println(Stream.of(1, 3, 4).collect(Collectors.reducing(1, x -> x + 1, (result, b) -> {
            System.out.println(result + "-" + b);
            return result * b;
        })));

    }

    public List<Book> getBookList() {
        Book b1 = new Book("1", "天空之城1", "玄幻", new BigDecimal("12.1"));
        Book b2 = new Book("2", "天空之城2", "玄幻", new BigDecimal("12.1"));
        Book b3 = new Book("3", "天空之城3", "玄幻", new BigDecimal("12.1"));
        Book b4 = new Book("4", "天空之城4", "玄幻", new BigDecimal("12.1"));
        Book b5 = new Book("5", "三体1", "科幻", new BigDecimal("12.2"));
        Book b6 = new Book("6", "三体2", "科幻", new BigDecimal("12.1"));
        b6.setBigDecimalNum(null);
        List<Book> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.add(b6);
        return list;
    }

    public List<Book> getBookListNull() {
        return null;
    }
}
