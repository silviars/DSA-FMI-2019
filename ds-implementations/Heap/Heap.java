import java.util.ArrayList;

public class Heap {
   private ArrayList<Integer> heap = new ArrayList<>();

    int parent(int pos){
        return (pos-1)/2;
    }

    int leftChild(int pos){
        return pos*2 + 1;
    }
    int rightChild(int pos){
        return pos*2 + 2;
    }

    public boolean isEmpty(){
        return heap.size() == 0;
    }

    public int peek(){
        return heap.get(0);
    }

    public void insert(int element){
        heap.add(element);
        if(heap.size() > 0){
            siftUp(heap.size() - 1);
        }
    }

    public void remove(){
        if(!isEmpty()){
            swap(0, heap.size() - 1);
            heap.remove(heap.size() -1);
            siftDown(0);
        }

    }

    private void swap(int x, int y){
        int temp = heap.get(x);
        heap.set(x, heap.get(y));
        heap.set(y, temp);

    }

    private void siftUp(int lastPos){
        int parent = parent(lastPos);
        while(heap.get(lastPos) > heap.get(parent)){
            swap(lastPos, parent);
            if(parent <= 0) return;

            lastPos = parent;
            parent = parent(lastPos);
        }

    }

    private void siftDown(int pos){

        boolean hasLeft = leftChild(pos) < heap.size();
        boolean hasRight = rightChild(pos) < heap.size();

        //has both children
        if(hasRight && (heap.get(pos) < heap.get(leftChild(pos)) || heap.get(pos) < heap.get(rightChild(pos)))){
            int swapWith;
            if(heap.get(leftChild(pos)) < heap.get(rightChild(pos))){
                swapWith = rightChild(pos);
            } else {
                swapWith = leftChild(pos);
            }
            swap(pos, swapWith);
            siftDown(swapWith);
        } else if(hasLeft && heap.get(pos) < heap.get(leftChild(pos))){
            swap(pos, leftChild(pos));
            siftDown(leftChild(pos));
    }
    }

    public void print(){
        for(int i = 0; i < heap.size(); i++){
            System.out.print(heap.get(i) + " ");
        }
    }

    Heap(){}


    public static void main(String[] args){

        Heap heap = new Heap();
        heap.insert(1);
        heap.insert(10);
        heap.insert(3);
        heap.insert(7);
        heap.insert(5);

       heap.print();
       heap.remove();
       System.out.println("\n");
       heap.print();


    }
}
