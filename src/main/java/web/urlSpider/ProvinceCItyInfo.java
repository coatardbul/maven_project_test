package web.urlSpider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceCItyInfo {
    // id":"37","pid":"3","area_id":"1210","name":"\u77f3\u5bb6\u5e84\u5e02","sid":"37

   
    /**
     * 省名称
     */
    private String pid;
    /**
     * 联行号4位地址
     */
    private String area_id;
    /**
     * 市名称
     */
    private String name;
    /**
     * 市id
     */
    private String sid;

}
