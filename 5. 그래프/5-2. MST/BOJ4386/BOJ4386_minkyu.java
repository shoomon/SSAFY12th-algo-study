import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
별자리 만들기

n개의 별들을 모두 이어서 하나의 별자리를 만든다.
별자리의 별들은 직/간접적으로 모두 이어져 있어야한다.
별자리를 만드는데 드는 최소비용을 구하시오.

메모리 : 14176 KB
시간 : 104 ms

*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int starCnt = Integer.parseInt(br.readLine());
		
		double[][] stars = new double[starCnt][2];
		for (int i = 0; i < starCnt; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		// 프림 알고리즘 진행 -> 모든 점들을 연결할 수 있다는 점을 이용
		double[] dist = new double[starCnt];
		for (int i = 0; i < starCnt; i++)
			dist[i] = Double.MAX_VALUE;
		boolean[] visited = new boolean[starCnt];
		
		dist[0] = 0.0;
		double cost = 0;
		for (int i = 0; i < starCnt; i++) {
			double min = Double.MAX_VALUE;
			int idx = -1;
			for (int j = 0; j < starCnt; j++) {
				if (min > dist[j] && !visited[j]) {
					min = dist[j];
					idx = j;
				}
			}
			
			if (idx == -1) break;
			visited[idx] = true;
			cost+=dist[idx];
			for (int j = 0; j < starCnt; j++) {
				if (j == idx) continue;
				double curDist = getDist(stars[idx], stars[j]);				
				if (dist[j] > curDist && !visited[j])
					dist[j] = curDist;
			}
		}
		System.out.println(cost);
	}
	
	public static double getDist(double[] a, double[] b) {
		return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
	}
}