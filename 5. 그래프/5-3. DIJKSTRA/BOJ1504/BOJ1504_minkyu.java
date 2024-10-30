import java.util.*;
import java.io.*;

/*
특정한 최단 경로

특정한 정점 2개를 반드시 지나는 최단 경로를 구하시오

메모리 : 68156 KB
시간 : 468 ms
*/

public class Main {
	static List<Node>[] adjList;
	static int N;
	static int E;
	static int V1, V2;
	
	static int[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			adjList[S].add(new Node(E, C));
			adjList[E].add(new Node(S, C));
		}
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1];
		int oneToV1Dist = dijkstra(1, V1);
		int oneToV2Dist = dijkstra(1, V2);
		int V1ToV2Dist = dijkstra(V1, V2);
		int V2ToV1Dist = dijkstra(V2, V1);
		int V2ToEnd = dijkstra(V2, N);
		int V1ToEnd = dijkstra(V1, N);
		
		int min = -1;
		boolean isFirstPossible = false;
		if (oneToV1Dist != -1 && V1ToV2Dist != -1 && V2ToEnd != -1)
			isFirstPossible = true;
		boolean isSecondPossible = false;
		if (oneToV2Dist != -1 && V2ToV1Dist != -1 && V1ToEnd != -1)
			isSecondPossible = true;
		if (isFirstPossible && isSecondPossible)
			min = Math.min(oneToV1Dist + V1ToV2Dist + V2ToEnd, oneToV2Dist + V2ToV1Dist + V1ToEnd);
		else if (isFirstPossible)
			min = oneToV1Dist + V1ToV2Dist + V2ToEnd;
		else if (isSecondPossible)
			min = oneToV2Dist + V2ToV1Dist + V1ToEnd;
		
		System.out.println(min);
	}
	
	public static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        dist[start] = 0;
        
        while(!q.isEmpty()) {
            Node tmp = q.poll();
            // 방문체크 -> 방문한 경우는 다시 방문하지 않음
            if(visited[tmp.e]) continue;
            visited[tmp.e] = true;
            if (tmp.e == end)
            	return dist[tmp.e];
            for(int i = 0; i < adjList[tmp.e].size(); i++) {
                Node next = adjList[tmp.e].get(i);
                if(dist[next.e] > dist[tmp.e] + next.cost) {
                    dist[next.e] = dist[tmp.e] + next.cost;
                    q.offer(new Node(next.e, dist[next.e]));
                }
            }
        }
        
        return -1;
	}
}

class Node implements Comparable<Node>{
	int e;
	int cost;
	
	public Node(int e, int cost) {
		this.e = e;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}