package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ1922_네트워크 연결
public class BOJ1922_hyunjin {

	static class Edge implements Comparable<Edge> {
		int A, B, W;

		public Edge(int a, int b, int w) {
			super();
			A = a;
			B = b;
			W = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.W, o.W);
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		// 인접 리스트
		List<Edge>[] adjList = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		//
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList[A].add(new Edge(A, B, W));
			adjList[B].add(new Edge(B, A, W));
		}

		// 방문 체크하기
		boolean[] visited = new boolean[N + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited[1] = true;

		int ans = 0;
		int pick = 0;

		// pq에 값 추가하기
		pq.addAll(adjList[1]);

		while (pick != N - 1) {
			Edge edge = pq.poll();
			if (visited[edge.B])
				continue;

			ans += edge.W;
			visited[edge.B] = true;
			pick++;

			pq.addAll(adjList[edge.B]);
		}

		System.out.println(ans);
	}

}