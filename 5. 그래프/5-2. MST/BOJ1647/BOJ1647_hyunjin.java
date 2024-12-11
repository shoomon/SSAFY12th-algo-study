package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ1647_도시 분할 계획

public class BOJ1674_hyunjin {
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

	static int[] p; // 대표자를 저장하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(A, B, W);

		}

		// 1. 크루스칼 : 가중치 순으로 정렬하기
		Arrays.sort(edges);

		p = new int[N + 1]; // 정점이 1부터 시작하므로

		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
		}

		int ans = 0;
		int pick = 0;
		int max = -1;

		for (int i = 0; i < M; i++) {
			int px = findSet(edges[i].A);
			int py = findSet(edges[i].B);

			if (px != py) {
				union(px, py);
				ans += edges[i].W;
				// 가중치 중에서 가장 큰 값 빼기
				max = Math.max(max, edges[i].W);
				pick++;
			}
			

			if (pick == N - 1)
				break;
		}

		System.out.println(ans - max);
	}


	static int findSet(int x) {
		if (x != p[x]) {
			p[x] = findSet(p[x]);
		}
		return p[x];
	}
	
	 static void union(int x, int y) {
		p[findSet(y)] = findSet(x);
	}
	
	
}