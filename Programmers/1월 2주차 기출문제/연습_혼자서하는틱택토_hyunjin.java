package PRO_1월_2주차;

import java.util.*;
import java.io.*;

class 연습_혼자서하는틱택토_hyunjin {
	static char[][] map;

	public int solution(String[] board) {
		map = new char[3][3];
		int OCnt = 0;
		int XCnt = 0;

		for (int i = 0; i < 3; i++) {
			String str = board[i];
			for (int j = 0; j < 3; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'O')
					OCnt++;
				if (map[i][j] == 'X')
					XCnt++;
			}
		}

		// for(int i=0; i<3; i++){
		// for(int j=0; j<3; j++){
		// System.out.print(map[i][j]);
		// }
		// System.out.println();
		// }

		int answer = 1;

		// O가 먼저 시작인데, OCnt < XCnt 이면 규칙 위반 (불가능)
		// OCnt와 XCnt 차이가 2이상인 경우, 번갈아가면서 놓지 않았다는 말 (불가능)
		if (OCnt < XCnt || Math.abs(OCnt - XCnt) > 1)
			answer = 0;

		// 보드에 O, X 어떤 표시도 없다면 => (가능)
		if (OCnt == 0 && XCnt == 0)
			answer = 1;

		boolean OWin = check('O');
		boolean XWin = check('X');

		// 둘 다 이기는 경우 => 규칙 위배 (불가능)
		if (OWin && XWin)
			answer = 0;

		// OWin이 이겼는데, X를 더 놓는 경우
		if (OWin && OCnt == XCnt)
			return 0;
		// XWin이 이겼는데, O를 더 놓는 경우
		if (XWin && OCnt > XCnt)
			return 0;

		return answer;
	}

	public static boolean check(char c) {
		// 가로 또는 세로 빙고
		for (int i = 0; i < 3; i++) {
			if (map[i][0] == c && map[i][1] == c && map[i][2] == c)
				return true;
			if (map[0][i] == c && map[1][i] == c && map[2][i] == c)
				return true;
		}

		// 대각선 빙고
		if (map[0][0] == c && map[1][1] == c && map[2][2] == c)
			return true;
		if (map[0][2] == c && map[1][1] == c && map[2][0] == c)
			return true;

		return false;
	}

}
