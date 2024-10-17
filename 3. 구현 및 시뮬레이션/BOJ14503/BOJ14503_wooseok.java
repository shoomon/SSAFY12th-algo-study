import java.io.*;
import java.util.*;

public class BOJ14503_wooseok {

	static int N, M, r, c, d; // N: 방의 세로 크기, M: 방의 가로 크기, r: 로봇 청소기의 초기 위치 행, c: 로봇 청소기의 초기 위치 열, d: 로봇 청소기의 초기 방향
	static int[][] arr; // 방의 상태를 저장하는 2차원 배열
	static int count = 1; // 청소한 칸의 개수, 초기 위치는 항상 청소함
	static int[] dx = {-1, 0, 1, 0}; // 방향을 나타내는 x축 변화량 (북, 동, 남, 서 순)
	static int[] dy = {0, 1, 0, -1}; // 방향을 나타내는 y축 변화량 (북, 동, 남, 서 순)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 방의 세로 크기 입력
		M = Integer.parseInt(st.nextToken()); // 방의 가로 크기 입력
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // 로봇 청소기의 초기 행 위치 입력
		c = Integer.parseInt(st.nextToken()); // 로봇 청소기의 초기 열 위치 입력
		d = Integer.parseInt(st.nextToken()); // 로봇 청소기의 초기 방향 입력

		arr = new int[N][M]; // 방의 상태를 저장할 2차원 배열 초기화

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); // 각 칸의 정보 입력 (0: 빈 칸, 1: 벽)
			}
		}

		clean(r, c, d); // 초기 위치에서 청소 시작
		System.out.println(count); // 청소한 칸의 개수 출력
	}

	public static void clean(int x, int y, int dir) {
		arr[x][y] = -1; // 현재 위치 청소 완료, -1로 표시

		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4; // 왼쪽 방향으로 회전
			int nx = x + dx[dir]; // 이동할 새로운 x 위치
			int ny = y + dy[dir]; // 이동할 새로운 y 위치

			if (nx >= 0 && ny >= 0 && nx < N && ny < M) { // 새로운 위치가 배열의 범위 내에 있는지 확인
				if (arr[nx][ny] == 0) { // 청소할 수 있는 빈 칸인 경우
					count++; // 청소한 칸의 개수 증가
					clean(nx, ny, dir); // 새로운 위치에서 청소 수행
					return;
				}
			}
		}

		// 네 방향 모두 청소가 불가능한 경우
		int backDir = (dir + 2) % 4; // 후진 방향 계산
		int bx = x + dx[backDir]; // 후진할 x 위치
		int by = y + dy[backDir]; // 후진할 y 위치

		if (bx >= 0 && by >= 0 && bx < N && by < M && arr[bx][by] != 1) { // 후진할 위치가 벽이 아니면
			clean(bx, by, dir); // 후진 수행, 방향은 그대로 유지
		}
	}
}
