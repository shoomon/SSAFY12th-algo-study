//25.02.18
//12:20
import java.util.*;
import java.io.*;

public class BOJ{
    static class Node implements Comparable<Node>{
        String str;
        int order, level;

        public Node(String str, int order, int level){
            this.str = str;
            this.order = order;
            this.level = level;
        }

        @Override
        public int compareTo(Node n){
            return this.order - n.order;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Node[] list = new Node[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            list[i] = new Node(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(list);

        for(int i = 0; i < N; i++){
            char c = list[i].str.charAt(list[i].level);

            if('a' <= c && c <= 'z'){
                sb.append(c.toUpperCase());
            }else{
                sb.append(c);
            }
            
        }

        System.out.println(sb.);
    }
}