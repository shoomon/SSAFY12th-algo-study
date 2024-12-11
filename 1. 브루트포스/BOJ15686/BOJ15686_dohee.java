package oct_5th;

import java.io.*;
import java.util.*;

public class BOJ15686 {
	static int N, M, map[][], minDistAll, dist[][];
	static StringTokenizer st; 
	static ArrayList<Node> houses ;
	static ArrayList<Node> stores ;
	
	public static class Node{
		int x;
		int y;
		
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		houses = new ArrayList<>();
		stores = new ArrayList<>();
		minDistAll = Integer.MAX_VALUE;
		map = new int[N+1][N+1];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					houses.add(new Node(i,j));
				} else if (map[i][j]==2) {
					stores.add(new Node(i,j));
				}
			}
		}
		
		dist = new int[houses.size()][stores.size()];
		
		// 각 집으로부터 치킨집까지의 거리 배열
		for (int i=0; i<houses.size();i++) {
			for (int j=0; j<stores.size(); j++) {
				dist[i][j] = calDist(houses.get(i),stores.get(j));
			}
		}
		
//		print(dist);
		// 살아남을 치킨집 선택 (조합)
		combi(0,0, new boolean[stores.size()]);
		
		System.out.println(minDistAll);
	}
	
	public static void combi(int start, int count, boolean visited[]) {
		if(count == M) {
//			System.out.println(Arrays.toString(visited));
			int minDistSum = 0;
			for (int i=0; i<houses.size(); i++) {
				int minDist = Integer.MAX_VALUE;
				for (int j=0; j<stores.size(); j++) {
					if(visited[j]) {
						minDist = Math.min(minDist, dist[i][j]);
//						System.out.println(minDist);
					}
				}
				minDistSum+=minDist;
			}
//			System.out.println("==========");
			minDistAll = Math.min(minDistSum, minDistAll);
			return;
		}
		
		for (int i=start; i<stores.size(); i++) {
			visited[i] = true;
			combi(i+1,count+1, visited);
			visited[i]= false;
		}
	}
	
	public static int calDist(Node a, Node b) {
		return Math.abs(a.x - b.x)+Math.abs(a.y - b.y);
	}
	
	public static void print(int[][] arr) {
		for(int i=0; i<arr.length;i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}
