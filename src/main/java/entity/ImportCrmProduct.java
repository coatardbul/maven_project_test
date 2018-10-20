package JDBCTest;


import java.io.Serializable;

public class ImportCrmProduct extends ImportCrmProductParent implements Cloneable,Serializable {

    /**
     * 产品名称
     */
    private String prodName;

    public ImportCrmProduct() {
        super();
    }



    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public String toString() {
        return "ImportCrmProduct{" +
                "prodName='" + prodName + '\'' +super.toString()+
                '}';
    }
}
