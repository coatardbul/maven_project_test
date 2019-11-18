
package IO;

/**
 * @author: suxiaolei
 * @date: 2019/8/29
 */
public class BodyName {
    private String name;

    private String property;

    private String declare;

    @Override
    public String toString() {
        if (name == null || "".equals(name)) {
            return "";
        } else {

                return  name.substring(0, 1).toLowerCase() + name.substring(1, name.length()) +"\n";


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
