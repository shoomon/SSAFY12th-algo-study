import java.util.*;
import java.io.*;
//날짜 24.09.24
//설계 시간: 2분
//구현 시간: 25분
//메모리: 57940 kb
//시간: 580 ms
public class Main {
    //음의 가중치가 가능하므로 prim
    //노드가 10만개이므로 인접리스트 또는 pQ사용

    static class Node implements Comparable<Node>{
        int v, w;
        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node n){
            return this.w-n.w;
        }
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int V, E;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited;
        List<Node>[] adj;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        visited = new boolean[V+1];
        adj = new LinkedList[V+1];

        for(int i = 0; i < V+1; i++){
            adj[i] = new LinkedList<>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[v1].add(new Node(v2, w));
            adj[v2].add(new Node(v1, w));
        }

        visited[1] = true;
        for(Node n : adj[1]){
            pq.offer(n);
        }

        int cnt=1, cost=0;

        while(true){
            if(pq.isEmpty() || cnt == V) break;
            if(visited[pq.peek().v]) {
                pq.poll();
                continue;
            }
            Node next = pq.poll();
            visited[next.v] = true;
            cost += next.w;
            cnt++;

            for(Node n : adj[next.v]){
                pq.offer(n);
            }
        }

        if(cnt == V) bw.write(cost+"\n");

        bw.close();
    }
}
