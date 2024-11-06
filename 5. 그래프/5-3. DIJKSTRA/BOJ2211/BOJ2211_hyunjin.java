package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


// BOJ2211_네트워크 복구
// 메모리 : 
// 시간 : 

public class BOJ2211_hyunjin {
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

	static int N, M;
	static int INF = Integer.MAX_VALUE;
	static int[] dist, path;
	static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList[A].add(new Node(B, W));
			adjList[B].add(new Node(A, W));
		}

		dijkstra(1); // 1번 컴퓨터에서 출발

		// System.out.println(Arrays.toString(dist));
		// System.out.println(Arrays.toString(path));

		// 복구해야하는 네트워크 출력
		List<int[]> connection = new ArrayList<>();
		for (int i = 2; i < N + 1; i++) {
			if (path[i] != -1) {
				connection.add(new int[] { i, path[i] });
			}
		}

		// 복구 해야할 네트워크 개수
		System.out.println(connection.size());
		for (int[] list : connection) {
			for (int a : list) {
				System.out.print(a + " ");
			}
			System.out.println();
		}

	}

	static void dijkstra(int start) {
		dist = new int[N + 1];
		path = new int[N + 1];

		Arrays.fill(dist, INF);
		Arrays.fill(path, -1);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			int currComputer = n.B;
			int currentW = n.W;

			if (currentW > dist[currComputer])
				continue;

			for (Node node : adjList[currComputer]) {
				if (dist[node.B] > currentW + node.W) {
					dist[node.B] = currentW + node.W;
					path[node.B] = currComputer;
					pq.add(new Node(node.B, dist[node.B]));
				}
			}
		}

	}
}
