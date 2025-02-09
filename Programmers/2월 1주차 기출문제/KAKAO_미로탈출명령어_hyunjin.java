package Programmers.PRO_2월_2주차;

public class KAKAO_미로탈출명령어_hyunjin {

	static int[] dr = {1, 0, 0, -1}; // d, l, r, u 순서
	static int[] dc = {0, -1, 1, 0};
	static char[] dir = {'d', 'l', 'r', 'u'};

	static String answer;
	static int N, M, K, targetX, targetY;

	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
		// answer 초기화
		answer = "impossible";

		N = n;
		M = m;
		K = k;
		targetX = r;
		targetY = c;

		int minDist = Math.abs(r - x) + Math.abs(c - y);

		// 도달 불가능한 경우 미리 리턴
		if (minDist > k || (k - minDist) % 2 != 0) {
			return "impossible";
		}

		dfs(x, y, k, new StringBuilder());
		return answer;
	}

	private static void dfs(int x, int y, int remaining, StringBuilder path) {
		if (!answer.equals("impossible")) return; // 이미 답을 찾았으면 중단

		if (remaining == 0) {
			if (x == targetX && y == targetY) {
				answer = path.toString();
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dr[i];
			int ny = y + dc[i];

			if (nx < 1 || nx > N|| ny < 1 || ny > M) continue; // 범위 체크

			int distToTarget = Math.abs(targetX - nx) + Math.abs(targetY - ny);

			// 남은 이동 횟수가 거리보다 작거나, 홀수 -> continue
			if (distToTarget > remaining - 1 || (remaining - 1 - distToTarget) % 2 != 0) continue;

			path.append(dir[i]);
			dfs(nx, ny, remaining - 1, path);
			path.deleteCharAt(path.length() - 1);
		}
	}
}

