import java.util.Arrays;

public class MinHeap {
    private Node[] heap;
    private int size;

    public MinHeap(int capacity){
        heap = new Node[capacity];
        size = 0;
    }

    public void add(Node node){
        int pos = size;
        int par = (pos-1)/2;
        if(pos>=heap.length){
            heap = Arrays.copyOf(heap,heap.length*3/2);
        }
        heap[pos] = node;
        while (pos>0 && heap[par].d > heap[pos].d) {
            Node temp = heap[pos];
            heap[pos] = heap[par];
            heap[par] = temp;
            pos = par;
            par = (pos - 1) / 2;
        }
        size++;
    }

    public Node popMin(){
        Node ret = heap[0];
        heap[0] = heap[--size];
        if(size+1<heap.length)
            heap[size+1] = null;
        int pos = 0;
        int left = 1;
        int right = 2;
        while(true){
            int select = 0;
            if(left>=size){
                break;
            } else if(right>=size){
                select = left;
            } else if(heap[left].d>heap[right].d){
                select = right;
            } else {
                select = left;
            }

            if(heap[select].d<heap[pos].d){
                Node temp = heap[select];
                heap[select] = heap[pos];
                heap[pos] = temp;
                pos = select;
            } else {
                break;
            }

            left = select*2+1;
            right = select*2+2;

        }
        return ret;
    }

    public int size(){
        return this.size;
    }
}
