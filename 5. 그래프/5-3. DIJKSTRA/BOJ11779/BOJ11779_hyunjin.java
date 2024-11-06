package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
BOJ11779_최소비용 구하기2
메모리 :
시간 :
*/	

public class BOJ11779_hyunjin {
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
	
	static int n, m, startVilage, endVilage;
	static List<Node>[] adjList;
	static int INF = Integer.MAX_VALUE;
	static int[] dist, path;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine()); // 도시의 개수 
		m = Integer.parseInt(br.readLine()); // 버스의 개수 
		
		adjList = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 버스 출발 도시 번호 
			int B = Integer.parseInt(st.nextToken()); // 버스 도착 도시 번호 
			int W = Integer.parseInt(st.nextToken()); // 버스 비용 
			
			adjList[A].add(new Node(B, W));
		}
		
		st = new StringTokenizer(br.readLine());
		startVilage = Integer.parseInt(st.nextToken()); // 출발점 도시 번호 
		endVilage = Integer.parseInt(st.nextToken()); // 도착점 도시 번호 
		
		// 출발 도시 -> 도착 도시까지 최소 비용 출력
		// 그때의 경로 도시 순서대로 출력 
		
		dijkstra(startVilage);
		
		System.out.println(Arrays.toString(dist));
		System.out.println(Arrays.toString(path));
		
		// 1. 도착 도시까지 가는데 드는 최소 비용
		System.out.println(dist[endVilage]);
		
		// 2. 최소 비용을 갖는 도시의 개수 출력 (출발 도시와 도착 도시 포함)
		// path 배열에서 0아닌 개수가 몇 개 있는지 체크
		List<Integer> pathList = getPath(endVilage);
		System.out.println(pathList.size());
		
		// 3. 경로 방문하는 도시 순서대로 출력
		for(int i=0; i<pathList.size(); i++) {
			System.out.print(pathList.get(i) + " " ); 
		}
		
		
	}

	// 도착 지점까지의 경로 추적
	 private static List<Integer> getPath(int end) {
		List<Integer> pathList = new ArrayList<>();
		for(int at = end; at != -1; at=path[at]) {
			pathList.add(at);
		}
//		System.out.println(pathList);
		Collections.reverse(pathList);
		return pathList ;
	}

	static void dijkstra(int start) {
		dist = new int[n+1]; // 최소 거리 배열 
		path = new int[n+1]; // 경로 저장 배열
		
		Arrays.fill(dist, INF); // 다익스트라를 돌기 위해 maxValue로 초기화
		Arrays.fill(path, -1); // 해당 지점을 지나갔는지 안 갔는지 체크하기 위해 안 지나간 곳은 -1로 표시 
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0)); 
		dist[start] = 0; //출발 지점은 거리 0 
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			int currentCity = n.B; // 현재 도시
			int currentW = n.W; // 현재 비용
			
			if(currentW > dist[currentCity]) continue; // 이미 더 작은 비용으로 현재 위치까지 방문했다면 continue
			
			for(Node adjNode : adjList[currentCity]) { // 현재 도시에 인접한 도시 탐색하기
				if(dist[adjNode.B] > currentW + adjNode.W) { // 다음 도시의 비용보다 현재 도시비용 + 다음 도시 비용(=다음 도시까지 가는 비용)이 더 작게 든다면,
					dist[adjNode.B] = currentW + adjNode.W;
					path[adjNode.B] = currentCity; // 다음 도시 전에 이전에 방문한 도시 기록
					pq.add(new Node(adjNode.B,  dist[adjNode.B]));
				}
			}
		}
		
	}

}
