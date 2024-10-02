import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
도시 분할 계획

마을을 2개로 나누어 해당 마을들에 해당하는 MST를 구하시오.

메모리 : 341036 KB
시간 : 2108 ms

*/

public class Main {
	static int[] p = {};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hCnt = Integer.parseInt(st.nextToken());
		int lCnt = Integer.parseInt(st.nextToken());
		
		int[][] lines = new int[lCnt][3];
		for (int i = 0; i < lCnt; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
			lines[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		Arrays.sort(lines, (o1,o2)->{
			if (o1[2] > o2[2])
				return 1;
			else if (o1[2] == o2[2])
				return 0;
			else
				return -1;
		});
		
		p = new int[hCnt + 1];
		for (int i = 1; i<= hCnt; i++)
			p[i] = i;
		
		int maxCost = 0;
		int costSum = 0;
		for (int i = 0; i < lCnt; i++) {
			int x = findSet(lines[i][0]);
			int y = findSet(lines[i][1]);
			if (x!=y) {
				if (maxCost < lines[i][2])
					maxCost = lines[i][2];
				costSum += lines[i][2];
				union(x,y);
			}
		}
		
		System.out.println(costSum - maxCost);
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