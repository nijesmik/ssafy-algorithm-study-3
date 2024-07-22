import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek1991 {

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        Node[] nodes = new Node[N];

        StringTokenizer st;

        for(int i=0; i < N; i++){
            st = new StringTokenizer(bf.readLine());
            int cen = st.nextToken().charAt(0) - 'A';
            int left = st.nextToken().charAt(0) - 'A';
            int right = st.nextToken().charAt(0) - 'A';
            nodes[cen] = new Node(cen, left, right);

        }

        preorder(0, nodes);
        System.out.println(sb_preorder.toString());

        inorder(0, nodes);
        System.out.println(sb_inorder.toString());

        postorder(0, nodes);
        System.out.println(sb_postorder.toString());
    }
    static StringBuilder sb_preorder = new StringBuilder();

    // 전위 순회
    static void preorder(int n, Node[] nodes){

        if(n== '.'-'A')return;

        sb_preorder.append((char) (n+'A'));

        preorder(nodes[n].left, nodes);
        preorder(nodes[n].right,nodes);
    }

    // 중위 순회
    static StringBuilder sb_inorder = new StringBuilder();
    static void inorder(int n, Node[] nodes){
        if(n== '.'-'A')return;

        inorder(nodes[n].left, nodes);
        sb_inorder.append((char) (n+'A'));
        inorder(nodes[n].right, nodes);

    }

    // 후위 순회
    static StringBuilder sb_postorder = new StringBuilder();
    static void postorder(int n, Node[] nodes){
        if(n== '.'-'A')return;

        postorder(nodes[n].left, nodes);
        postorder(nodes[n].right, nodes);
        sb_postorder.append((char) (n+'A'));

    }

    static class Node{
        int left;
        int right;
        int cen;

        public Node(int cen, int left, int right) {
            this.left = left;
            this.right = right;
            this.cen = cen;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", cen=" + cen +
                    '}';
        }
    }
}
