package recursion;

import java.io.*;
import java.util.*;

public class BOJ_2529 {

	static int N, minVal, maxVal;
	static char signs[];
	static ArrayList<String> resList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		signs = new char[N];
		String line = br.readLine();
		minVal = Integer.MAX_VALUE;
		maxVal = Integer.MIN_VALUE;
		resList = new ArrayList<String>();
		for (int i = 0; i < N; i++) {
			signs[i] = line.charAt(2 * i);
		}

		sign(0, new int[N + 1], new boolean[10], -1);
		
		Collections.sort(resList);
		System.out.println(resList.get(resList.size()-1));
		System.out.println(resList.get(0));

	}

	static void sign(int cnt, int[] res, boolean[] selected, int prev) {
		if (cnt == N + 1) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<res.length;i++) {
				sb.append(res[i]);
			}
			resList.add(sb.toString());
			return;
		}

		for (int i = 0; i < 10; i++) {
			// 선택된 숫자면 지나치기
			if (!selected[i]) {
				if (cnt == 0 || isRight(res[cnt - 1], i, signs[cnt - 1])) { // 부등호에 안 맞으면 지나치기

					res[cnt] = i;
					selected[i] = true;
					sign(cnt + 1, res, selected, i);
					selected[i] = false;
				}
			}
		}
	}

	static boolean isRight(int prev, int cur, char sign) {
		if (sign == '>') {
			return prev > cur;
		} else {
			return prev < cur;
		}
	}
}
