import java.util.*;
import java.io.*;

/*
네트워크 복구

최소한의 회선만 복구하여 모든 컴퓨터를 관리할 수 있는 회선들을 출력하시오.

메모리 : 91744 KB
시간 : 480 ms

*/

public class Main {
	static int N, M;
	static List<Node>[] adjList;
	static int[][] dist;
	static int[] route;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		dist = new int[N + 1][2];
		route = new int[N + 1];
		Arrays.fill(route, -1);
		for (int i = 1; i<= N; i++)
			adjList[i] = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			dist[i][0] = Integer.MAX_VALUE;
			dist[i][1] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			adjList[S].add(new Node(E, C, 0));
			adjList[E].add(new Node(S, C, 0));
		}
		
		dijkstra();
		
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i<=N; i++) {
			if (route[i] != -1) {
				cnt++;
				sb.append(route[i]).append(" ").append(i).append("\n");
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	public static void dijkstra() {
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0, 0));
        dist[1][0] = 0;
        dist[1][1] = 0;
        route[1] = 0;
        
        while(!q.isEmpty()) {
            Node tmp = q.poll();
            // 방문체크 -> 방문한 경우는 다시 방문하지 않음
            if(visited[tmp.e]) continue;
            visited[tmp.e] = true;

            for(int i = 0; i < adjList[tmp.e].size(); i++) {
                Node next = adjList[tmp.e].get(i);
                if(dist[next.e][0] > dist[tmp.e][0] + next.cost) {
                    dist[next.e][0] = dist[tmp.e][0] + next.cost;
                    dist[next.e][1] = dist[tmp.e][1] + 1;
                    route[next.e] = tmp.e;
                    q.offer(new Node(next.e, dist[next.e][0], dist[next.e][1]));
                // 같은 경우에는 해당 경로를 거치는 노드의 수가 최소가 되어야 좋은 것임
                }else if (dist[next.e][0] == dist[tmp.e][0] + next.cost) {
                	// 노드 수 최소가 되도록 중간 값을 변경
                	if (dist[next.e][1] > dist[tmp.e][1] + 1) {
                		dist[next.e][0] = dist[tmp.e][0] + next.cost;
                        dist[next.e][1] = dist[tmp.e][1] + 1;
                        route[next.e] = tmp.e; 
                        q.offer(new Node(next.e, dist[next.e][0], dist[next.e][1]));
                	}
                }
            }
        }
	}
}

class Node implements Comparable<Node>{
	int e;
	int cost;
	int cnt;
	public Node(int e, int cost, int cnt) {
		this.e = e;
		this.cost = cost;
		this.cnt = cnt;
	}
	public int compareTo(Node o) {
		if (this.cost == o.cost)
			return Integer.compare(this.cnt, o.cnt);
		return Integer.compare(this.cost, o.cost);
	}
}