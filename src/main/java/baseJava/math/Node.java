package baseJava.math;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2021/8/20
 *
 * @author Su Xiaolei
 */
public class Node {

    private Node previousNode;

    private Node nextNode;

    private int value;

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
