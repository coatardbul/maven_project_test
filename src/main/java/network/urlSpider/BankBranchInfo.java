package network.urlSpider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankBranchInfo {
    private String cityId;

    private String branchNo;

    private String branchAddr;

    private String branchProviceCityAddr;

}
