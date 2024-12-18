/* 
 [PCCP 기출문제]_2번_퍼즐 게임 챌린지 

1차 시도 : 완탐 => 모든 level + 1 씩 하면서 탐색 -> 시간 초과
-> 10만 * 30만 = 약 300초... 바로 터짐

2차 풀이 : 이진탐색 진행
-> min과 max 를 사용하여 mid 값 구하기
-> 이후, mid 를 사용해서 탐색할 필요없는 범위 날리기 
 */


class PCCP_2_hyunjin {
	public int solution(int[] diffs, int[] times, long limit) {
		int answer = 0;

		int minLevel = 1; // diffs[0] = 1이므로
		int maxLevel = -1;
		for (int i = 0; i < diffs.length; i++) {
			maxLevel = Math.max(maxLevel, diffs[i]);
		}

		// 이진 탐색 시작
		// minLevel > maxLevel 보다 커지면 stop
		/* ex)
		   min = 1; max = 5; mid = 3;
		   min = 1; max = 3-1 = 2; ans = mid = 3;
		   mid = 1;
		   min = 2, max = 2; ans = 3;
		   mid = 2;
		   min = 3; max = 2 -> min > max -> stop => 여전히 ans = 3;
		 * */

		while (minLevel <= maxLevel) {
			// level은 중간값(mid)부터 시작
			int mid = (minLevel + maxLevel) / 2;

			if (canComplete(diffs, times, limit, mid)) {
				// 시간 내 해결 가능하다면,
				// 더 낮은 레벨 탐색 == 최솟값 찾기
				answer = mid;
				maxLevel = mid - 1;
			} else {
				// 시간 내 해결하지 못한다면,
				// mid+1로 min 값을 땡겨와서 mid 아래 값들은 모두 탐색하지 않음
				minLevel = mid + 1;
			}
		}

		return answer;
	}

	public static boolean canComplete(int[] diffs, int[] times, long limit, int mid) {
		int level = mid;

		for (int i = 0; i < diffs.length; i++) {
			// level이 난이도보다 높거나 같다면,
			// 제한 시간 - 문제풀이 시간 
			if (diffs[i] <= level) {
				limit -= times[i];
			
			// level이 난이도 보다 낮다면, 	
			} else {
				int errorCnt = diffs[i] - level; // 틀리는 횟수 
				// 걸리는 시간 : (time_prev + time_curr) * errorCnt + time_curr
				int time = (times[i - 1] + times[i]) * errorCnt + times[i];
				limit -= (long) time;
			}

			// 이미 제한 시간을 초과했다면, return
			if (limit < 0) {
				return false; // 제한 시간 초과
			}
		}
		return true; // 제한 시간은 통과
	}
}