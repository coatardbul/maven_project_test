
package IO;

/**
 * @author: suxiaolei
 * @date: 2019/6/25
 */
public class BodyUnit {

    private  String name;

    private String  property;

    private  String declare;

    /**
     *
     * @return
     */
    @Override
    public String toString() {

        if(property==null || "".equals(property)){
            return "/**\n" +
                    "     * \n" +declare+
                    "     */\n"+
                    " private String "+name+";\n";
        }else {
            return "/**\n" +
                    "     * \n" +declare+
                    "     */\n"+
                    "@NotBlank(message = \""+declare+"不能为空\")"+
                    " private String "+name+";\n";
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDeclare() {
        return declare;
    }

    public void setDeclare(String declare) {
        this.declare = declare;
    }
}
