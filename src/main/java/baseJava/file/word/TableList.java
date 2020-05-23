package baseJava.file.word;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableList {

    /**
     * 表序列
     */
    private String id;
    /**
     * 表系统
     */
    private String TableSystem;
    /**
     * 表名称
     */
    private String TableName;
    /**
     * 表名称
     */
    private String TableCommont;
    /**
     * 表是否公用
     */
    private String TableStyle;

}
