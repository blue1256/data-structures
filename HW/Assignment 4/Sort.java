import java.util.*;
import java.io.*;
public class Sort {
    public static void selectionSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static int partition(int[] arr, int left, int right){
        int i = left;
        int j = right+1;
        int pivot = arr[left];
        int temp;
        while(i<j){
            do{
                i++;
            } while(i<=right&&arr[i]<pivot);
            do{
                j--;
            } while(j>=left&&arr[j]>pivot);
            if(i<j){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public static void quickSort(int[] arr, int left, int right){
        if(left<right){
            int p = partition(arr,left,right);
            quickSort(arr,left,p-1);
            quickSort(arr,p+1,right);
        }
    }

    public static void heapSort(int[] arr){
        Heap heap = new Heap(arr.length);
        for(int i=0;i<arr.length;i++){
            heap.insert(arr[i]);
        }
        heap.sort(arr);
    }

    public static void initHeapSort(int[] arr){
        Heap iheap = new Heap(arr);
        iheap.sort();
    }

    public static void bstSort(int[] arr){
        BST bst = new BST();
        for(int i=0;i<arr.length;i++){
            bst.insert(arr[i]);
        }
        bst.inorderExtract(arr);
    }

    public static void splaySort(int[] arr){
        SplayTree st = new SplayTree();
        for(int i=0;i<arr.length;i++){
            st.insert(arr[i]);
        }
        st.inorderExtract(arr);
    }

    public static void merge(int[] arr1, int[] arr2, int[] arr){
        int i=0,j=0,k=0;
        int len1 = arr1.length;
        int len2 = arr2.length;
        while(i<len1 && j<len2){
            if(arr1[i]>arr2[j]){
                arr[k++] = arr2[j++];
            } else {
                arr[k++] = arr1[i++];
            }
        }
        while(i<len1){
            arr[k++] = arr1[i++];
        }
        while(j<len2){
            arr[k++] = arr2[j++];
        }
    }

    public static void mergeSort(int[] arr){
        int n = arr.length;
        if(n>=2){
            int m = n/2;
            int[] arr1 = Arrays.copyOfRange(arr,0,m);
            int[] arr2 = Arrays.copyOfRange(arr,m,n);
            mergeSort(arr1);
            mergeSort(arr2);
            merge(arr1, arr2, arr);
        }
    }

    public static boolean check(int[] arr){
        int n = arr.length;
        boolean ret = true;
        for(int i=0;i<n-1;i++){
            if(arr[i]>arr[i+1]){
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws FileNotFoundException{
        File in = new File(args[0]);
        File out = new File("output.txt");
        Scanner input = new Scanner(in);
        PrintStream output = new PrintStream(out);

        int num = 0;
        int cnt = 0;
        while(input.hasNextInt()){
            num = input.nextInt();
            cnt++;
        }
        int[] arr = new int[cnt];
        input = new Scanner(in);
        cnt = 0;
        while(input.hasNextInt()){
            arr[cnt] = input.nextInt();
            cnt++;
        }

        int[] selArr = Arrays.copyOf(arr,arr.length);
        int[] quickArr = Arrays.copyOf(arr,arr.length);
        int[] heapArr = Arrays.copyOf(arr,arr.length);
        int[] iheapArr = Arrays.copyOf(arr,arr.length);
        int[] bstArr = Arrays.copyOf(arr,arr.length);
        int[] splayArr = Arrays.copyOf(arr,arr.length);
        int[] mergeArr = Arrays.copyOf(arr,arr.length);
        long time = 0;
        double timeForMethod = 0;

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            selArr = Arrays.copyOf(arr,arr.length);
            selectionSort(selArr);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(selArr)){
            System.out.println(timeForMethod+" ms selection sort");
            output.println("selection sort: ");
            output.println(Arrays.toString(selArr));
        }

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            quickArr = Arrays.copyOf(arr,arr.length);
            quickSort(quickArr, 0, quickArr.length - 1);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(quickArr)){
            System.out.println(timeForMethod+" ms quicksort");
            output.println("quicksort: ");
            output.println(Arrays.toString(quickArr));
        }

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            heapArr = Arrays.copyOf(arr,arr.length);
            heapSort(heapArr);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(heapArr)){
            System.out.println(timeForMethod+" ms heapsort (without heap initialization)");
            output.println("heapsort (without heap initialization): ");
            output.println(Arrays.toString(heapArr));
        }

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            iheapArr = Arrays.copyOf(arr,arr.length);
            initHeapSort(iheapArr);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(iheapArr)){
            System.out.println(timeForMethod+" ms heapsort");
            output.println("heapsort: ");
            output.println(Arrays.toString(iheapArr));
        }

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            bstArr = Arrays.copyOf(arr,arr.length);
            bstSort(bstArr);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(bstArr)){
            System.out.println(timeForMethod+" ms BST sort");
            output.println("BST sort: ");
            output.println(Arrays.toString(bstArr));
        }

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            splayArr = Arrays.copyOf(arr,arr.length);
            splaySort(splayArr);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(splayArr)){
            System.out.println(timeForMethod+" ms splay tree sort");
            output.println("splay tree sort: ");
            output.println(Arrays.toString(splayArr));
        }

        time = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            mergeArr = Arrays.copyOf(arr,arr.length);
            mergeSort(mergeArr);
        }
        time = System.currentTimeMillis() - time;
        timeForMethod = time/100.0;

        if(check(mergeArr)){
            System.out.println(timeForMethod+" ms merge sort");
            output.println("merge sort: ");
            output.println(Arrays.toString(mergeArr));
        }

