import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
최소 스패닝 트리

그래프가 주어졌을 때, 해당 그래프의 최소 스패닝 트리를 구하는 프로그램 작성

메모리 : 49760KB
시간 : 548 ms
*/

public class Main {
	static int[] p = {};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nodeCnt = Integer.parseInt(st.nextToken());
		int lineCnt = Integer.parseInt(st.nextToken());
		int[][] lines = new int[lineCnt][3];
		// 서로소 집합 초기화
		p = new int[nodeCnt + 1];
		for (int i = 1; i <= nodeCnt; i++) {
			p[i] = i;
		}
		for (int i = 0; i < lineCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			lines[i][0] = a;
			lines[i][1] = b;
			lines[i][2] = c;
		}
		
		Arrays.sort(lines, (o1, o2)->{
			if (o1[2] > o2[2])
				return 1;
			else
				return -1;
		});
		
		int cost = 0;
		for (int i = 0; i < lines.length; i++) {
			int x = findSet(lines[i][0]);
			int y = findSet(lines[i][1]);
			if (x != y) {
				cost += lines[i][2];
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