package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ18352_특정 거리의 도시 찾기
// 메모리 : 276952KB
// 시간 : 1464 ms

public class BOJ18352_hyunjin {
	public static class Node implements Comparable<Node> {
		// 연결된 마을 번호와 거리
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

	static final int INF = Integer.MAX_VALUE;
	static int N, M, K, X;
	static List<Node>[] adjList; // 한 마을에서 연결된 마을
	static int[] dist; // 거리 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시의 개수(정점 수)
		M = Integer.parseInt(st.nextToken()); // 도로의 개수(간선 수)
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시 번호

		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 거리 배열 초기화
		dist = new int[N + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			adjList[A].add(new Node(B, 1)); // B지점과 연결되어 있고, 가중치는 무조건 1로 저장
		}

		// 다익스트라를 돌면서 최소 거리 배열 구하기
		dijkstra(X); // X 도시에서 출발

//		System.out.println(Arrays.toString(dist));

		// flag를 활용하여 답이 있는지 없는지 체크
		boolean flag = false;
		
		// 다익스트라를 다 돈 배열을 순회하면서, 
		// 거리 정보와 동일한 마을의 인덱스 출력하기
		for (int i = 0; i < N + 1; i++) {
			if (dist[i] == K) {
				// 동일한 값이 있다면 true로 변경
				flag = true;
				System.out.println(i);
			}
		}
		
		// 만약 같은 값이 하나도 없다면, flag는 그대로 false 일 것.
		if(!flag) {
			System.out.println(-1);
		}
		

	}

	// 다익스트라
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		dist[start] = 0; // 처음 출발하는 위치 

		pq.add(new Node(start, 0)); // 출발하는 위치, 출발점의 가중치 == 0

		while (!pq.isEmpty()) {
			Node n = pq.poll();

			// 다음 마을을 이미 방문했다면, continue
			if (visited[n.B])
				continue;
			visited[n.B] = true;

			// 연결된 모든 마을을 돌면서 최소 거리로 초기화하기 
			for (Node node : adjList[n.B]) {
				if (dist[node.B] > dist[n.B] + node.W) {
					dist[node.B] = dist[n.B] + node.W;

					pq.add(new Node(node.B, dist[node.B]));
				}
			}

		}

	}
}
