import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int v; // 노드
    int w; // 가중치
    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
    
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.w, o.w);
	}
}

public class Main {
    static int n, m, k, x;
    static final int inf = Integer.MAX_VALUE;
    static List<Node>[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드 수
        m = Integer.parseInt(st.nextToken()); // 간선 수
        k = Integer.parseInt(st.nextToken()); // 찾을 거리
        x = Integer.parseInt(st.nextToken()) - 1; // 출발 도시 번호 (0-based indexing)

        // 유향 그래프 초기화
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        dist = new int[n]; // 거리 배열
        Arrays.fill(dist, inf); // 무한대로 초기화

        // 간선 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0-based indexing
            int b = Integer.parseInt(st.nextToken()) - 1; // 0-based indexing
            adjList[a].add(new Node(b, 1)); // 가중치를 1로 설정
        }

        dijkstra(x);
    }

    private static void dijkstra(int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean [] visited = new boolean[n];
        
        dist[x] = 0;
        pq.offer(new Node(x, 0));
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int idx = curr.v;
        	
        	if(visited[idx])continue;
        	
        	visited[idx] = true;
        	
        	for(Node node : adjList[idx]) {
        		if(!visited[node.v] && dist[node.v] > dist[idx] + node.w) {
        			dist[node.v] = dist[idx] + node.w;
        			pq.offer(new Node(node.v, dist[node.v]));
        		}
        	}
        }
        boolean exist = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (dist[i] == k) {
                exist = true;
                sb.append(i + 1).append('\n'); // 1-based indexing으로 출력
            }
        }
        System.out.println(exist ? sb : -1);
    }
}
