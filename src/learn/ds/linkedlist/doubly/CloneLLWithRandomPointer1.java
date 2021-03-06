package learn.ds.linkedlist.doubly;

/**
 * @author Varma Penmetsa
 *
 * https://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
 */
public class CloneLLWithRandomPointer1 {

    Node head;

    public CloneLLWithRandomPointer1(int data){
        head = new Node(data);
    }

    public class Node{
        Node next,random;
        int data;

        public Node(int data){
            this.data = data;
            next = random = null;
        }
    }

    void print() {
        Node temp = head;
        while (temp != null) {
            int randomData = (temp.random != null) ? temp.random.data : -1;
            System.out.println("Data = " + temp.data + ", Random data = " + randomData);
            temp = temp.next;
        }
    }

    void print(Node head) {
        Node temp = head;
        while (temp != null) {
            int randomData = (temp.random != null) ? temp.random.data : -1;
            System.out.println("Data = " + temp.data + ", Random data = " + randomData);
            temp = temp.next;
        }
    }

    public void push(int data) {
        Node new_node = new Node(data);
        new_node.next = head;
        head = new_node;
    }

    //Use constant extra space
    public Node clone(){
        Node orig = head;
        Node clone = null;

        while(orig!=null ){
            clone = new Node(orig.data);
            clone.next = orig.next;
            orig.next = clone;
            orig = clone.next;
        }

        orig = head;
        while(orig!=null ){
            if (orig.random != null){
                orig.next.random = orig.random.next;
            }
            orig=orig.next.next;
        }

        //Breaking List
        orig = head;
        clone = head.next;
        while(orig!=null && orig.next.next!=null){
            Node temp = orig.next;
            orig.next = temp.next;
            temp.next = temp.next.next;
            orig = orig.next;
        }
        return clone;
    }

    public static void main(String[] args){

        CloneLLWithRandomPointer1 list = new CloneLLWithRandomPointer1(5);

        list.push(4);
        list.push(3);
        list.push(2);
        list.push(1);

        // Setting up random references.
        list.head.random = list.head.next.next;
        list.head.next.random = list.head.next.next.next;
        list.head.next.next.random = list.head.next.next.next.next;
        list.head.next.next.next.random = list.head.next.next.next.next.next;
        list.head.next.next.next.next.random = list.head.next;

        list.print();
        System.out.println();
        list.print(list.clone());

    }

}
