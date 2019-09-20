import java.util.*;
import java.io.*;

public class Subarray {
    public static void main(String[] args) throws FileNotFoundException {
        Hashtable<Integer,List<Integer>> hmap = new Hashtable<Integer, List<Integer>>();
        File inputFile = new File("array.txt");
        Scanner input = new Scanner(inputFile);
        int[] arr = new int[100];
        int size = 0;
        while(input.hasNextLine()){
            String line = input.nextLine();
            if(size>=arr.length){
                arr = Arrays.copyOf(arr,(int)(arr.length*1.5));
            }
            arr[size++] = Integer.parseInt(line);
        }
        int sum =0;

        for(int i=0;i<size;i++){
            sum = sum + arr[i];
            if(sum==0){
                System.out.println("Subarray found from Index "+0+" to "+i);
            }
            if(hmap.get(sum)!=null){
                List<Integer> value = hmap.get(sum);
                Iterator itr = value.iterator();
                while(itr.hasNext()){
                    int index = (int) itr.next();
                    System.out.println("Subarray found from Index "+(index+1)+" to "+i);
                }
                value.add(i);
            } else {
                List<Integer> val = new ArrayList<Integer>();
                val.add(i);
                hmap.put(sum, val);
            }
        }
    }
}
