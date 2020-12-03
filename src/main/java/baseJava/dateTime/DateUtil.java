package baseJava.dateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    public static void main(String[] args) {
String str="2018-01-01";

            boolean matches = str.matches("\\d{4}[-]{1}\\d{2}[-]{1}\\d{2}");
            System.out.println(matches);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = df.parse(str);
            System.out.println(parse.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);
new Date().getTime();
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy--dd HH:mm:ss");
        LocalDateTime l=LocalDateTime.now();
        LocalDateTime ll =l.withNano(111111111);
        System.out.println(System.currentTimeMillis());
        System.currentTimeMillis();

        System.out.println(l);
        System.out.println(ll);


    }

}
