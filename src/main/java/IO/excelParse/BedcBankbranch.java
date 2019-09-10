package IO.excelParse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedcBankbranch {

    /**
     * 分支机构在本银行的编码
     */
    private String branchcode;

    /**
     * 银行分支机构名称
     */
    private String bankcode;

    /**
     * 分支机构在本银行的编码
     */
    private String branchname;

    /**
     * 分支机构地址
     */
    private String branchno;

    /**
     * 机构名称助记码
     */
    private String searchcode;

    /**
     * 省
     */
    private String platproname;

    /**
     * 代码
     */
    private String platprocode;

    /**
     * 市
     */
    private String platareaname;

    /**
     * 代码
     */
    private String platareacode;

    /**
     * 银行内部清算号
     */
    private String bankinnerclearno;

    /**
     * 国家统一行别代码
     */
    private String bankcodeunit;

    /**
     * 状态1-正常展示
     */
    private String status;

    /**
     * 创建用户
     */
    private String createuser;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 最后修改用户
     */
    private String lastupdateuser;

    /**
     * 最后修改时间
     */
    private Date lastupdatetime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedcBankbranch that = (BedcBankbranch) o;
        return branchcode.equals(that.branchcode);
    }

    @Override
    public int hashCode() {
        return branchcode.hashCode();
    }
}