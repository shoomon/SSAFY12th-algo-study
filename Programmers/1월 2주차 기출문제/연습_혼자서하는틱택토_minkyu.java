
public class tictacto {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] board = {"OOO", "...", "XXX"};
		int ans = sol.solution(board);
		System.out.println(ans);
	}
	
	static class Solution{
		char[][] ticBoard;
		public int solution(String[] board) {
			int answer = 1;
			int OCnt = 0;
			int XCnt = 0;
			ticBoard = new char[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					char curChar = board[i].charAt(j);
					if (curChar == 'O') OCnt++;
					if (curChar == 'X') XCnt++;
					ticBoard[i][j] = curChar;
				}
			}
			
			// OCnt보다 XCnt가 큰 경우는 무조건 발생할 수 없음.
			if (OCnt < XCnt) return 0;
			// OCnt와 XCnt의 차는 1보다 클 수 없음.
			if (OCnt - XCnt > 1) return 0;
			
			int OWinCnt = winCnt('O');
			int XWinCnt = winCnt('X');
			
			// O와 X 둘 다 3개의 줄이 있는 경우 불가능
			if (OWinCnt > 0 && XWinCnt > 0) return 0;
			// O가 승리한 경우 O의 개수가 1개 더 많아야 함
			if (OWinCnt > 0 && OCnt == XCnt) return 0;
			// X가 승리한 경우 O와 X의 개수가 동일해야 함.
			if (XWinCnt > 0 && OCnt > XCnt) return 0;
			
			return answer;
		}
		
		public int winCnt(char checker) {
			int winCnt = 0;
			// 행과 렬에 대한 승리 확인
			for (int i = 0; i < 3; i++) {
				int rCnt = 0;
				int cCnt = 0;
				for (int j = 0; j < 3; j++) {
					if (ticBoard[i][j] == checker) rCnt++;
					if (ticBoard[j][i] == checker) cCnt++;
				}
				if (rCnt == 3) winCnt++;
				if (cCnt == 3) winCnt++;
			}
			
			int rCnt = 0;
			int cCnt = 0;
			for (int i = 0; i < 3; i++) {
				if (ticBoard[i][i] == checker) rCnt++;
				if (ticBoard[2-i][i] == checker) cCnt++;
			}
			if (rCnt == 3) winCnt++;
			if (cCnt == 3) winCnt++;
			
			return winCnt;
		}
	}
}
 