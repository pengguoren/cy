package collect;


public class Linkedlist01 {
    private Node first;
    private Node last;
    private int size;

    /**
     * 添加node节点
     * @param o
     */
    public void add(Object o) {
        Node node = new Node(o);
        if(first == null){
            first = node;
            last = node;
        }else{
            node.previos = last;
            node.next =null;
            last.next=node;
            last =node;
        }
        size++;
    }

    /**
     * 获取指定位置的节点
     * @return
     */
    public Object get( int index){
        Node temp = null;
        if(index<=(size>>1)){
            temp = first;
            for (int i = 0; i <index ; i++) {
                temp = temp.next;
            }
        }else{
            temp = last;
            for (int i =size-1 ;i >index ; i++) {
                temp = temp.previos;
            }
        }
       return temp.element;
    }

    @Override
    public String toString() {
        /*
        * Node temp = first;
        * while(!temp==null){
        *   temp.element;
        *   temp =first.next;
        *   }
        *
        * */
        StringBuilder sb = new StringBuilder("[");
        Node temp = first;
        for (int i = 0; i <size ; i++) {
            sb.append(temp.element+",");
            temp = first.next;
        }
        sb.setCharAt(sb.length()-1,']');
        System.out.println(sb);
        return super.toString();
    }

    public static void main(String[] args) {
        Linkedlist01 list = new Linkedlist01();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.size);
        System.out.println(list);
        System.out.println(list.get(1));

    }

}
