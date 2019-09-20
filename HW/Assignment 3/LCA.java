//2017-11522 Park Jong Seok
public class LCA {
    public static void printCommonPath(Node root, Node n1, Node n2){
        String s1 = search(root,n1,"");
        String s2 = search(root,n2,"");
        if(s1.length()<=0 || s2.length()<=0){
            System.out.println("No Common Path");
            return;
        }
        String commonPath = s1.substring(0,1);
        if(!commonPath.equals(s2.substring(0,1))){
            System.out.println("No Common Path");
            return;
        }
        int cnt = 1;
        int length = s1.length() > s2.length() ? s2.length() : s1.length();
        while(cnt<length){
            String sub1 = s1.substring(0,cnt+3);
            String sub2 = s2.substring(0,cnt+3);
            if(!sub1.equals(sub2)){
                break;
            }
            commonPath = sub1;
            cnt = cnt + 3;
        }
        System.out.println(commonPath);
    }

    public static String search(Node root, Node target, String path){
        if(root!=null){
            String curPath = path + root.data;
            if(root.data == target.data){
                return curPath;
            }
            String leftPath = search(root.left,target,curPath+"->");
            String rightPath = search(root.right,target,curPath+"->");
            if(leftPath.length()>0){
                return leftPath;
            } else if(rightPath.length()>0){
                return rightPath;
            }
        }
        return "";
    }

}

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data){
        this.data = data;
    }
}
