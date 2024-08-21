import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* 사탕게임
상근이는 어렸을 적에 "봄보니 (Bomboni)" 게임을 즐겨했다.

가장 처음에 N×N크기에 사탕을 채워 놓는다. 사탕의 색은 모두 같지 않을 수도 있다. 상근이는 사탕의 색이 다른 인접한 두 칸을 고른다. 그 다음 고른 칸에 들어있는 사탕을 서로 교환한다. 이제, 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분(행 또는 열)을 고른 다음 그 사탕을 모두 먹는다.

사탕이 채워진 상태가 주어졌을 때, 상근이가 먹을 수 있는 사탕의 최대 개수를 구하는 프로그램을 작성하시오.
*/
public class Main {
	static char[][] board = {};
	static int maxCnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int boardSize = Integer.parseInt(br.readLine());
		
		board = new char[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			String curLine = br.readLine();
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = curLine.charAt(j);
			}
		}
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize - 1; j++) {
				if (board[i][j] != board[i][j+1]) {
					char tmp = board[i][j];
					board[i][j] = board[i][j+1];
					board[i][j+1] = tmp;
					checkCnt();
					board[i][j+1] = board[i][j];
					board[i][j] = tmp;
				}
				
				if (board[j][i] != board[j+1][i]) {
					char tmp = board[j][i];
					board[j][i] = board[j+1][i];
					board[j+1][i] = tmp;
					checkCnt();
					board[j+1][i] = board[j][i];
					board[j][i] = tmp;
				}
			}
		}
		
		System.out.println(maxCnt);
	}
	
	public static void checkCnt() {
		
		int cnt = 0;
		char beforeChar = 0;
		for (int i = 0; i < board.length; i++) {
			cnt = 0;
			beforeChar = 0;
			for (int j = 0; j < board.length; j++) {
				if (beforeChar == board[i][j])
					cnt++;
				else {
					if (maxCnt < cnt)
						maxCnt = cnt;
					beforeChar = board[i][j];
					cnt = 1;
				}
			}
			if (maxCnt < cnt)
				maxCnt = cnt;
			
			cnt = 0;
			beforeChar = 0;
			for (int j = 0; j < board.length; j++) {
				if (beforeChar == board[j][i])
					cnt++;
				else {
					if (maxCnt < cnt)
						maxCnt = cnt;
					beforeChar = board[j][i];
					cnt = 1;
				}
			}
			if (maxCnt < cnt)
				maxCnt = cnt;
		}
	}
}