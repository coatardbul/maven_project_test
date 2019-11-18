
package leetCode.num17;

import java.util.List;

/**
 * @author: suxiaolei
 * @date: 2019/9/11
 */
public class Node {
    private Node fatherNode;
    private Node childNode;
    private List<String> dateList;

    public Node getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(Node fatherNode) {
        this.fatherNode = fatherNode;
    }

    public Node getChildNode() {
        return childNode;
    }

    public void setChildNode(Node childNode) {
        this.childNode = childNode;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

//    public Node getFatherNode(Node currentNode){
//        return fatherNode;
//    }
//    public Node getChildNode(Node currentNode){
//        return childNode;
//    }
//    public void setFatherNode(Node fatherNode){
//        this.fatherNode=fatherNode;
//    }
//    public void  setChildNode(Node childNode){
//        this.childNode=childNode;
//    }
//
//    public List<String> getDateList() {
//        return dateList;
//    }
//
//    public void setDateList(List<String> dateList) {
//        this.dateList = dateList;
//    }
}
