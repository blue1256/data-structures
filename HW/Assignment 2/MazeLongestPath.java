import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeLongestPath {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("maze2.txt");
        String line = null;
        int r=0,c=0;
        int[][] map;
        Stack stack = new Stack();
        int[] movr = {-1,0,1,0};
        int[] movc = {0,1,0,-1};
        String ans = null;
        String subAns = null;

        Scanner input = new Scanner(inputFile);
        line = input.nextLine();
        Scanner lineScanner = new Scanner(line);
        r = lineScanner.nextInt();
        c = lineScanner.nextInt();
        lineScanner.close();
        map = new int[r][c];
        for(int i=0;i<r;i++){
            line = input.nextLine();
            lineScanner = new Scanner(line);
            for(int j=0;j<c;j++){
                map[i][j] = lineScanner.nextInt();
            }
            lineScanner.close();
        }
        line = input.nextLine();
        lineScanner = new Scanner(line);
        int startRow = lineScanner.nextInt();
        int startCol = lineScanner.nextInt();
        lineScanner.close();
        Coordinate startCoord = new Coordinate(startRow,startCol);
        stack.push(startCoord);
        map[startRow][startCol] = 2;
        subAns = startCoord.toString();
        while(!stack.isEmpty()){
            Coordinate cur = stack.pop();
            boolean noRoute = true;
            for(int i=0;i<4;i++){
                int nextRow = cur.getRow()+movr[i];
                int nextCol = cur.getCol()+movc[i];
                Coordinate next = new Coordinate(nextRow,nextCol);
                if(chkMap(map,nextRow,nextCol) && !cur.didVisited(i)) {
                    cur.addVisited(i);
                    map[nextRow][nextCol] = 2;
                    stack.push(cur);
                    stack.push(next);
                    subAns = subAns + ", " + next.toString();
                    noRoute = false;
                    break;
                }
            }
            if(noRoute){
                if((cur.getRow()==0 || cur.getRow() == r-1 || cur.getCol() ==0 || cur.getCol() == c-1) && !(cur.getRow()==startRow && cur.getCol()==startCol)) {
                    if (ans == null || ans.length() < subAns.length()) {
                        ans = subAns;
                    }
                }
                if(subAns.length()>5) {
                    map[cur.getRow()][cur.getCol()] = 1;
                    subAns = subAns.substring(0, subAns.length() - 7);
                }
            }
        }
        System.out.println(ans);
    }
    public static boolean chkMap(int[][] map, int curRow, int curCol){
        int row = map.length;
        int col = map[0].length;
        if(curRow>=0 && curRow<row && curCol>=0 && curCol<col){
            if(map[curRow][curCol]==1){
                return true;
            }
        }
        return false;
    }
}
