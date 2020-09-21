package network.urlSpider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankBranchCollectionInfo {
    /**
     * 市id
     */
    private String id;

    private Map<String,BankBranchInfo> map;
}
