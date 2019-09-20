//2017-11522 Park Jong Seok
import java.util.*;

public class MyTree01 {
    public TreeNode root = null;

    public MyTree01(String preorder){
        root = consPreorder(preorder);
    }

    private TreeNode consPreorder(String preorder){
        if(preorder.length()>1) {
            TreeNode node = new TreeNode(preorder.substring(0, 1));
            String rest = preorder.substring(1);
            int divider = divideString(rest);
            String leftString = rest.substring(0, divider);
            String rightString = rest.substring(divider);
            node.left = consPreorder(leftString);
            node.right = consPreorder(rightString);
            return node;
        } else if(preorder.length()==1) {
            TreeNode node = new TreeNode(preorder);
            return node;
        } else {
            return null;
        }
    }

    private int divideString(String str){
        int op = 0;
        int var = 0;
        int i = 0;
        for(i=0;i<str.length();i++){
            if(var == op+1){
                break;
            }
            char tar = str.charAt(i);
            if(tar == '*' || tar == '/' || tar == '+' || tar == '-'){
                op++;
            } else {
                var++;
            }
        }
        return i;
    }

    public String postorder(){
        return postorder(root);
    }

    private String postorder(TreeNode node){
        String ret = "";
        if(node!=null) {
            ret = ret + postorder(node.left);
            ret = ret + postorder(node.right);
            ret = ret + node.getData();
        }
        return ret;
    }

    public String inorder(){
        return inorder(root);
    }

    private String inorder(TreeNode node){
        String ret = "";
        if(node!=null && node.left!=null && node.right!=null) {
            ret = "(";
            ret = ret + inorder(node.left);
            ret = ret + node.getData();
            ret = ret + inorder(node.right);
            ret = ret + ")";
        } else if(node!=null){
            ret = ret + node.getData();
        }
        return ret;
    }

    public String levelorder(){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        String ret = "";
        if(root!=null) {
            queue.add(root);
        }
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            ret = ret + cur.getData();
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
        return ret;
    }

}

class TreeNode{
    private String data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(String data){
        this.data = data;
    }

    public String getData(){
        return this.data;
    }
}
