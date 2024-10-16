import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
중량제한
다리를 지날때마다 한번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하는 프로그램 작성.

메모리 : 57269 KB
시간 : 428 ms

*/

public class Main {
	static List<Edge>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int islandCnt = Integer.parseInt(st.nextToken());
		int bridgeCnt = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[islandCnt+1];
		for (int i = 1; i <= islandCnt; i++)
			adjList[i] = new ArrayList<>();
		
		// 각 노드별 연결 가능한 다리 목록 구하기
		int maxBridge = 0;
		for (int i = 0; i < bridgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b,w));
			adjList[b].add(new Edge(a,w));
			maxBridge = Math.max(maxBridge, w);
		}
		st = new StringTokenizer(br.readLine());
		// 공장이 위치한 섬 2개의 위치
		// 결국 A공장에서 B공장에 도달할 수 있는지 여부 확인이 필요함
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 다리의 하중 제한이 1은 무조건 넘으니 최소치 1로 설정
		int left = 1;
		// 다리의 하중 제한의 최대치가 전부인 경우 해당 위치로 설정
		int right = maxBridge;
		
		// 정답 값을 구하기
		int ans = 0;
		// 해당 값을 찾을때까지 구하기
		while(left <= right) {
			int mid = (left + right) / 2;
			
			// mid에 해당하는 중량을 가지고 있을때, a 섬에서 b섬으로 갈 수 있는지 확인
			boolean reachable = isReachable(start, end, mid);
			// 도달할 수 있는 경우 트럭의 중량을 더 높여볼 수 있음
			if (reachable) {
				left = mid + 1;
				ans = mid;
			// 도달할 수 없는 경우 트럭의 중량을 더 낮춰 다시 시도.
			}else
				right = mid - 1;
		}
		
		System.out.println(ans);
	}
	
	
	public static boolean isReachable(int start, int end, int weight) {
		boolean reachable = false;
		
		// 섬의 방문 여부 체크
		boolean[] visited = new boolean[10001];
		// BFS를 통해 방문 가능한지 여부를 확인할 것임
		Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.offer(start);
		while(!q.isEmpty()) {
			int tmp = q.poll();
			for (int i = 0; i < adjList[tmp].size(); i++) {
				Edge nextIsland = adjList[tmp].get(i);
				// 방문하지 않은 섬인 경우, 해당 섬까지 가는 다리가 현재 weight보다 높거나 같은 경우
				if (!visited[nextIsland.tar] && nextIsland.weight >= weight) {
					// 현재 도달한 섬의 위치가 다른 공장이 위치한 섬인 경우 도달 가능처리하고 함수 종료
					if (nextIsland.tar == end) return true;
					q.offer(nextIsland.tar);
					visited[nextIsland.tar] = true;
				}
			}
		}
		
		return reachable;
	}
	
}

class Edge{
	int tar;
	int weight;
	public Edge(int tar, int weight) {
		this.tar = tar;
		this.weight = weight;
	}
}