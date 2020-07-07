package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node{
    int data;
    int level;
    Node left, right;

    Node(int data, int level){
        this.data = data;
        this.level = level;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
     Node root;

    BinarySearchTree(){
        this.root = null;
    }

    //insert node with given the tree root and the data
   static Node _insert(Node root, int data, int level) {
        if (root == null) {
            return new Node(data, level);
        } else {
            if (data == root.data) {
                root.left = _insert(root.left, data, level + 1);
            } else if(data != root.data){
                root.right = _insert(root.right, data, level + 1);
            }
        }
        return root;
    }

    //left, root, right
     void _inorder(Node root){
        if(root != null){
            _inorder(root.left);
            System.out.print(root.data + " ");
            _inorder(root.right);
        }
    }

    void _preorder(Node root){
        if(root != null){
            System.out.print(root.data + " ");
            _preorder(root.left);
            _preorder(root.right);
        }
    }

    void _postorder(Node root){
        if(root != null){
            _postorder(root.right);
            _postorder(root.left);
            System.out.print(root.data + " ");
        }
    }

    void _levelorder(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node curr = queue.peek();
            queue.poll();
            System.out.println("Element: " + curr.data + " Level: " + curr.level);

            if(curr.left != null) queue.add(curr.left);
            if(curr.right != null) queue.add(curr.right);
        }
    }

    boolean _exists(Node root, int data){
        if(root != null) {
            if (root.data == data) {
                return true;
            } else if (root.data > data) {
                return _exists(root.left, data);
            } else {
                return _exists(root.right, data);
            }
        }
        return false;
    }

    int _minValue(Node root){
        if(root != null){
            if(root.left != null){
                return _minValue(root.left);
            } else return root.data;
        }
        return -1;
    }
    int _maxValue(Node root){
        if(root != null){
            if(root.right != null){
                return _maxValue(root.right);
            } else return root.data;
        }
        return -1;
    }

    Node _delete(Node root, int data){
        if(root == null){
            return root;
        }
        //check if element exists in the tree
        if(_exists(root, data)) {
            if (data < root.data) root.left = _delete(root.left, data);
            if (data > root.data) root.right = _delete(root.right, data);
            //when we find the node to be deleted
            if (data == root.data) {
                //only right child or no child
                if (root.left == null) return root.right;
                    //only left child
                else if (root.right == null) return root.left;
                else {
                    //replace with min value in right
                    //alternatively we can replace it with max on left
                    root.data = _minValue(root.right);
                    root.right = _delete(root.right, root.data);
                }

            }
        } else System.out.println( data + " is not present in the tree");
        return root;
    }

    void _zigzagorder(Node root){
        if(root == null) return;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);

        //check both, otherwise it will stop ot the 1st level
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            while(!stack1.isEmpty()){
                Node curr = stack1.peek();
                stack1.pop();
                System.out.print(curr.data + " ");

                if(curr.left != null) stack2.push(curr.left);
                if(curr.right != null) stack2.push(curr.right);
            }
            while(!stack2.isEmpty()){
                Node curr = stack2.peek();
                stack2.pop();
                System.out.print(curr.data + " ");

                if(curr.right != null) stack1.push(curr.right);
                if(curr.left != null) stack1.push(curr.left);
    }
}



    }

    //Utility functions
    void insert(int data){
        root = _insert(root, data, 0);
    }
    void inorder(){
        _inorder(root);
    }
    void preorder(){
        _preorder(root);
    }
    void postorder(){
        _postorder(root);
    }
    void levelorder(){
        _levelorder(root);
    }
    boolean exists(int data){
       return _exists(root, data);
    }
    int minValue(){
        return _minValue(root);
    }
    int maxValue(){
        return _maxValue(root);
    }
    void delete(int data){
        root = _delete(root, data);
    }
    void zigzagorder(){
        _zigzagorder(root);
    }


    public static void main(String[] args) {
      //  BinarySearchTree tree = new BinarySearchTree();
        BinarySearchTree tree1 = new BinarySearchTree();
        /*
              5
           /     \
          3       7
         /  \    /  \
       2     4  6    8 */
//        tree.insert(5);
//        tree.insert(3);
//        tree.insert(2);
//        tree.insert(4);
//        tree.insert(7);
//        tree.insert(6);
//        tree.insert(8);

        /*
                5
             /     \
           2         8
            \       /
             3     7
                  /
                 6
         */

        tree1.insert(5);
        tree1.insert(2);
        tree1.insert(3);
        tree1.insert( 8);
        tree1.insert(7);
        tree1.insert(6);

        //test methods with the first three
//        System.out.print("Inorder: "); //2 3 4 5 6 7 8
//        tree1.inorder();
        System.out.print("\nPreorder: "); //5 3 2 4 7 6 8
        tree1.preorder();
//        System.out.print("\nPostorder: "); //8 6 7 4 2 3 5
//        tree1.postorder();
        System.out.print("\nLevelorder: "); //5 3 7 2 4 6 8
        tree1.levelorder();
//        boolean exists = tree.exists(6);
//        System.out.println("\nCheck if 6 exists: " + exists);
//        int minValue = tree.minValue();
//        System.out.println("Min value: " + minValue);
//        int maxValue = tree.maxValue();
//        System.out.println("Max value: " + maxValue);
        tree1.delete(10);
        System.out.print("\nPreorder: "); //5 3 2 4 7 6 8
        tree1.preorder();
//        System.out.print("Zig-Zag order: "); //5 7 3 2 4 6 8
//        tree.zigzagorder();


    }
}


