package hw6.AVL;

import java.util.*;

class Node{
    double value;
    int height;
    Node left;
    Node right;

    Node(double value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

public class AVL {

    private Node root;

    int height(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }


    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node N) {

        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Node _add(Node node, double value) {

        if (node == null)
            return (new Node(value));

        if (value < node.value)
            node.left = _add(node.left, value);

        else if (value > node.value)
            node.right = _add(node.right, value);

        else {
            System.out.println(String.format("%.6f", value) + " already added");
            return node;

        }

        node.height = 1 + max(height(node.left),
                height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        if (balance > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    Node minValue(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    Node _remove(Node root, double value) {

        if (root == null) {
            System.out.println(String.format("%.6f", value) + " not found to remove");
            return root;

        }

        if (value < root.value)
            root.left = _remove(root.left, value);

        else if (value > root.value)
            root.right = _remove(root.right, value);

        else {

            if ((root.left == null) || (root.right == null)) {
                Node temp = null;

                if (temp == root.left)
                    temp = root.right;

                else
                    temp = root.left;

                if (temp == null) {
                    root = null;

                } else
                    root = temp;

            } else {
                Node temp = minValue(root.right);
                root.value = temp.value;
                root.right = _remove(root.right, temp.value);

            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);

        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);

        }

        return root;
    }

    private boolean containsRecursive(Node current, double value) {
        if (current == null) {
            return false;
        }

        if (current.value == value) {
            return true;
        }

        if (value < current.value) {
            return containsRecursive(current.left, value);

        } else {
            return containsRecursive(current.right, value);

        }
    }

    private void printRecursive(Node current) {
        if (current == null) {
            return;
        }

        printRecursive(current.left);
        System.out.printf("%.6f", current.value);
        System.out.print(" ");
        printRecursive(current.right);
    }

    public AVL() {
        root = null;
    }

    public void add(double value) {
        root = _add(root, value);
    }

    public void remove(double value) {
        root = _remove(root, value);
    }


    public boolean contains(double value) {
        if (root == null) {
            return false;
        }

        return containsRecursive(root, value);
    }

    public void print() {
        if (root == null) {
            return;
        }

        printRecursive(root);
        System.out.println(" ");
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in) ;
        AVL tree = new AVL();
        String operation;
        double number;
        int N = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            operation = scanner.next();

            if(!operation.equals("print")){
                number = scanner.nextDouble();

                if(operation.equals("add")) {
                    tree.add(number);

                } else if(operation.equals("remove")) {
                    tree.remove(number);

                } else if(operation.equals("contains")) {

                    if (tree.contains(number)) {
                        System.out.println("yes");

                    } else {
                        System.out.println("no");

                    }
                }

            } else{
                tree.print();

            }
        }
    }
}
