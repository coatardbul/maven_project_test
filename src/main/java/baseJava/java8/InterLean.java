package baseJava.java8;

import common.entity.Book;
import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class InterLean {

    @Test
    public void predicateTest() {
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }

    @Test
    public void functionLean() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");
    }

    @Test
    public void supplierLean() {
        Supplier<Book> personSupplier = Book::new;
        personSupplier.get();   // new Person
    }
    @Test
    public void consumerLean() {
        Consumer<Book> greeter = (p) -> System.out.println("Hello, " + p.getId());
        greeter.accept(new Book("1", "天空之城1", "玄幻"));
    }

    @Test
    public void comparatorLean() {
        Comparator<Book> comparator = (p1, p2) -> p1.getId().compareTo(p2.getId());

        Book p1 = new Book("1", "天空之城1", "玄幻");
        Book p2 = new Book("2", "天空之城2", "玄幻");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);
    }

    @Test
    public void optionalLean() {
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }
}
