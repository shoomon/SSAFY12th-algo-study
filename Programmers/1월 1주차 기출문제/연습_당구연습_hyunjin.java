package PRO_1월_1주차;

import java.util.Arrays;

public class 연습_당구연습_hyunjin {
	public static void main(String[] args) {
		int m = 10;
		int n = 10;
		int startX = 3;
		int startY = 7;
		int[][] balls = { { 7, 7 }, { 2, 7 }, { 7, 3 } };
		연습_당구연습_hyunjin sol = new 연습_당구연습_hyunjin();
		System.out.println(Arrays.toString(sol.solution(m, n, startX, startY, balls)));
	}

	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for (int i = 0; i < balls.length; i++) {
			int targetX = balls[i][0]; // 타겟하는 x 좌표 
			int targetY = balls[i][1]; // 타겟하는 y 좌표 

			int dist = 0;
			int result = Integer.MAX_VALUE;

			// 상하좌우 기준으로 대칭 시켜서 최소 거리 계산하기 
			// 좌
			if (!(startY == targetY && startX >= targetX)) {
				dist = calc(startX, startY, targetX * (-1), targetY);
				result = Math.min(result, dist);
			}

			// 우
			if (!(startY == targetY && startX <= targetX)) {
				dist = calc(startX, startY, targetX + 2 * (m - targetX), targetY);
				result = Math.min(result, dist);
			}

			// 상
			if (!(startX == targetX && startY <= targetY)) {
				dist = calc(startX, startY, targetX, targetY + 2 * (n - targetY));
				result = Math.min(result, dist);
			}

			// 하
			if (!(startX == targetX && startY >= targetY)) {
				dist = calc(startX, startY, targetX, targetY * (-1));
				result = Math.min(result, dist);
			}

			answer[i] = result;

		}
		return answer;
	}

	// 거리 계산하기 
	static int calc(int startX, int startY, int targetX, int targetY) {
		return (int) (Math.pow(startX - (targetX), 2) + Math.pow(startY - (targetY), 2));
	}
}
