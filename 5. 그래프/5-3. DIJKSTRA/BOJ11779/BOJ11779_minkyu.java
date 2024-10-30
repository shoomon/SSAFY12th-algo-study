import java.util.*;
import java.io.*;

/*
최소비용 구하기 2

도시에서 다른 도시까지 가는 최단 경로의 길이와
해당 경로를 전부 출력하시오.

메모리 : 51812 KB
시간 : 440 ms

*/

public class Main {
    static List<Node>[] list;
    static int N;
    static int M;
    static int start;
    static int end;
    static int[] dist;
    static int[] route; // 경로 저장
    static boolean[] visited; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            list[i] = new ArrayList<>();
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[S].add(new Node(E, C));
        }
        
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        
        dist = new int[N + 1];
        route = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        dijkstra();
        
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append("\n");
        List<Integer> routes = new ArrayList<>();
        int current = end;
        while(current != 0) {
            routes.add(current);
            current = route[current];
        }
        sb.append(routes.size()).append("\n");
        for(int i = routes.size() - 1; i >= 0; i--)
        	sb.append(routes.get(i) + " ");
        
        System.out.println(sb);
    }
    
    // 다익스트라 함수
    public static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        // 시작 노드의 거리를 0으로 설정
        dist[start] = 0;
        route[start] = 0;
        
        while(!q.isEmpty()) {
            Node tmp = q.poll();
            // 방문체크
            if(visited[tmp.e]) continue;
            
            visited[tmp.e] = true;
            for(int i = 0; i < list[tmp.e].size(); i++) {
                Node next = list[tmp.e].get(i);
                if(dist[next.e] > dist[tmp.e] + next.cost) {
                    dist[next.e] = dist[tmp.e] + next.cost;
                    q.offer(new Node(next.e, dist[next.e]));
                    route[next.e] = tmp.e;
                }
            }
        }
    }
    
    public static class Node implements Comparable<Node> {
        int e;
        int cost;
        
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }
    }
}