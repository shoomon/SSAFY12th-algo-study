import java.util.*;
import java.io.*;

/*
지름길 

지름길을 최대한 이용하여 목적지까지 최소 거리로 도착하시오

메모리 : 11692 KB
시간 : 72 ms

*/

public class Main {
	static int N, D;
	static Map<Integer, List<Edge>> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		int[] dp = new int[D + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			// 마지막 위치가 넘어가지 않는 지름길만 고려할 것
			if (!map.containsKey(E))
				map.put(E, new ArrayList<Edge>());
			
			map.get(E).add(new Edge(S, C));
		}
		
		dp[0] = 0;
		for (int i = 1; i <= D; i++) {
			dp[i] = dp[i - 1] + 1;
			if (map.containsKey(i)) {
				List<Edge> curList = map.get(i);
				for (int j = 0; j < curList.size(); j++) {
					Edge curEdge = curList.get(j);
					dp[i] = Math.min(dp[i], dp[curEdge.s] + curEdge.cost);
				}
			}
		}
		
		System.out.println(dp[D]);
	}

}

class Edge {
	int s;
	int cost;

	public Edge(int s, int cost) {
		this.s = s;
		this.cost = cost;
	}
}