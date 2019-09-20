import java.util.*;

public class Stack {
    private Coordinate[] stack;
    private int sp;
    public Stack(){
        stack = new Coordinate[100];
        sp = 0;
    }
    public void push(Coordinate c){
        if(sp>=stack.length){
            stack = Arrays.copyOf(stack, (int)(stack.length*1.5));
        }
        stack[sp] = c;
        sp++;
    }
    public Coordinate pop(){
        if(sp>0) {
            Coordinate ret = stack[--sp];
            return ret;
        } else {
            return null;
        }
    }
    public boolean isEmpty(){
        return sp<=0;
    }
}
