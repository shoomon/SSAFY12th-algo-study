package PRO_1월_3주차;

import java.util.*;

public class 연습_숫자변환하기_hyunjin {
	public int solution(int x, int y, int n) {
		int[] dp = new int[y + 1];

		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[x] = 0;
		for (int i = x; i <= y; i++) {
			// Integer.MAX_VALUE 이면, 확인 X
			if (dp[i] == Integer.MAX_VALUE)
				continue;

			if (i + n <= y) {
				dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
			}

			if (i * 2 <= y) {
				dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
			}

			if (i * 3 <= y) {
				dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
			}
		}

		// 연산 공식으로 만들 수 없는 값인 경우, -1 반환
		if (dp[y] == Integer.MAX_VALUE) {
			dp[y] = -1;
		}

		return dp[y];
	}
}
