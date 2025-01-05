package PRO_12월_5주차;

import java.util.*;

public class 연습문제_연속된부분수열의합_hyunjin {
	public static void main(String[] args) {
		int[] sequence = new int[] { 1, 2, 3, 4, 5 };
		int k = 7;
		연습문제_연속된부분수열의합_hyunjin sol = new 연습문제_연속된부분수열의합_hyunjin();
		System.out.println(Arrays.toString(sol.solution(sequence, k)));

	}

	public int[] solution(int[] sequence, int k) {

		int N = sequence.length;
		// 연속된 부분 수열의 인덱스 값 초기화
		int left = 0;
		int right = 0;

		int[] answer = { left, right };

		int sum = sequence[0]; // 초기 구간 합
		int minLength = N; // 초기 부분 수열 길이 == 배열의 길이 => 가장 작은 부분 수열의 길이로 갱신

		while (right < N) {
			// 합이 같을 경우,
			if (sum == k) {
				// 현재 구간이 더 짧다면, 갱신
				if (right - left < minLength) {
					minLength = right - left;
					// 정답 인덱스도 갱신
					answer[0] = left;
					answer[1] = right;
				}

				// 구간을 더 축소해서 정답이 있는지 확인
				sum -= sequence[left];
				left++;

				// 합이 k보다 작다면, 오른쪽 구간을 늘려나가기
			} else if (sum < k) {
				right++;
				if (right < N) {
					sum += sequence[right];
				}

				// 합이 k보다 크다면, 왼쪽 구간을 당겨서 축소하기
			} else {
				sum -= sequence[left];
				left++;
			}

		}

		return answer;
	}

}
