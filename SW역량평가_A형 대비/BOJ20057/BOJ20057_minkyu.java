import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int size = Integer.parseInt(br.readLine());
		int[][] sandInfo = new int[size][size];
		// 모래 정보 채우기
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++)
				sandInfo[i][j] = Integer.parseInt(st.nextToken());
		}

		// 소용돌이 가야될 방향 정하기
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { -1, 0, 1, 0 };

		int[] sandPercent = {5,10,10,7,7,2,2,1,1};
		int[][] findDr = { { 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 }, { 2, 1, 1, 0, 0, 0, 0, -1, -1, 1 },
				{ 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 }, { -2, -1, -1, 0, 0, 0, 0, 1, 1, -1 } };
		int[][] findDc = { { -2, -1, -1, 0, 0, 0, 0, 1, 1, -1 }, { 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 },
				{ 2, 1, 1, 0, 0, 0, 0, -1, -1, 1 }, { 0, -1, 1, -1, 1, -2, 2, -1, 1, 0 } };

		// 현재위치 -> 시작은 가운데
		int[] curPos = { size / 2, size / 2 };

		// 방향 바꾼 횟수
		int dirCnt = 1;
		// 현재 방향으로 가는 횟수
		int curCnt = 1;
		int curDir = 0;

		// 밖으로 나간 모래의 양
		int outSands = 0;
		while (true) {
			// 1행 1열에 도착하면 반복문 종료
			if (curPos[0] == 0 && curPos[1] == 0)
				break;

			// 현재 방향으로 가야되는 횟수만큼 이동
			for (int i = 0; i < curCnt; i++) {
				// 위치 이동
				curPos[0] += dr[curDir];
				curPos[1] += dc[curDir];

				// 현재 날려야되는 모래양
				int curSand = sandInfo[curPos[0]][curPos[1]];

				// 모래 내보내기
				for (int j = 0; j < 10; j++) {
					int moveSand = 0;
					if (j == 9)
						moveSand = sandInfo[curPos[0]][curPos[1]];
					else
						moveSand = curSand * sandPercent[j] / 100;
					
					sandInfo[curPos[0]][curPos[1]] -= moveSand;
					
					int curR = curPos[0] + findDr[curDir][j];
					int curC = curPos[1] + findDc[curDir][j];
					if (0 <= curR && curR < size && 0 <= curC && curC < size)
						sandInfo[curR][curC] += moveSand;
					else
						outSands += moveSand;
				}

//				System.out.println("현재 위치 r : " + curPos[0] + " c :" + curPos[1]);
//				System.out.println("밖으로 간 모래 양 : " + outSands);
//				for (int k = 0; k < size; k++) {
//					for (int j = 0; j < size; j++) {
//						System.out.print(sandInfo[k][j]);
//						System.out.print(" ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}

			// 2번째마다 바꿔주고 마지막 크기보다 1작은 경우엔 계속 유지
			if (dirCnt % 2 == 0 && curCnt != size - 1)
				curCnt++;
			// 방향 바꾼 횟수 추가
			dirCnt++;
			// 방향 바꿔주기
			curDir = (curDir + 1) % 4;
		} // 반복문 종료

		System.out.println(outSands);
	}
}