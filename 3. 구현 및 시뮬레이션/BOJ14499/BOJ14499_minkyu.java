import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
주사위 굴리기

지도에서 주사위를 굴려서 바닥에 있는걸 복사하며 이동한다.
이동할 때마다의 주사위 윗 면에 쓰여있는 수를 출력한다.


메모리 : 14912 KB
시간 : 136 ms

*/

public class Main {
	static int[] dice = {};

	static int rSize;
	static int cSize;

	static int[] curPos = {};

	static int[][] map = {};

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[6];

		rSize = Integer.parseInt(st.nextToken());
		cSize = Integer.parseInt(st.nextToken());

		// 주사위 현위치
		curPos = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		int comCnt = Integer.parseInt(st.nextToken());

		// 맵에 대한 정보
		map = new int[rSize][cSize];
		for (int i = 0; i < rSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < cSize; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < comCnt; i++) {
			int dir = Integer.parseInt(st.nextToken());
			move(dir);
		}
	}

	public static void move(int dir) {
		// 움직일 수 없으면 리턴
		if (!movable(dir))
			return;

		// 주사위 굴리는 위치 갱신
		curPos[0] += dr[dir - 1];
		curPos[1] += dc[dir - 1];

		// 주사위 굴리기
		rollDice(dir, map[curPos[0]][curPos[1]]);
		
		// 맵이 0인 경우 주사위에 있는 값 복사
		if (map[curPos[0]][curPos[1]] == 0)
			map[curPos[0]][curPos[1]] = dice[0];
		// 맵이 0이 아닌 경우 주사위에 바닥 값 복사
		else {
			dice[0] = map[curPos[0]][curPos[1]];
			map[curPos[0]][curPos[1]] = 0;
		}
		
		System.out.println(dice[2]);
	}

	public static boolean movable(int dir) {
		switch (dir) {
		// 동쪽으로 이동
		case 1:
			if (curPos[1] == cSize - 1)
				return false;
			break;
		// 서쪽으로 이동
		case 2:
			if (curPos[1] == 0)
				return false;
			break;
		// 북쪽으로 이동
		case 3:
			if (curPos[0] == 0)
				return false;
			break;
		// 남쪽으로 이동
		case 4:
			if (curPos[0] == rSize - 1)
				return false;
			break;
		}
		return true;
	}

	public static void rollDice(int dir, int val) {
		// dice 순서 -> 0 : 바닥, 1 : 동쪽, 2 : 위, 3 : 서쪽, 4 : 북쪽, 5 : 남쪽
		switch (dir) {
		// 동쪽
		case 1:
			int[] newDiceEast = new int[] { dice[1], dice[2], dice[3], dice[0], dice[4], dice[5] };
			dice = newDiceEast;
			break;
		// 서쪽
		case 2:
			int[] newDiceWest = new int[] { dice[3], dice[0], dice[1], dice[2], dice[4], dice[5] };
			dice = newDiceWest;
			break;
		// 북쪽
		case 3:
			int[] newDiceNorth = new int[] { dice[4], dice[1], dice[5], dice[3], dice[2], dice[0] };
			dice = newDiceNorth;
			break;
		// 남쪽
		case 4:
			int[] newDiceSouth = new int[] { dice[5], dice[1], dice[4], dice[3], dice[0], dice[2] };
			dice = newDiceSouth;
			break;
		}

		// 0이 아닌 경우 바닥에 있던 값으로 주사위 갱신
		if (val != 0)
			dice[0] = val;
	}
}