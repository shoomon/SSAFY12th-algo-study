package study;
//24.11.06
// 71800 kb
// 572 ms
//다익스트라 -> 64만*3, 플로이드 5억 1천 2백
// (start - v1) + (v1 - v2) + (v2 - end)
import java.util.*;
import java.io.*;

public class BOJ1504 {
	static class Node implements Comparable<Node>{
		int v,c;
		public Node(int v, int c) {
			this.v = v;
			this.c = c;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.c-n.c;
		}
	}
	static int N, E, v1,v2;
	static long answer;
	static List<Node>[] adj;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new LinkedList[N+1];
		v1=v2=0;
		answer = 0;
		
		
		for(int i = 0; i < N+1; i++) adj[i] =  new LinkedList<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adj[a].add(new Node(b,c));
			adj[b].add(new Node(a,c));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		answer = Math.min(calculatePath(v1, v2), calculatePath(v2, v1));
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		
		System.out.println(answer);
	}
	
	static long calculatePath(int a, int b) {
		long first = dijikstra(1,a);
		long second = dijikstra(a,b);
		long third = dijikstra(b, N);
		
		if(first == Integer.MAX_VALUE || second == Integer.MAX_VALUE || third == Integer.MAX_VALUE) return Integer.MAX_VALUE;
		return first+second+third;
	}
	
	static int dijikstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] minDist = new int[N+1];
		
		Arrays.fill(minDist,Integer.MAX_VALUE);
		minDist[start] = 0;
		
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(minDist[cur.v] >= cur.c) {
				for(Node n : adj[cur.v]) {
					if(minDist[n.v] > minDist[cur.v]+n.c) {
						minDist[n.v] = minDist[cur.v]+n.c;
						pq.offer(new Node(n.v,minDist[cur.v]+n.c));
					}					
				}
			}
		}
		
		return minDist[end];
	}
}
