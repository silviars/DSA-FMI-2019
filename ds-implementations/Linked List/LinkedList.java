package LinkedList;

import java.util.Stack;

public class LinkedList {
    Node head;
    Node tail;

    public LinkedList(){
        this.head = null;
       this.tail = null;
    }

    public class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public void insert(int data){
        Node node = new Node(data);

        if(this.head == null){
            this.head = node;
        }
        else {
            node.next = head;
            head = node;
        }

    }

    public void insertAt(int data, int position){

        if(position == 0)
        {
            insert(data);
            return;
        }
        if(position<0)
        {
            insert(data);
            return;
        }
        Node newNode = new Node(data);
        Node currNode = this.head;

        int i = 0;
        while(i < position - 1){
            if(currNode==null )
            {
                insert(data);
                return;
            }
            currNode = currNode.next;
            i++;
        }

        newNode.next = currNode.next;
        currNode.next = newNode;
    }

     void removeFrom(int pos){
        if(head == null) System.out.println("Empty stack");
       // if(pos == 0)

        int i = 0;
        Node currNode = this.head;
        while(i < pos - 1){
            currNode = currNode.next;
            i++;
        }

        currNode.next = currNode.next.next;
    }

    //works
    void print(){
        Node node = this.head;
        if(this.head != null) {
            while (node != null) {
                System.out.print(node.data + "#");
                node = node.next;
            }
        }
    }

    void reverse(){
        Node prev = null;
        Node curr = this.head;
        Node next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        this.head = prev;
    }



    //start from head
    void isPalindrome() {
        Node head = this.head;
        Node temp = this.head;
        boolean isPal = true;
        Stack<Integer> stack = new Stack<>();

        while(temp != null){
            stack.push(temp.data);
            temp = temp.next;
        }

        while(head != null) {
            int i = stack.pop();
            if (head.data != i) {
                isPal = false;
                break;
            }
            head = head.next;
        }

        if(isPal) System.out.println("true");
        else System.out.println("false");
    }
}



class LLapp{
    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(1);
        list.insert(4);
        list.insert(1);
        list.print();
        System.out.println(" ");

        list.insertAt(1, 1);
        list.print();
        System.out.println(" ");

        list.removeFrom(4 );
        list.print();
        System.out.println(" ");

        list.reverse();
        list.print();
        System.out.println(" ");

        list.isPalindrome();
    }
}
