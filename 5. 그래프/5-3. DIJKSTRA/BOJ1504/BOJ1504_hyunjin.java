package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ1504_특정한 최단 거리
// 메모리 : 67664KB 
// 시간 : 660ms 
public class BOJ1504_hyunjin {
	public static class Node implements Comparable<Node> {
		int B, W;

		public Node(int b, int w) {
			super();
			B = b;
			W = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.W - o.W;
		}
	}

	static int N, E;
	static int INF = Integer.MAX_VALUE;
	static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		adjList = new ArrayList[N + 1]; // 정점 번호가 1부터 시작
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			// 무방향 그래프
			adjList[A].add(new Node(B, W));
			adjList[B].add(new Node(A, W));
		}

		// 반드시 거쳐야 하는 두 개의 서로 다른 정점
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		// 1->v1, 1->v2, v1->v2, v1->N, v2->N 의 최단 거리 각각 구하기
		int startTov1 = dijkstra(1, v1);
		int startTov2 = dijkstra(1, v2);
		int v1Tov2 = dijkstra(v1, v2);
		int v1ToEnd = dijkstra(v1, N);
		int v2ToEnd = dijkstra(v2, N);

		// case 1 : 1 -> v1 -> v2 -> N
        long case1 = (startTov1 == INF || v1Tov2 == INF || v2ToEnd == INF) ? INF : (long) startTov1 + v1Tov2 + v2ToEnd;

        // case 2 : 1 -> v2 -> v1 -> N
        long case2 = (startTov2 == INF || v1Tov2 == INF || v1ToEnd == INF) ? INF : (long) startTov2 + v1Tov2 + v1ToEnd;

        long ans = Math.min(case1, case2);
        System.out.println(ans >= INF ? -1 : ans);

	}

	// 다익스트라
	static int dijkstra(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();

			if (n.W > dist[n.B])
				continue;

			for (Node node : adjList[n.B]) {
				if (dist[node.B] > n.W + node.W) {
					dist[node.B] = n.W + node.W;
					pq.add(new Node(node.B, dist[node.B]));
				}
			}

		}
//		System.out.println(Arrays.toString(dist));
		return dist[end];
	}

}
