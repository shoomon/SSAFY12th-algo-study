package BOJ3085;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3085_dohee {
	static int N, maxCandy = 0;
	static String[][] board, swapBoard;
	static String tmp;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		swapBoard = new String[N][N];
		tmp = "";
		for (int i = 0; i < N; i++) {
			char[] line = bf.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				swapBoard[i][j] = String.valueOf(line[j]);
			}
		}
//		print(board);
		bomboni();
		
		System.out.println(maxCandy);
	}
	private static void bomboni() {

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				tmp = swapBoard[i][j-1];
				swapBoard[i][j-1] = swapBoard[i][j];
				swapBoard[i][j] = tmp;
				
				findMax();
				
				tmp = swapBoard[i][j-1];
				swapBoard[i][j-1] = swapBoard[i][j];
				swapBoard[i][j] = tmp;
				
			}
		}
		
		for (int j = 0; j < N; j++) {
			for (int i = 1; i < N; i++) {
				tmp = swapBoard[i-1][j];
				swapBoard[i-1][j] = swapBoard[i][j];
				swapBoard[i][j] = tmp;
				
				findMax();
//				print(swapBoard);
				
				tmp = swapBoard[i-1][j];
				swapBoard[i-1][j] = swapBoard[i][j];
				swapBoard[i][j] = tmp;
			}
		}
		
	}
	
	private static void findMax() {
		
		// 가로방향 체크
		for (int i = 0; i < N; i++) {
			int cnt = 1;
			for (int j = 1; j < N; j++) {
				if(maxCandy==N) return;
				if (swapBoard[i][j].equalsIgnoreCase(swapBoard[i][j-1])){
					cnt++;
					maxCandy = Math.max(cnt, maxCandy); 
				} else {
					maxCandy = Math.max(cnt, maxCandy); 
					cnt = 1;
				}
			}
		}
		
		// 세로방향 체크
		for (int j = 0; j < N; j++) {
			int cnt = 1;
				for (int i = 1; i < N; i++) {
					if(maxCandy==N) return;
					if (swapBoard[i][j].equalsIgnoreCase(swapBoard[i-1][j])){
						cnt++;
						maxCandy = Math.max(cnt, maxCandy); 
					} else {
						maxCandy = Math.max(cnt, maxCandy); 
						cnt = 1;
					}
			}
		}
		
	}
	
	private static void print(String[][] Board) {
		for (int i = 0; i < Board.length; i++) {
			System.out.println(Arrays.toString(Board[i]));
		}
		System.out.println();
	}
	
}
