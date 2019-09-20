import java.io.*;
import java.util.*;

public class prime {
    public static double[] weights = new double[400];
    public static int[] prior = new int[400];
    public static boolean[] set = new boolean[400];

    public static void main(String[] args) throws FileNotFoundException {
        init.main(args);
        LinkedList<LinkedList<GraphNode>> adjlist = init.adjlist;
        File out = new File("Output_c.txt");
        PrintStream output = new PrintStream(out);
        for(int i=0;i<400;i++){
            weights[i] = Double.MAX_VALUE;
            prior[i] = -1;
        }
        MinHeap queue = new MinHeap(400);
        Node src = new Node(399,0);
        queue.add(src);
        weights[399] = 0;
        while(queue.size()>0){
            Node tar = queue.popMin();
            set[tar.index] = true;
            LinkedList<GraphNode> list = adjlist.get(tar.index);
            for(int i=0;i<list.size();i++){
                int index = list.get(i).x*20 + list.get(i).y;
                double newWeight = list.get(i).d;
                if(!set[index] && weights[index]>newWeight){
                    weights[index] = newWeight;
                    prior[index] = tar.index;
                    Node newNode = new Node(index,weights[index]);
                    queue.add(newNode);
                }
            }
        }
        double total = 0;
        for(int i=0;i<399;i++){
            if(prior[i]<0&&weights[i]==Double.MAX_VALUE){
                continue;
            }
            int ix = i/20;
            int iy = i%20;
            int ex = prior[i]/20;
            int ey = prior[i]%20;
            total += weights[i];
            output.println("("+ix+","+iy+")"+" ("+ex+","+ey+")");
        }
        output.println("Weight: "+total);
    }
}
