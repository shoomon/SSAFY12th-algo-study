import java.util.*;
import java.io.*;

class Solution {
	static int ans;
	static int[] diffArr;
	static int[] timeArr;

	public int solution(int[] diffs, int[] times, long limit) {
		// 이분탐색을 통한 최솟값 찾기.
		// 난이도 최소, 최댓값에 맞추어 좌, 우 설정.
		int left = 1;
		int right = 100000;

		diffArr = diffs;
		timeArr = times;
		// 정답 찾고 내보내기
		find(left, right, limit);
		return ans;
	}

	// 이분탐색 진행하여 찾아내기
	public void find(int left, int right, long limit) {
		if (left <= right) {
			// 중간값을 구하기.
			int mid = (left + right) / 2;
			if (check(limit, mid)) {
				ans = mid;
				find(left, mid - 1, limit);
			} else
				find(mid + 1, right, limit);
		}
	}

	// 해당 값이 제한 시간 안에 도달할 수 있는 값인지 확인
	public boolean check(long limit, int level) {
		// 시간들의 누적합.
		long timeSum = 0;
		// 모든 문제들을 풀어보면서 시간이 얼마나 걸리는지 확인.
		for (int i = 0; i < diffArr.length; i++) {
			// 바로 풀 수 있는 것.
			if (diffArr[i] <= level)
				timeSum += timeArr[i];
			// 바로 풀 수 없는 것.
			else {
				long diff = diffArr[i] - level;
				long prevTime = (i - 1) < 0 ? 0 : timeArr[i - 1];
				timeSum += (prevTime + timeArr[i]) * diff + timeArr[i];
			}
		}

		return timeSum <= limit;
	}
}