package PRO_12월_5주차;

import java.util.*;

public class 연습문제_요격시스템_hyunjin {

	public int solution(int[][] targets) {
		// 구간을 종료 지점 기준으로 오름차순 정렬
		Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

		int answer = 0;
		double lastPosition = -1; // 마지막으로 미사일을 발사한 위치

		for (int[] target : targets) {
			// 현재 미사일이 해당 구간을 커버하지 못하는 경우
			if (lastPosition <= target[0]) {
				lastPosition = target[1];
				answer++; // 새로운 요격 미사일 추가
			}
		}

		return answer;
	}

}
