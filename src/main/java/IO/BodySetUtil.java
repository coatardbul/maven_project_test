
package IO;

/**
 * @author: suxiaolei
 * @date: 2019/6/25
 */
public class BodySetUtil {

    private String name;

    private String property;

    private String declare;

    @Override
    public String toString() {
        if (name == null || "".equals(name)) {
            return "";
        } else {
            if (property == null || "".equals(property)) {
                return " //非必填  " + declare + "\n" +
                        "xxxxx.set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()) + "(\"\");" + "\n";
            } else {
                return " //必填  " + declare + "\n" +
                        "xxxxx.set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()) + "(\"\");" + "\n";
            }

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
