package web.url;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceCItyInfo {
    // id":"37","pid":"3","area_id":"1210","name":"\u77f3\u5bb6\u5e84\u5e02","sid":"37

    private String id;

    private String idName;
    private String pid;

    private String area_id;

    private String name;

    private String sid;

}
