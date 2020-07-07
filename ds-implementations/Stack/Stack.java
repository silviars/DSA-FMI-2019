package Stack;

public class Stack {

    static final int capacity = 1000;
    int top; // size
    int a[] = new int[capacity];

    Stack(){
        top = -1;
    }

    boolean isEmpty(){
        return top < 0;
    }

    boolean stack_push(int x){
        //if there's no place left for x
        if(top >= capacity - 1){
            System.out.println("Stack Overflow");
            return false;
        } else {
            a[++top] = x;
            System.out.println("add " + x);
            return true;
        }
    }

    int stack_pop(){
        //if stack is empty
        if(top < 0){
            System.out.println("Stack is empty");
            return 0;
        } else { //if not empty we remove the last added element
            System.out.println("Popped " + a[top]);
            int x = a[--top];
            return x;
        }
    }

    int stack_peek(){
        if(top < 0){
            System.out.println("Stack is empty");
            return 0;
        } else {
            return a[top];
        }
    }

    void stack_print(){
        for(int i = 0; i <= top; i++){
            System.out.print(a[i] + " ");
        }
    }



}
class Main{
    public static void main(String[] args){
        Stack stack = new Stack();
        stack.stack_push(5);
        stack.stack_push(6);
        stack.stack_push(7);
        stack.stack_push(8);
        stack.stack_print();
        System.out.println(" ");
        int x = stack.stack_pop();
        System.out.println(x);
        stack.stack_print();
        System.out.println(" ");
        int y = stack.stack_peek();
        System.out.println(y);
        boolean isEmpty = stack.isEmpty();
        System.out.println(isEmpty);

    }
}


