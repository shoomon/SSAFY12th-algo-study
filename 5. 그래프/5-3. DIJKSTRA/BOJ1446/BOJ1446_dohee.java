package oct_5th;

import java.io.*;
import java.util.*;

public class BOJ1446 {
	static boolean[] visited;
	static int N, D, dist[], dp[];
	static StringTokenizer st;
	
	public static class Node implements Comparable<Node>{
		
		int from;
		int to;
		int dist;
		
		public Node(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.from, o.from);
		}

		@Override
		public String toString() {
			return "Node [from=" + from + ", to=" + to + ", dist=" + dist + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		dp = new int[D+1];
		for (int i=0; i<=D; i++) {
			dp[i] = i;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			pq.add(new Node(from,to,dist));
			
		}
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(now.to>D) continue;
//			System.out.println(now.toString());
			for (int i=0; i<now.from; i++) {
				if(dp[i]!=i) {
					dp[now.from] = Math.min(dp[i] + (now.from-i), dp[now.from]);
				}
			}
			dp[now.to] = Math.min(dp[now.from] + now.dist, dp[now.to]);
			dp[D] = Math.min((dp[now.to]+ (D - now.to)), dp[D]);
		}
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[D]);
	}
}
