package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ1197_최소 스패닝 트리
public class BOJ1197_hyunjin {

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

	static int[] p; // 대표자를 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()); // 점점의 번호 1부터 시작
		int E = Integer.parseInt(st.nextToken()); // 간선의 수

		// Edge를 inner class로 설정하기
		Edge[] edges = new Edge[E];

		for (int i = 0; i < E; i++) {
			String line = br.readLine();

			// 빈 줄인 경우 무시
			if (line.trim().isEmpty()) {
				i--;
				continue;
			}
			st = new StringTokenizer(line);

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(A, B, W);
		} // 입력 끝

		// 1. 가중치 순으로 정렬하기
		Arrays.sort(edges);

		// 2. 간선 뽑기
		// 크루스칼 알고리즘 : 유니온-파인드
		p = new int[V + 1];

		// 정점 번호 1부터 시작
		// 자기 자신을 대표로 만들기
		for (int i = 1; i < V + 1; i++) {
			makeSet(i);
		}

		int ans = 0; // 최소 비용 저장
		int pick = 0; // 내가 뽑은 간선의 개수

		for (int i = 0; i < E; i++) {
			// findSet 메서드 호출하여 한 번에 저장하기
			int px = findSet(edges[i].A);
			int py = findSet(edges[i].B);

			if (px != py) {
				union(px, py);
				ans += edges[i].W;
				pick++;
			}

			if(pick == V-1) break;
		}
		
		System.out.println(ans);
	}

	static void makeSet(int x) {
		p[x] = x;
	}

	static int findSet(int x) {
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}

	static void union(int x, int y) {
		p[findSet(y)] = findSet(x);
	}
}