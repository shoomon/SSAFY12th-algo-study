import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
네트워크 연결

모든 컴퓨터를 연결하는 네트워크를 구축한다.
모든 컴퓨터를 연결할 때 드는 최소 비용을 출력하라.


메모리 : 49880 KB
시간 : 484 ms

*/

public class Main {
	static int[] p = {};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int comCnt = Integer.parseInt(br.readLine());
		int lineCnt = Integer.parseInt(br.readLine());
		
		int[][] lineInfos = new int[lineCnt][3];
		for (int i = 0; i < lineCnt; i++) {
			st = new StringTokenizer(br.readLine());
			lineInfos[i][0] = Integer.parseInt(st.nextToken());
			lineInfos[i][1] = Integer.parseInt(st.nextToken());
			lineInfos[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(lineInfos,(o1,o2)->{
			if (o1[2] > o2[2])
				return 1;
			else
				return -1;
		});
		
		p = new int[comCnt + 1];
		for (int i = 1; i <= comCnt; i++)
			p[i] = i;
		
		int cost = 0;
		for (int i = 0; i < lineInfos.length; i++) {
			int x = findSet(lineInfos[i][0]);
			int y = findSet(lineInfos[i][1]);
			if (x != y) {
				cost += lineInfos[i][2];
				union(x,y);
			}
		}
		
		System.out.println(cost);
	}
	
	public static int findSet(int x) {
		if (p[x] != x)
			p[x] = findSet(p[x]);
		return p[x];
	}
	
	public static void union(int x, int y) {
		p[y] = x;
	}
}