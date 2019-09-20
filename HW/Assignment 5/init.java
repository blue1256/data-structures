import java.io.*;
import java.util.*;

public class init {
    public static LinkedList<LinkedList<GraphNode>> adjlist = new LinkedList<LinkedList<GraphNode>>();

    public static void main(String[] args) throws FileNotFoundException {
        File in = new File("height.txt");
        Scanner input = new Scanner(in);
        Scanner linescan;
        String line;
        double[][] hmat = new double[20][20];
        for(int i=0;i<400;i++){
            adjlist.add(new LinkedList<GraphNode>());
        }
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                hmat[i][j] = input.nextDouble();
            }
        }
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                double selH = hmat[i][j];
                if(selH==-1){
                    continue;
                }
                for(int r=-2;r<=2;r++){
                    for(int c=-2;c<=2;c++){
                        int mandist = Math.abs(r)+Math.abs(c);
                        if(i+r<0||i+r>=20||j+c<0||j+c>=20||(r==0&&c==0)||mandist>2){
                            continue;
                        }
                        double tarH = hmat[i+r][j+c];
                        if(tarH<0){
                            continue;
                        }
                        double distance = Math.sqrt((selH-tarH)*(selH-tarH)+mandist*mandist);
                        GraphNode pos = new GraphNode(i+r,j+c,distance);
                        adjlist.get(i*20+j).add(pos);
                    }
                }
            }
        }
    }
}

class GraphNode {
    public int x;
    public int y;
    public double d;

    public GraphNode(int x, int y, double d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