        System.out.println("the sorted file is \"output.txt\".");
    }
}

class Heap{
    private int[] heap;
    private int size;

    public Heap(int len){
        this.heap = new int[len];
        this.size = 0;
    }

    public Heap(int[] arr){
        heap = arr;
        size = arr.length;
        heapify();
    }

    private void heapify(){
        int i = (size-2)/2;
        while(i>=0){
            downheap(i);
            i--;
        }
    }

    private void increaseHeap(){
        this.heap = Arrays.copyOf(heap,heap.length*3/2);
    }

    public void insert(int data){
        int pos = size;
        int par = (pos-1)/2;
        if(pos>=heap.length){
            increaseHeap();
        }
        heap[pos] = data;
        while(heap[par]<heap[pos]){
            int temp = heap[par];
            heap[par] = heap[pos];
            heap[pos] = temp;
            pos = par;
            par = (pos-1)/2;
        }
        size++;
    }

    public void sort(){
        while(size>0) {
            int temp = heap[size-1];
            heap[size-1] = heap[0];
            heap[0] = temp;
            size--;
            downheap(0);
        }
    }

    public void sort(int[] arr){
        while(size>0) {
            int temp = heap[size-1];
            arr[size-1] = heap[0];
            heap[0] = temp;
            size--;
            downheap(0);
        }
    }

    private void downheap(int pos){
        while(pos<size){
            int left = pos*2+1;
            int right = pos*2+2;
            int select;
            if(left<size&&right<size){
                if(heap[left]<heap[right]){
                    select = right;
                } else {
                    select = left;
                }
            } else if(left<size){
                select = left;
            } else {
                break;
            }
            if(heap[select]>heap[pos]){
                int temp = heap[select];
                heap[select] = heap[pos];
                heap[pos] = temp;
                pos = select;
            } else {
                break;
            }
        }
    }
}

class BST{
    private BSTNode root;
    private int index;

    public BST(){
        this.root = null;
        this.index = 0;
    }

    public void insert(int data){
        root = insert(data,root);
    }

    private BSTNode insert(int data, BSTNode node){
        if(node == null){
            return new BSTNode(data);
        } else {
            if(node.data < data){
                node.right = insert(data, node.right);
            } else {
                node.left = insert(data, node.left);
            }
            return node;
        }
    }

    public void inorderExtract(int[] arr){
        index = 0;
        inorderExtract(arr,root);
    }

    private void inorderExtract(int[] arr, BSTNode node){
        if(node!=null) {
            inorderExtract(arr, node.left);
            arr[index++] = node.data;
            inorderExtract(arr, node.right);
        }
    }
}

class BSTNode{
    public int data;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class SplayTree{
    private STNode root;
    private int index;

    public SplayTree(){
        this.root = null;
        this.index = 0;
    }

    public void insert(int data){
        if(root==null){
            root = new STNode(data);
            return;
        }
        STNode pos = root;
        STNode x = null;
        while(pos!=null){
            if(pos.data < data){
                if(pos.right==null){
                    pos.right = new STNode(data, pos);
                    x = pos.right;
                    break;
                }
                pos = pos.right;
            } else {
                if(pos.left==null){
                    pos.left = new STNode(data, pos);
                    x = pos.left;
                    break;
                }
                pos = pos.left;
            }
        }
        splay(x);
    }

    private void splay(STNode x){
        while(x.parent!=null){
            STNode par = x.parent;
            STNode grd = par.parent;
            if(grd==null){
                if(par.left == x) {
                    par = rotateRight(par);
                } else {
                    par = rotateLeft(par);
                }
            } else {
                if((grd.left == par) && (par.left == x)){
                    grd = rotateRight(grd);
                    grd = rotateRight(grd);
                } else if(grd.left == par) {
                    par = rotateLeft(par);
                    grd = rotateRight(grd);
                } else if((grd.right == par) && (par.right == x)){
                    grd = rotateLeft(grd);
                    grd = rotateLeft(grd);
                } else {
                    par = rotateRight(par);
                    grd = rotateLeft(grd);
                }
            }
        }
        root = x;
    }

    private STNode rotateLeft(STNode x){
        STNode newRoot = x.right;
        if(x.parent!=null){
            if(x.parent.left == x){
                x.parent.left = newRoot;
            } else if(x.parent.right == x){
                x.parent.right = newRoot;
            }
        }
        x.right = newRoot.left;
        if(newRoot.left!=null) {
            newRoot.left.parent = x;
        }
        newRoot.left = x;
        newRoot.parent = x.parent;
        x.parent = newRoot;
        return newRoot;
    }

    private STNode rotateRight(STNode x){
        STNode newRoot = x.left;
        if(x.parent!=null){
            if(x.parent.left == x){
                x.parent.left = newRoot;
            } else if(x.parent.right == x){
                x.parent.right = newRoot;
            }
        }
        x.left = newRoot.right;
        if(newRoot.right!=null) {
            newRoot.right.parent = x;
        }
        newRoot.right = x;
        newRoot.parent = x.parent;
        x.parent = newRoot;
        return newRoot;
    }

    public void inorderExtract(int[] arr){
        index = 0;
        inorderExtract(arr,root);
    }

    private void inorderExtract(int[] arr, STNode node){
        if(node!=null) {
            inorderExtract(arr, node.left);
            arr[index++] = node.data;
            inorderExtract(arr, node.right);
        }
    }
}

class STNode{
    public int data;
    public STNode left;
    public STNode right;
    public STNode parent;

    public STNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public STNode(int data, STNode parent){
        this.data = data;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
}
