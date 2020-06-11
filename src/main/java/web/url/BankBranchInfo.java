package web.url;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankBranchInfo {
    /**
     * 省id
     */
    private String id;
    /**
     *
     */
    private String cityName;

    private String branchNo;

    private String branchAddr;

    private String branchProviceCity;

}
