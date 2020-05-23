package baseJava.file.word;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableInfo {
    /**
     * 字段英文名称
     */
    private String column;
    /**
     * 字段中文名称
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 字段长度
     */
    private String columnLength;
    /**
     * 字段是否非空
     */
    private String columnIsNotNull;
    /**
     * 字段备注
     */
    private String columnComment;
}
