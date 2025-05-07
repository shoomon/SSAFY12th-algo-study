package PRO_5월_1주차;

import java.util.*;

class 위클리챌린지_교점에별만들기_hyunjin {

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public String[] solution(int[][] line) {
		// 교점을 저장하기 위한 Set
		Set<Point> points = new HashSet<>();

		// 1. 교점 구하기
		for (int i = 0; i < line.length; i++) {
			// (Ax + By + E = 0)
			int A = line[i][0], B = line[i][1], E = line[i][2];

			for (int j = i + 1; j < line.length; j++) {
				// (Cx + Dy + F = 0)
				int C = line[j][0], D = line[j][1], F = line[j][2];

				// 분모가 0이면 두 직선은 평행
				// 20만 * 20만 => long
				long denominator = (long) A * D - (long) B * C;
				if (denominator == 0)
					continue;

				// 분자 / 분모 == 소수 즉, 분자 % 분모 != 0 => 소수이다
				long yNumerator = (long) C * E - (long) A * F;
				long xNumerator = (long) B * F - (long) D * E;
				if (yNumerator % denominator != 0 || xNumerator % denominator != 0)
					continue;

				// x,y 좌표가 정수인 좌표는 Set에 추가
				int x = (int) (xNumerator / denominator);
				int y = (int) (yNumerator / denominator);

				points.add(new Point(x, y));
			}
		}

		// 2. 교점 중 최대, 최소 x,y 좌표 구하기
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (Point p : points) {
			maxX = Math.max(maxX, p.x);
			maxY = Math.max(maxY, p.y);
			minX = Math.min(minX, p.x);
			minY = Math.min(minY, p.y);
		}

		// 3. 가로, 세로 별 그릴 격자판 길이
		int xLen = maxX - minX + 1;
		int yLen = maxY - minY + 1;

		char[][] board = new char[yLen][xLen];

		// 초기화하기
		for (char[] row : board) {
			Arrays.fill(row, '.');
		}

		// 4. 별표 그리기
		for (Point p : points) {
			// 격자판 크기를 맞춰놓았기 때문에, (p.x, p.y) 위치 조정
			int r = maxY - p.y; // ex) maxY = 4, p.y = 4 ---> r = 0 (최상단)
			int c = -(minX - p.x); // ex) minX = -2, p.x = -1 ---> c = -((-2)-(-1)) = 1
			// (0.1) 위치에 별 찍힘
			board[r][c] = '*';
		}

		// 5. 문자열로 변환
		String[] answer = new String[yLen];
		for (int i = 0; i < yLen; i++) {
			answer[i] = new String(board[i]);
		}
		return answer;
	}
}

// (Ax + By + E = 0) * C or D
// (Cx + Dy + F = 0) * A or B
// --------------------------
// (AD - BC)y + (AF - CE) = 0
// (AD - BC)x + (DE - BF) = 0
// AD - BC = 0 => 평행