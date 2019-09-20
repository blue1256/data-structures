import java.io.*;
import java.util.*;

public class Dijestra {
    public static double[] dists = new double[400];
    public static int[] prior = new int[400];
    public static boolean[] set = new boolean[400];

    public static void main(String[] args) throws FileNotFoundException {
        init.main(args);
        LinkedList<LinkedList<GraphNode>> adjlist = init.adjlist;
        File out = new File("Output_b.txt");
        PrintStream output = new PrintStream(out);
        for(int i=0;i<400;i++){
            dists[i] = Double.MAX_VALUE;
            prior[i] = -1;
        }
        MinHeap queue = new MinHeap(400);
        Node src = new Node(0,0);
        queue.add(src);
        dists[0] = 0;
        while(queue.size()>0){
            Node tar = queue.popMin();
            double dist = tar.d;
            if(set[tar.index]||dists[tar.index]<dist){
                continue;
            }
            set[tar.index] = true;
            LinkedList<GraphNode> list = adjlist.get(tar.index);
            for(int i=0;i<list.size();i++){
                GraphNode pos = list.get(i);
                int index = pos.x*20 + pos.y;
                if(!set[index]){
                    double newDist = dist+pos.d;
                    if(newDist<dists[index]){
                        dists[index] = newDist;
                        prior[index] = tar.index;
                        Node newNode = new Node(index, dists[index]);
                        queue.add(newNode);
                    }
                }
            }
        }
        for(int i=0;i<400;i++){
            if(dists[i]==Double.MAX_VALUE && prior[i] == -1){
                continue;
            }
            int x = i/20;
            int y = i%20;
            output.print("(0,0) - "+"("+x+","+y+"): "+dists[i]);
            printPath(i,output);
            output.println();
        }
    }

    public static void printPath(int index,PrintStream output){
        if(index==0){
            output.print(" ("+0+","+0+")");
            return;
        }
        if(prior[index]>=0){
            printPath(prior[index],output);
            int x = index/20;
            int y = index%20;
            output.print(" ("+x+","+y+")");
        }
    }
}

class Node {
    public int index;
    public double d;

    public Node(int index,double d){
        this.index = index;
        this.d = d;
    }
}
