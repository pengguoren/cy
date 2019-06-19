package collect;

public class Node {
    Node previos;
    Node next;
    Object element;

    public Node(Node previos, Node next, Object element) {
        this.previos = previos;
        this.next = next;
        this.element = element;
    }

    public Node(Object element) {
        this.element = element;
    }
}
