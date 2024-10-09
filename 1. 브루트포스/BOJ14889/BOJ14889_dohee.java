package recursion;
/*
14692KB	304ms
24m
 */
import java.io.*;
import java.util.*;

public class BOJ_14889 {
	static int N, map[][], minVal;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		minVal = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		startCombi(0, 0, new boolean[N + 1]);
		System.out.println(minVal);
	}

	static void startCombi(int cnt, int start, boolean[] selected) {
		if (cnt == N / 2) {
			// 스타트팀의 능력치와 링크팀의 능력치 계산
			int startCapa = 0;
			int linkCapa = 0;
			ArrayList<Integer> startList = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (selected[i] && selected[j]) {
						startCapa += map[i][j];
					} else if (!selected[i] && !selected[j]) {
						linkCapa += map[i][j];
					}
				}
			}
			minVal = Math.min(Math.abs(startCapa-linkCapa), minVal);

			return;
		}

		for (int i = start; i < N; i++) {
			if (selected[i])
				continue;
			selected[i] = true;
			startCombi(cnt + 1, i + 1, selected);
			selected[i] = false;
		}

	}
}
