package common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data

public class Book {

    private String id;

    private String name;

    private String type;

    private BigDecimal bigDecimalNum;

    public Book(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Book() {
    }

    public Book(String id, String name, String type, BigDecimal bigDecimalNum) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.bigDecimalNum = bigDecimalNum;
    }
}
