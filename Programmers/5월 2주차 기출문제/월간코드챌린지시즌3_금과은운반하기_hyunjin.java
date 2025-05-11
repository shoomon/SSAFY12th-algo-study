package PRO_5월_2주차;

class Solution {
	public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
		// 트럭을 운반하는데 걸리는 최악의 시간
		// 필요시간 = 운반 횟수 * 왕복 시간
		// a와 b 각각 10^9개 필요 / 편도 최대 10^5
		// (2 * 10^9) * (2 * 10^5) = 4 * 10^14 => Long 범위 안!!

		// 최소 시간 : 0, 최대 시간 : 4 * 10^14 충분히 큰 수로 정의
		long left = 0;
		long right = 4L * (long) 1e14;
		long answer = right;

		// 이분 탐색으로 가능한 시간 찾기
		while (left <= right) {
			long mid = (left + right) / 2;

			// 모든 도시 돌면서 운반할 수 있는 금, 은, 총합
			long totalGold = 0;
			long totalSilver = 0;
			long totalWeight = 0;

			// 각 도시 별로 트럭으로 mid 시간 동안 옮길 수 있는 광물 계산하기
			for (int i = 0; i < g.length; i++) {
				long time = mid;
				long doubleTime = t[i] * 2L; // 왕복 시간 계산

				// mid 시간 동안 왕복 가능한 횟수
				long moveCnt = time / doubleTime;
				if (time % doubleTime >= t[i]) {
					// 마지막 편도 운반이 한 번 더 가능하면, 1회 추가
					moveCnt += 1;
				}

				// mid 시간 내에 최대 운반 가능한 총 무게
				long maxWeight = moveCnt * w[i];

				// 해당 도시의 금, 은과 현재 운반 가능한 최대 무게 비교
				long gold = Math.min(g[i], maxWeight);
				long silver = Math.min(s[i], maxWeight);

				// 해당 도시의 금 + 은 합친 무게가 운반 가능한 총 무게 넘을 수 없음
				long total = Math.min(g[i] + s[i], maxWeight);

				// 모든 도시를 돌면서 운반할 수 가능한 총 무게
				totalGold += gold;
				totalSilver += silver;
				totalWeight += total;
			}

			// 총 금은 a 이상, 총 은도 b 이상 필요 && 총 무게도 a+b 이상이어야 함.
			if (totalGold >= a && totalSilver >= b && totalWeight >= (a + b)) {
				// 모든 조건 만족 => 운반 가능 : 더 짧은 시간으로 운반 가능한지 확인
				answer = mid;
				right = mid - 1;
			} else {
				// 하나라도 불만족 => 운반 불가능 : 더 긴 시간 찾기
				left = mid + 1;
			}
		}

		return answer;
	}
}