package 백트래킹;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// BOJ2992_크면서 작은 수
public class BOJ2992_hyunjin {

	static int num;
	static int[] numArr;
	static int[] result;
	static boolean[] visited;
	static int N; // 배열의 길이
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		String numStr = Integer.toString(num);

		N = numStr.length();
		numArr = new int[N];
		result = new int[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			numArr[i] = numStr.charAt(i) - '0';
		}

		perm(0, 0);

		Collections.sort(list);
//		System.out.println(list);

		int ans = (list.size() < 2 ? 0 : list.get(1));
		System.out.println(ans);

	} // main

	// 순열 구하기
	static void perm(int idx, int cnt) {
		if (cnt == N) {
			String ansStr = "";
			for (int a : result) {
				ansStr += Integer.toString(a);
			}
//			System.out.println(Arrays.toString(result));

			// 초기 입력 받은 값보다 작을 경우, return;
			if (Integer.parseInt(ansStr) < num)
				return;

			list.add(Integer.parseInt(ansStr));
		}

		int sameNum = 0; // 중복되는 수

		for (int i = 0; i < N; i++) {
			// 중복되는 수를 만날 경우 continue
			if (visited[i] || numArr[i] == sameNum)
				continue;

			result[cnt] = numArr[i];
			sameNum = numArr[i];
			visited[i] = true;
			perm(i, cnt + 1);
			visited[i] = false;
		}
	}
}