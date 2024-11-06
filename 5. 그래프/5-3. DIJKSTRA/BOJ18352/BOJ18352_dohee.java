package oct_5th;

import java.io.*;
import java.util.*;

public class BOJ18352 {
	public static class Node implements Comparable<Node>{
		int index;
		int weight;
		
		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight,o.weight);
		}
	}
	
	static boolean check[];
	static int N,M,K,X, dist[];
	static StringTokenizer st;
	static ArrayList<Integer>[] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		check = new boolean[N+1];
		dist = new int[N+1];
		int INF = 100000000;
		Arrays.fill(dist, INF);

		graph = new ArrayList[N+1];
		for (int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
		}
		
		// 시작점 넣기
		dist[X] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(X, 0));
		
		while (!pq.isEmpty()) {
			int nowVertex = pq.poll().index;
			
			if(check[nowVertex]) continue;
			check[nowVertex] = true;
			
			for(Integer next : graph[nowVertex]) {
				if(dist[next] > dist[nowVertex] + 1) {
					dist[next] = dist[nowVertex] + 1;
					pq.offer(new Node(next, dist[next]));
				}
			}
			
		}
		boolean exist = false;
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<dist.length; i++) {
//			System.out.println(dist[i]);
			if(dist[i]==K) {
				sb.append(i).append("\n");
				exist = true;
			}
		}
		
		if(exist) {
			System.out.println(sb.toString());
		} else {
			System.out.println(-1);
		}
		
	}
}
