public class Coordinate {
    private int row,col;
    private int[] visited;
    private int vp = 0;
    public Coordinate(int row,int col){
        this.row = row;
        this.col = col;
        this.visited = new int[4];
        for(int i=0;i<4;i++){
            visited[i] = -1;
        }
        this.vp = 0;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void setCol(int col){
        this.col = col;
    }
    public void addVisited(int index){
        visited[vp++] = index;
    }
    public boolean didVisited(int index){
        boolean ret = false;
        for(int i=0;i<4;i++){
            if(visited[i]==index){
                ret = true;
                break;
            }
        }
        return ret;
    }
    public String toString(){
        return "<" + row + "," + col + ">";
    }
}
