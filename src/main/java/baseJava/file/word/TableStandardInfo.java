package baseJava.file.word;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableStandardInfo extends TableInfo{
    /**
     * 表名称
     */
    private String TableName;
}
