package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ1446_지름길
// 메모리 : 11884KB
// 시간 : 80ms

public class BOJ1446_hyunjin {

	public static class Node implements Comparable<Node> {
		int B, W;

		public Node(int b, int w) {
			B = b;
			W = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.W - o.W;
		}
	}

	static final int INF = Integer.MAX_VALUE;
	static int N, D;
	static List<Node>[] adjList;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지름길의 개수
		D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

		adjList = new ArrayList[D + 1];
		for (int i = 0; i < D + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 모든 위치에서 다음 위치로 가는 경로 설정하기
		// 거리는 기본 : 1
		for (int i = 0; i < D ; i++) {
			adjList[i].add(new Node(i + 1, 1));
		}

		dist = new int[D + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken()); // 시작 위치
			int e = Integer.parseInt(st.nextToken()); // 도착 위치
			int len = Integer.parseInt(st.nextToken()); // 지름길 길이

			if (e - s >= len && e <= D) {
				adjList[s].add(new Node(e, len));
			}
		}

		dijkstra(0);

//		System.out.println(Arrays.toString(dist));
		System.out.println(dist[D]);

	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 출발 위치 거리 0으로 설정
		dist[start] = 0;

		// pq에 넣기
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node n = pq.poll();

			// 이미 dist[n.B]에 기록된 거리가 더 작다면..n.B 무시
			if (n.W > dist[n.B])
				continue;

			for (Node node : adjList[n.B]) {
				if (dist[node.B] > dist[n.B] + node.W) {
					dist[node.B] = dist[n.B] + node.W;

					pq.add(new Node(node.B, dist[node.B]));
				}
			}
		}
	}
}
