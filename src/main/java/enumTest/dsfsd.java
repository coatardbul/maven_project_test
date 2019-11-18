
package enumTest;



public class dsfsd {
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ssSSSSSS";
    public static void main(String[] args) {

        System.out.println(java.time.LocalDateTime.now().format((java.time.format.DateTimeFormatter.ofPattern(DATE_TIME))))  ;
    }
}
