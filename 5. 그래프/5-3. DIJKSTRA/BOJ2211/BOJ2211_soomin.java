//24.10.30
//93148 kb
//504 ms
//전체 비용을 최소로 하는것이 아니라 각 노드까지의 거리가 최소가 되어야 한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int e,w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Node e) {
			return this.w-e.w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, M;
		List<Node>[] nodes;
		ArrayList<int[]> selected = new ArrayList<>();
		int[] minDist, from;
		boolean[] visited;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes = new ArrayList[N+1];
		minDist = new int[N+1];
		from = new int[N+1];
		visited = new boolean[N+1];
		
		Arrays.fill(minDist, Integer.MAX_VALUE);
		for(int i = 0 ; i < N+1; i++) nodes[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodes[v1].add(new Node(v2, cost));
			nodes[v2].add(new Node(v1, cost));
		}
		
		minDist[1] = 0;
		visited[1] = true;
		from[1] = 0;
		for(Node n : nodes[1]) {
			if(minDist[n.e] > n.w) {
				minDist[n.e] = n.w;
				from[n.e] = 1;
			}
		}
		
		for(int i = 0; i < N; i++) {
			int min = -1, dist = Integer.MAX_VALUE;
			for(int j = 1; j < N+1; j++) {
				if(visited[j]) continue;
				if(dist > minDist[j]) {
					dist = minDist[j];
					min = j;
				}
			}
			
			if(min == -1) break;
			
			visited[min] = true;
			selected.add(new int[] {from[min],min});
			
			for(Node n : nodes[min]) {
				if(minDist[n.e] > minDist[min]+n.w) {
					minDist[n.e] = minDist[min]+n.w;
					from[n.e] = min;
				}
			}
		}
		
		System.out.println(selected.size());
		for(int[] i : selected) {
			System.out.println(i[0]+" "+i[1]);
		}
	}
}
