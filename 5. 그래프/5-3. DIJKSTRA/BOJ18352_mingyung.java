import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ18352_mingyung {
	// S2_특정 거리의 도시 찾기
	// 메모리 : 237,876 kb
	// 시간 : 1,308 ms

	static int INF = Integer.MAX_VALUE;
	static int N, M, K, X; // 도시개수, 도로개수, 거리정보, 출발도시
	static List<Integer>[] adjList;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st1.nextToken()); // 도시 개수
		M = Integer.parseInt(st1.nextToken()); // 도로 개수
		K = Integer.parseInt(st1.nextToken()); // 거리 정보
		X = Integer.parseInt(st1.nextToken()); // 출발 도시
		
		adjList = new ArrayList[N+1]; // 도시가 1번부터 시작하므로
		for (int i=1; i<N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		for (int i=0; i<M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st2.nextToken());
			int B = Integer.parseInt(st2.nextToken());
			adjList[A].add(B);
		} // 입력받기 완료
		
		dijkstra(X);
		
		StringBuilder sb = new StringBuilder();
		boolean result = false;
		for (int i=1; i<N+1; i++) {
			if (dist[i]==K) {
				sb.append(i).append("\n");
				result = true;
			}
		}
		
		if (result) System.out.println(sb);
		else System.out.println(-1);
		
	} // main
	
	
	static void dijkstra(int st) {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));
		boolean[] vis = new boolean[N+1];
		
		dist[st] = 0;
		pq.add(new int[]{st, 0});
		
		while(!pq.isEmpty()) {
			int curr = pq.poll()[0];
			if (vis[curr]) continue;
			vis[curr] = true;
			for (int i : adjList[curr]) {
				if (!vis[i] && dist[i] > dist[curr]+1) {
					dist[i] = dist[curr]+1;
					pq.add(new int[] {i, dist[i]});
				}
			}
		}
	}
}